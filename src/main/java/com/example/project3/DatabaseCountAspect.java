package com.example.project3;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.math.BigInteger;

@Repository
@Transactional
@Component
@Aspect
public class DatabaseCountAspect {          //DISPLAYS THE AMOUNT OF ROWS IN THE INVENTORY TABLE AFTER EACH TASK IS RAN

    @PersistenceContext
    private EntityManager entityManager;

    @Pointcut("execution(public * com.example..*(..))")
    public void publicMethod() {

    }

    @Before("publicMethod()")
    public void countDatabase(final JoinPoint joinPoint) {

        Query query = entityManager.createNativeQuery("SELECT COUNT(*) FROM inventory");

        System.out.println("ROWS IN SET: " + query.getSingleResult());
    }

}
