package org.example;

import java.util.List;

public class JpaPublishingHouseRepository implements PublishingHouseRepository {
    @Override
    public void save(PublishingHouse publishingHouse) {
        System.out.println("Saving publishing house to database: " + publishingHouse);
    }

    @Override
    public List<PublishingHouse> findAll() {
        System.out.println("Retrieving all publishing houses from database");
        return null;
    }
}