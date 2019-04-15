package com.gmail.kaminski.viktar.weektwo.repository.impl;

import com.gmail.kaminski.viktar.weektwo.repository.DocumentRepository;
import com.gmail.kaminski.viktar.weektwo.repository.connection.ConnectionService;
import com.gmail.kaminski.viktar.weektwo.repository.exception.DocumentRepositoryException;
import com.gmail.kaminski.viktar.weektwo.repository.model.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.*;

@Repository
public class DocumentRepositoryImpl implements DocumentRepository {
    private final ConnectionService connectionService;

    @Autowired
    public DocumentRepositoryImpl(ConnectionService connectionService) {
        this.connectionService = connectionService;
    }

    @Override
    public Document add(Document document) {
        String sqlRequest = "INSERT INTO Document(unique_number, description) VALUES (?,?)";
        try (Connection connection = connectionService.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, document.getUuid());
                preparedStatement.setString(2, document.getDescription());
                if (preparedStatement.executeUpdate() == 0) {
                    throw new DocumentRepositoryException("DocumentRepository: add: Document hasn't added!");
                }
                try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                    if (resultSet.next()) {
                        document.setId(resultSet.getLong(1));
                        connection.commit();
                    }
                } catch (SQLException e) {
                    connection.rollback();
                    throw new DocumentRepositoryException("", e);
                }
            }
        } catch (SQLException e) {
            throw new DocumentRepositoryException("DocumentRepository: add: ", e);
        }
        return document;
    }

    @Override
    public Document get(Long id) {
        String sqlRequest = "SELECT * FROM Document WHERE deleted = 0 AND id=?";
        try (Connection connection = connectionService.getConnection()) {
            connection.setAutoCommit(false);
            Document document = null;
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
                preparedStatement.setLong(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        document = get(resultSet);
                        connection.commit();
                    }
                } catch (SQLException e) {
                    connection.rollback();
                    throw new DocumentRepositoryException("DocumentRepository: get rollback: ", e);
                }
            }
            return document;
        } catch (SQLException e) {
            throw new DocumentRepositoryException("DocumentRepository: get: ", e);
        }
    }

    @Override
    public void delete(Long id) {
        String sqlRequest = "UPDATE Document SET deleted = 1 WHERE id = ?";
        try (Connection connection = connectionService.getConnection()) {
            connection.setAutoCommit(false);
            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlRequest)) {
                preparedStatement.setLong(1, id);
                int deletedRows = preparedStatement.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw new DocumentRepositoryException("DocumentRepository: delete rollback: ", e);
            }
        } catch (SQLException e) {
            throw new DocumentRepositoryException("DocumentRepository: delete: ", e);
        }
    }


    private Document get(ResultSet resultSet) throws SQLException {
        Document document = new Document();
        try {
            Long id = resultSet.getLong("id");
            String uuid = resultSet.getString("unique_number");
            String description = resultSet.getString("description");
            document.setId(id);
            document.setUuid(uuid);
            document.setDescription(description);
            return document;
        } catch (SQLException e) {
            String errorMessage = "Exception: DocumentRepository private get";
            throw new SQLException(errorMessage, e);
        }
    }


}
