package org.example.springbootcarservice.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.springbootcarservice.models.CarRepository;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;

@Repository
public class CarsDAO {

    @PersistenceContext
    private EntityManager em;

    public List<CarRepository> findAll() {
        return em.createQuery("select c from CarRepository c", CarRepository.class).getResultList();
    }

    public CarRepository findById(int id) {
        return em.find(CarRepository.class, id);
    }

    public CarRepository findMinPower() {
        return em.createQuery("select c from CarRepository c where c.enginePower = (select MIN(c2.enginePower) from CarRepository c2)", CarRepository.class)
                .getSingleResult();
    }

    public CarRepository findMaxPower() {
        List<CarRepository> cars = em.createQuery("select c from CarRepository c", CarRepository.class).getResultList();
        return cars.stream()
                .max(Comparator.comparingInt(CarRepository::getEnginePower))
                .orElse(null);
    }

    @Transactional
    public void save(CarRepository customer) {
        em.persist(customer);
    }

    @Transactional
    public void update(CarRepository customer) {
        em.merge(customer);
    }

    @Transactional
    public void delete(int id) {
        em.remove(em.find(CarRepository.class, id));
    }
}
