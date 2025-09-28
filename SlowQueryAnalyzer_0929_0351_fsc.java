// 代码生成时间: 2025-09-29 03:51:29
package com.example.monitoring;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * SlowQueryAnalyzer is a managed bean used for slow query analysis.
 * It provides methods to execute queries and analyze their execution time.
 */
@ManagedBean
@RequestScoped
public class SlowQueryAnalyzer {

    private List<QueryExecution> queryExecutions;
    private String databaseUrl;
    private String databaseUser;
    private String databasePassword;

    /**
     * Constructor for SlowQueryAnalyzer. Initializes the list of query executions.
     */
    public SlowQueryAnalyzer() {
        this.queryExecutions = new ArrayList<>();
    }

    /**
     * Analyzes the execution time of the given SQL query.
     *
     * @param query The SQL query to be executed.
     * @return QueryExecution The object containing query information and execution time.
     * @throws SQLException If a database access error occurs.
     */
    public QueryExecution analyzeQuery(String query) throws SQLException {
        long startTime = System.currentTimeMillis();
        QueryExecution queryExecution = executeQuery(query);
        long endTime = System.currentTimeMillis();

        queryExecution.setExecutionTime(endTime - startTime);
        this.queryExecutions.add(queryExecution);

        return queryExecution;
    }

    /**
     * Executes the given SQL query and returns a QueryExecution object with details.
     *
     * @param query The SQL query to be executed.
     * @return QueryExecution The object containing query information and execution details.
     * @throws SQLException If a database access error occurs.
     */
    private QueryExecution executeQuery(String query) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // Establish database connection
            connection = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);
            statement = connection.createStatement();

            // Execute query and retrieve result set
            resultSet = statement.executeQuery(query);

            // Create a new QueryExecution object with query details
            QueryExecution queryExecution = new QueryExecution(query, resultSet);
            return queryExecution;

        } finally {
            // Close resources to prevent memory leaks
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
    }

    /**
     * Returns the list of query executions.
     *
     * @return List<QueryExecution> The list of query executions.
     */
    public List<QueryExecution> getQueryExecutions() {
        return queryExecutions;
    }

    /**
     * Sets the database URL.
     *
     * @param databaseUrl The database URL to be set.
     */
    public void setDatabaseUrl(String databaseUrl) {
        this.databaseUrl = databaseUrl;
    }

    /**
     * Sets the database user.
     *
     * @param databaseUser The database user to be set.
     */
    public void setDatabaseUser(String databaseUser) {
        this.databaseUser = databaseUser;
    }

    /**
     * Sets the database password.
     *
     * @param databasePassword The database password to be set.
     */
    public void setDatabasePassword(String databasePassword) {
        this.databasePassword = databasePassword;
    }

    /**
     * Represents a query execution with details.
     */
    public static class QueryExecution {
        private String query;
        private ResultSet resultSet;
        private long executionTime;

        public QueryExecution(String query, ResultSet resultSet) {
            this.query = query;
            this.resultSet = resultSet;
        }

        public String getQuery() {
            return query;
        }

        public ResultSet getResultSet() {
            return resultSet;
        }

        public long getExecutionTime() {
            return executionTime;
        }

        public void setExecutionTime(long executionTime) {
            this.executionTime = executionTime;
        }
    }
}