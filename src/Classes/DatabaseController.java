package Classes;

import javafx.scene.layout.Pane;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseController {
    private java.sql.Connection connection;
    private String hostname;
    private String port;
    private String database;
    private String username;
    private String password;

    public DatabaseController(){
        this.hostname = "localhost";
        this.port = "3306";
        this.database = "";
        this.username = "root";
        this.password = "";
    }

    public DatabaseController(String database, String username, String password){
        this.hostname = "localhost";
        this.port = "3306";
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public DatabaseController(String hostname, String database, String username, String password){
        this.hostname = hostname;
        this.port = "3306";
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public DatabaseController(String hostname, String port, String database, String username, String password){
        this.hostname = hostname;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    public ResultSet query(String query) throws SQLException {
        if(!this.isConnected()) {
            this.connect();
        }

        Statement statement = this.connection.createStatement();

        return statement.executeQuery(query);
    }

    public ResultSet preparedStatement(String sql, ArrayList parameters) throws SQLException {
        if(!this.isConnected()) {
            this.connect();
        }

        PreparedStatement ps = this.connection.prepareStatement(sql);
        for(int i = 1; i <= parameters.size(); i++) {
            ps.setObject(i, parameters.get(i-1));
        }

        ps.execute();
        return ps.getResultSet();
    }

    private boolean connect(){
        String url = "jdbc:mysql://"+this.hostname+":"+this.port+"/"+this.database;
        try {
            this.connection = DriverManager.getConnection(url, this.username, this.password);
            return true;
        } catch(SQLException ex) {
            System.out.println(ex);
            this.connection = null;
            System.out.println("Couldn't connect");
            return false;
        }
    }

    private boolean isConnected() throws SQLException {
        if(this.connection == null || this.connection.isClosed() == false){
            return false;
        }
        return true;
    }

    private void disconnect(){
        try{
            this.connection.close();
        }catch(Exception ex) {
            this.connection = null;
        }
        this.connection = null;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
