package com.example.hellosunshine;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class jdbc {
    private static final String DATABASE_URl = "jdbc:mysql://localhost:3306/freppos";
    private static final String DATABASE_USERNAME = "root";
    private static final String DATABASE_PASSWORD = "root";
    private static final String SELLECT_QUERY = "SELECT * FROM admin WHERE admin_username = ? AND admin_password = ?";

    public boolean validate(String username, String password) {
        try {
            Connection connection = DriverManager.getConnection(DATABASE_URl, DATABASE_USERNAME, DATABASE_PASSWORD);

            PreparedStatement prepStatment = connection.prepareStatement(SELLECT_QUERY);
            prepStatment.setString(1, username);
            prepStatment.setString(2, password);

            System.out.println(prepStatment);

            ResultSet resultSet = prepStatment.executeQuery();
            if(resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
    }

    public Connection getConnection(){
        try{

            Connection connection = DriverManager.getConnection(DATABASE_URl, DATABASE_USERNAME,DATABASE_PASSWORD);

            return connection;
        }catch(SQLException ex){
            printSQLException(ex);
        }
        return null;
    }

    public static void printSQLException(SQLException ex) {
        for(Throwable e: ex) {
            if(ex instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQL State: " + ((SQLException)e).getSQLState());
                System.err.println("Error code: " + ((SQLException)e).getErrorCode());
                System.err.println("Message: " + ex.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.err.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
