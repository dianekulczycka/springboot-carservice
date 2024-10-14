package org.example.springbootcarservice.repository;

import org.example.springbootcarservice.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository // a class that interacts with DB
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("select c from Car c where c.enginePower = (select MIN(c2.enginePower) from Car c2)")
    Car findMinPower();

    @Query("select c from Car c where c.enginePower = (select MAX(c2.enginePower) from Car c2)")
    Car findMaxPower();
}

//@Repository
//public class CarsRepository {
//
//    @PersistenceContext
//    private EntityManager em;
//
//    public List<Car> findAll() {
//        return em.createQuery("select c from Car c", Car.class).getResultList();
//    }
//
//    public Car findById(int id) {
//        return em.find(Car.class, id);
//    }
//
//    public Car findMinPower() {
//        return em.createQuery("select c from Car c where c.enginePower = (select MIN(c2.enginePower) from Car c2)", Car.class)
//                .getSingleResult();
//    }
//
//    public Car findMaxPower() {
//        List<Car> cars = em.createQuery("select c from Car c", Car.class).getResultList();
//        return cars.stream()
//                .max(Comparator.comparingInt(Car::getEnginePower))
//                .orElse(null);
//    }
//
//    @Transactional
//    public void save(Car customer) {
//        em.persist(customer);
//    }
//
//    @Transactional
//    public void update(Car customer) {
//        em.merge(customer);
//    }
//
//    @Transactional
//    public void delete(int id) {
//        em.remove(em.find(Car.class, id));
//    }
//}
