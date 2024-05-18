package org.example;

import jakarta.persistence.EntityManager;

public class PublishingHouseRepository extends AbstractRepository<PublishingHouse> {

    public PublishingHouseRepository(EntityManager entityManager) {
        super(entityManager, PublishingHouse.class);
    }

}