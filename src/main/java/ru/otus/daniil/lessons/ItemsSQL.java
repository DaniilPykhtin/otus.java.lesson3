package ru.otus.daniil.lessons;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemsSQL implements ItemsStorage {
    private static final String URL = "jdbc:sqlite::resource:items.db";
    private static final String GET_QUERY = "select itemId, itemName from items";
    private static final String DEL_QUERY = "delete from items";
    private static final String PUT_QUERY = "insert into items values (%d, '%s')";

    private Connection conn;

    public ItemsSQL() {
        try {
            this.conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Item> get() {
        List<Item> items = new ArrayList<>();

        try (Statement statement = conn.createStatement()) {
            ResultSet resultSet = statement.executeQuery(GET_QUERY);
            while (resultSet.next())
                items.add(new Item(resultSet.getLong("itemId"), resultSet.getString("itemName")));

            } catch (SQLException e) {
                e.printStackTrace();
        }
        //System.out.println(items.size());
        return items;
    }

    @Override
    public void put(List<Item> items) {
        try (Statement statement = conn.createStatement()) {
            for (Item item : items) {
                statement.execute(String.format(PUT_QUERY, item.getId(),  item.getTitle()));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
