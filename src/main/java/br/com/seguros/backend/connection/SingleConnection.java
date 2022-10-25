package br.com.seguros.backend.connection;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SingleConnection {

    private static final Logger logger = LogManager.getLogger(SingleConnection.class);

    private static String url = "jdbc:postgresql://localhost:5432/insurance";
    private static String user = "postgres";
    private static String password = "admin";
    private static Connection connection = null;

    static {
        conectar();
    }

    public SingleConnection() {
        conectar();
    }

    static void conectar() {
        try {

            if (connection == null) {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, password);

                logger.info("Conectado ao banco com sucesso.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
