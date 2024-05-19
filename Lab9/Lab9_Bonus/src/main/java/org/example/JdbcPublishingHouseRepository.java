package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcPublishingHouseRepository implements PublishingHouseRepository {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=java";
    private static final String USERNAME = "java";
    private static final String PASSWORD = "java";

    @Override
    public void save(PublishingHouse publishingHouse) {
        String sql = "INSERT INTO publishing_houses (name) VALUES (?)";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, publishingHouse.getName());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error while saving publishing house", e);
        }
    }

    @Override
    public List<PublishingHouse> findAll() {
        List<PublishingHouse> publishingHouses = new ArrayList<>();
        String sql = "SELECT * FROM publishing_houses";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                PublishingHouse publishingHouse = new PublishingHouse();
                publishingHouse.setId(resultSet.getLong("id"));
                publishingHouse.setName(resultSet.getString("name"));
                publishingHouses.add(publishingHouse);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching publishing houses", e);
        }

        return publishingHouses;
    }
}