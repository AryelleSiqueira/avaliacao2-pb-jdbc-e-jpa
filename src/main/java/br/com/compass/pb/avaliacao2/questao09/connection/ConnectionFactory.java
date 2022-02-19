package br.com.compass.pb.avaliacao2.questao09.connection;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    private static final String HOST = "jdbc:mysql://localhost/shop?useTimeZone=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public DataSource dataSource;

    public ConnectionFactory() {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setJdbcUrl(HOST);
        cpds.setUser(USER);
        cpds.setPassword(PASSWORD);
        cpds.setMaxPoolSize(10);

        this.dataSource = cpds;
    }

    public Connection retrieveConnection() {
        try {
            return this.dataSource.getConnection();
        }
        catch (SQLException e) {
            throw new ConnectionFailedException("Could not establish a connection to the database!");
        }
    }
}
