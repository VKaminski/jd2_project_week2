package com.gmail.kaminski.viktar.weektwo.repository.connection.impl;

import com.gmail.kaminski.viktar.weektwo.repository.config.DatabaseConfiguration;
import com.gmail.kaminski.viktar.weektwo.repository.connection.ConnectionService;
import com.gmail.kaminski.viktar.weektwo.repository.exception.DocumentRepositoryException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

@Component
public class ConnectionServiceImpl implements ConnectionService {

    private final DatabaseConfiguration configuration;

    @Autowired
    public ConnectionServiceImpl(DatabaseConfiguration configuration) {
        this.configuration = configuration;
        try {
            Class.forName(configuration.getDriver());
        } catch (ClassNotFoundException e) {

        }
    }

    @Override
    public Connection getConnection() {
        try {
            Properties properties = new Properties();
            properties.setProperty("user", configuration.getUsername());
            properties.setProperty("password", configuration.getPassword());
            properties.setProperty("useUnicode", configuration.getUnicode());
            properties.setProperty("characterEncoding", configuration.getEncoding());
            properties.setProperty("serverTimezone", configuration.getTimezone());
            return DriverManager.getConnection(configuration.getUrl(), properties);
        } catch (SQLException e) {
            throw new DocumentRepositoryException("ConnectionServiceImpl - getConnection: ", e);
        }
    }

    @PostConstruct
    public void createDatabaseTable() {
        String sqlRequestDrop = "DROP TABLE IF EXISTS Document";
        String sqlRequestCreate = "CREATE TABLE Document(id BIGINT NOT NULL AUTO_INCREMENT," +
                " unique_number VARCHAR(36) NOT NULL," +
                " description VARCHAR(100) NOT NULL," +
                " deleted TINYINT(1) DEFAULT 0," +
                " PRIMARY KEY (id))";
        try (Connection connection = getConnection()) {
            connection.setAutoCommit(false);
            try (Statement statement = connection.createStatement()) {
                statement.execute(sqlRequestDrop);
                statement.execute(sqlRequestCreate);
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new DocumentRepositoryException("ConnectionServiceImpl - createDatabaseTable: rollback: ", e);
            }
        } catch (SQLException e) {
            throw new DocumentRepositoryException("ConnectionServiceImpl - createDatabaseTable: ", e);
        }

    }
}
