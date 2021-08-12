package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DbHelper {
    private final QueryRunner runner = new QueryRunner();
    private Properties prop = prop();
    private final Connection conn = getConnect();


    private Properties prop() {
        Properties properties = new Properties();
        try (InputStream is = DbHelper.class.getClassLoader().getResourceAsStream("application.properties")) {
            properties.load(is);
        } catch(IOException ex) { ex.printStackTrace(); }
        return properties;
    }


    @SneakyThrows
    private Connection getConnect() {
        return DriverManager.getConnection(
                prop.getProperty("spring.datasource.url"),
                prop.getProperty("spring.datasource.username"),
                prop.getProperty("spring.datasource.password")
        );
    }

    @SneakyThrows
    public String getPaymentStatus() {
        var status = "SELECT status FROM payment_entity ORDER BY created DESC";
        return runner.query(conn, status, new ScalarHandler<>());
    }

    @SneakyThrows
    public Integer getPaymentAmount() {
        var amount = "SELECT amount FROM payment_entity ORDER BY created DESC";
        return runner.query(conn, amount, new ScalarHandler<>());
    }

    @SneakyThrows
    public String getCreditRequestStatus() {
        var status = "SELECT status FROM credit_request_entity ORDER BY created DESC";
        return runner.query(conn, status, new ScalarHandler<>());
    }

    @SneakyThrows
    public String getCreditId() {
        var id = "SELECT credit_id FROM order_entity ORDER BY created DESC";
        return runner.query(conn, id, new ScalarHandler<>());
    }
}
