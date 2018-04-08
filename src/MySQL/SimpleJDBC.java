package MySQL;

import java.sql.*;


public class SimpleJDBC {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //Load the JDBC driver
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("Driver loaded");


        //Connect to a database
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/javabook", "Hitagi", "123456");
        System.out.println("Database connected");

        //Create a sql statement
        Statement statement = connection.createStatement();

        //Execute a statement
        ResultSet resultSet = statement.executeQuery("select * from student");

        //Iterate through the result and print the result names
        while (resultSet.next()){
            //show by order using name, age, ssn
            System.out.println(resultSet.getString(1) + '\t' + resultSet.getString(2) + '\t' + resultSet.getString(3));

        }

        //Close connection
        connection.close();



    }
}
