package doranco.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;

public final class DorancoMeriseDB {

    private static DorancoMeriseDB INSTANCE;

    private String user;
    private String password;
    private String hostname;
    private String port;
    private String database;

    private DorancoMeriseDB() {
        ResourceBundle rb = ResourceBundle.getBundle("fr.doranco.jdbc.model.db.merise.database_file");
        this.user = rb.getString("user");
        this.password = rb.getString("password");
        this.hostname = rb.getString("hostname");
        this.port = rb.getString("port");
        this.database = rb.getString("database");
    }

    public static DorancoMeriseDB getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new DorancoMeriseDB();
        }
        return INSTANCE;
    }

    private String getConnectionUrl() {
        String url = String.format("jdbc:mysql://%s:%s/%s", hostname, port, database);

        return url;
    }

    public Connection getConnection() throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        Class.forName(driver);

        return DriverManager.getConnection(getConnectionUrl(), this.user, this.password);
    }
}
