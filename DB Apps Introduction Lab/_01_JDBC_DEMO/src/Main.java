package com.company;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username default (root): ");
        String user = sc.nextLine();
        user = user.equals("") ? "root" : user;
        System.out.println();

        System.out.print("Enter password default (empty):");
        String password = sc.nextLine().trim();
        System.out.println();

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);

        Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/diablo", props);

        PreparedStatement stmt =
                connection.prepareStatement("SELECT user_name,CONCAT_WS(\" \",first_name,last_name) AS full_name,COUNT(*) AS count FROM users AS u " +
                        "JOIN users_games AS us ON us.user_id = u.id " +
                        "WHERE user_name = ? " +
                        "GROUP BY user_name,first_name,last_name;");

        String username = sc.nextLine();
        stmt.setString(1, username);
        ResultSet rs = stmt.executeQuery();


        if(rs.next()){
            System.out.printf("User: %s\n",username);
            System.out.printf("%s has played %d games",rs.getString("full_name"),rs.getInt("count"));
        } else {
            System.out.println("No such user exists");
        }

        connection.close();
    }
}