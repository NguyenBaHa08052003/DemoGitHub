/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAL;

import java.sql.*;
import java.util.Scanner;
import models.Users;

/**
 *
 * @author admin
 */
public class DAO extends DBContext {

    public DAO() {
    }

    public void loadDB() {
        System.out.println("Loading data...");
        String sql = "select * from Users";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String fName = rs.getString("fName");
                String lName = rs.getString("lName");
                System.out.println(id + "-" + fName + "-" + lName);
            }
            rs.close();
        } catch (Exception e) {

        }
    }

    public void insertUsers(Users u) {
        String sql = "insert into Users(fName,lName)\n"
                + "values (?,?)";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setString(1, u.getfName());
            ps.setString(2, u.getlName());

            ps.executeUpdate();

            ps.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchUserById(int id) {
        String sql = "select * from Users\n"
                + "where id = ?";
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String fName = rs.getString("fName");
                String lName = rs.getString("lName");

                System.out.println("fName " + fName + " - " + "lName " + lName);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeUser(int id) {
        String sql = "delete from Users\n"
                + "where id = ?";

        try {
            PreparedStatement ps = connect.prepareStatement(sql);
            ps.setInt(1, id);

            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
        }
    }

    public void updateUsers(int id, Users users) {
        String sql = "UPDATE [dbo].[Users]\n"
                + "   SET [fName] = ?\n"
                + "      ,[lName] = ?\n"
                + " WHERE id = ?";
        
        try {
            PreparedStatement ps = connect.prepareStatement(sql);
        
            ps.setString(1, users.getfName());
            
            ps.setString(2, users.getlName());
            
            ps.setInt(3, id);
            
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        DAO DAO = new DAO();
        DAO.loadDB();
        System.out.println("-----");

//        Users user1 = new Users("HAHAH", "GAGAGAG");
//        DAO.insertUsers(user1);
        Scanner sc = new Scanner(System.in);
//        System.out.println("Nhập ID");
//        int id = sc.nextInt();
//        
//        DAO.searchUserById(id);

        System.out.println("Nhập ID cần update");
        int id = sc.nextInt();
        sc.nextLine();
        String fName = sc.nextLine();
        String lName = sc.nextLine();
        DAO.updateUsers(id, new Users(fName, lName));
        DAO.loadDB();
        System.out.println("-----");
    }
}
