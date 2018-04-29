package com.example.project3;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class VehicleDoa {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Vehicle vehicle) {

        entityManager.persist(vehicle);
        return;
    }

    public Vehicle getById(int id) {

        return entityManager.find(Vehicle.class, id);
    }

    public Vehicle update(Vehicle vehicle) {

        if (entityManager.find(Vehicle.class, vehicle.getId()) == null) {
            System.out.println("Unknown ID entered for update.");
            return vehicle;
        }
        else {
            return entityManager.merge(vehicle);
        }
    }

    public ResponseEntity<String> delete(int id) {

        if (entityManager.find(Vehicle.class, id) == null) {
            System.out.println("Unknown ID entered for delete.");
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        else {
            entityManager.remove(entityManager.find(Vehicle.class, id));
            return new ResponseEntity<String>(HttpStatus.OK);
        }
    }

    public List<Vehicle> getLatest () {

        return entityManager.createNativeQuery("SELECT * from Inventory order by id desc limit 10").getResultList();

    }

}
