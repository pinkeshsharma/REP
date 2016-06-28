/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.dao;

import com.java.data.ConnectionPool;
import com.java.model.User;
import com.java.util.DBUtil;
import com.java.util.HashUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Pinkesh
 */
public class UserDB {

    ConnectionPool pool;
    Connection connection;

    public UserDB() {
        pool = ConnectionPool.getInstance();

    }

    public User getUser(String email, String password) {
        PreparedStatement ps = null;
        connection = pool.getConnection();
        User user = null;
        String pass;
        String salt;
        String query = "SELECT UName, Email, Coins, Studies, Participation, Password, refferalCode, salt,(select COALESCE(sum(ActParticipants) , 0 ) from Study where Email=?) as participants FROM User WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, email);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                pass = resultSet.getString("Password");
                salt = resultSet.getString("salt");
                String passwordHex = HashUtil.getHash(password, salt);
                if (pass.equals(passwordHex) || pass.equals(password)) {
                    user = new User(resultSet.getString("UName"), resultSet.getString("Email"), resultSet.getString("Coins"), resultSet.getString("Studies"), resultSet.getString("Participation"), resultSet.getString("participants"), resultSet.getString("refferalCode"));
                } else {
                    return user;
                }
            } else {
                return user;
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return user;
    }

    public List<User> getUsers() {
        return new ArrayList<User>() {
        };
    }

    public int createUser(String name, String email, String password, String salt, String refferalCodeUser, String actToken) {
        PreparedStatement ps = null;
        connection = pool.getConnection();
        String query
                = "INSERT INTO TempUser (UName, Email, Password, IssueDate, Salt, ActToken, refferalCode) "
                + "VALUES (?, ?, ?, now(), ?, ?, ?)";
        try {

            ps = connection.prepareStatement(query);
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.setString(4, salt);
            ps.setString(5, actToken);
            ps.setString(6, refferalCodeUser);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public int activateUser(String token) {
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        connection = pool.getConnection();
        String email = "";
        String fetchQuery = "Select * from TempUser where ActToken=?";
        String insertQuery = "INSERT INTO User (UName,Email,password,Studies,Participation,Coins,salt,token,refferalCode,peopleReffered) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(fetchQuery);
            ps.setString(1, token);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                email = resultSet.getString("Email");
                ps1 = connection.prepareStatement(insertQuery);
                ps1.setString(1, resultSet.getString("UName"));
                ps1.setString(2, email);
                ps1.setString(3, resultSet.getString("Password"));
                ps1.setString(4, "0");
                ps1.setString(5, "0");
                ps1.setString(6, "0");
                ps1.setString(7, resultSet.getString("Salt"));
                ps1.setString(8, "");
                ps1.setString(9, resultSet.getString("refferalCode"));
                ps1.setString(10, "0");
                return ps1.executeUpdate();
            } else {
                return 10;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            deleteTempUser(email);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public boolean emailExists(String email) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        connection = pool.getConnection();
        String query = "SELECT Email FROM User "
                + "WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public int deleteTempUser(String email) {
        PreparedStatement ps = null;
        connection = pool.getConnection();
        String query = "DELETE FROM TempUser where Email=?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public User getUser(String email) {
        PreparedStatement ps = null;
        connection = pool.getConnection();
        User user = null;
        String query = "SELECT UName, Email, Coins, Studies, Participation, (select  COALESCE(sum(ActParticipants) , 0 ) from Study where Email=?) as participants FROM User WHERE Email = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, email);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                user = new User(resultSet.getString("UName"), resultSet.getString("Email"), resultSet.getString("Coins"), resultSet.getString("Studies"), resultSet.getString("Participation"), resultSet.getString("participants"));
            } else {
                return user;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
        return user;
    }

    public int updateReferral(String refferalCode) {
        PreparedStatement ps = null;
        connection = pool.getConnection();
        String refferarsEmail = getEmail("refferalCode", refferalCode);
        String query
                = "UPDATE User set Coins = Coins+2 , set peopleReffered = peopleReffered +1 where Email = ? ";
        try {

            ps = connection.prepareStatement(query);
            ps.setString(1, refferarsEmail);

            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public String getEmail(String param, String value) {
        PreparedStatement ps = null;
        connection = pool.getConnection();

        String query = "SELECT Email FROM User WHERE ? = ?";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, param);
            ps.setString(2, value);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("Email");
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

}
