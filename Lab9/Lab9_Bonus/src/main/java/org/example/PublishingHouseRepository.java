package org.example;

import java.util.List;

public interface PublishingHouseRepository {
    void save(PublishingHouse publishingHouse);
    public List<PublishingHouse> findAll();
}
