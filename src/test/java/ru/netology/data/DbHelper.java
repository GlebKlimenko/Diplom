package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;



public class DbHelper {
    private final QueryRunner runner = new QueryRunner();
    public static Connection connection;
    public static Statement statement;

    public static String url = System.getProperty("property.URL");
    public static String user = System.getProperty("property.NAME_USER");
    public static String password = System.getProperty("property.PASSWORD");

    static {
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @SneakyThrows
    public String getPaymentStatus() {
        var status = "SELECT status FROM payment_entity ORDER BY created DESC";
        return runner.query(connection, status, new ScalarHandler<>());
    }

    @SneakyThrows
    public Integer getPaymentAmount() {
        var amount = "SELECT amount FROM payment_entity ORDER BY created DESC";
        return runner.query(connection, amount, new ScalarHandler<>());
    }

    @SneakyThrows
    public String getCreditRequestStatus() {
        var status = "SELECT status FROM credit_request_entity ORDER BY created DESC";
        return runner.query(connection, status, new ScalarHandler<>());
    }

    @SneakyThrows
    public String getCreditId() {
        var id = "SELECT credit_id FROM order_entity ORDER BY created DESC";
        return runner.query(connection, id, new ScalarHandler<>());
    }
}
