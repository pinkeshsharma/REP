/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.dao;

import com.java.data.ConnectionPool;
import com.java.model.Study;
import com.java.util.DBUtil;
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
public class StudyDB {

    public Study getStudy(String studyCode) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "SELECT * from Study WHERE SCode = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, studyCode);
            ResultSet resultSet = ps.executeQuery();
            Study study = new Study();
            if (resultSet.next()) {
                study.setName(resultSet.getString("SName"));
                study.setStudyCode(resultSet.getString("SCode"));
                study.setDescription(resultSet.getString("Description"));
                study.setEmail(resultSet.getString("Email"));
                study.setDateCreated(resultSet.getDate("DateCreated"));
                study.setQuestion(resultSet.getString("Question"));
                study.setImageURL(resultSet.getString("ImageURL"));
                study.setRequestedparticipants(resultSet.getString("ReqParticipants"));
                study.setNumOfParitipants(resultSet.getString("ActParticipants"));
                study.setStatus(resultSet.getString("SStatus"));
            }
            return study;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public Study getStudyDetail(String studyCode) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        Study study = new Study();
        List<String> answers = new ArrayList<>();
        String query = "select SCode, Question, ImageURL from Study where SCode = ?";
        String query1 = "select choice from Answer where SCode = ?";
        try {
            ps = connection.prepareStatement(query);
            ps1 = connection.prepareStatement(query1);
            ps.setString(1, studyCode);
            ps1.setString(1, studyCode);
            ResultSet resultSet = ps.executeQuery();
            ResultSet resultSet1 = ps1.executeQuery();
            if (resultSet.next()) {
                study.setStudyCode(resultSet.getString("SCode"));
                study.setQuestion(resultSet.getString("Question"));
                study.setImageURL(resultSet.getString("ImageURL"));
            }
            while (resultSet1.next()) {
                answers.add(resultSet1.getString("choice"));
            }
            study.setAnswers(answers);
            return study;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public List<Study> getStudies(String email) {
        List<Study> studies = new ArrayList<>();

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "SELECT * from Study WHERE Email = ?";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Study study = new Study();
                study.setName(resultSet.getString("SName"));
                study.setStudyCode(resultSet.getString("SCode"));
                study.setDescription(resultSet.getString("Description"));
                study.setEmail(resultSet.getString("Email"));
                study.setDateCreated(resultSet.getDate("DateCreated"));
                study.setQuestion(resultSet.getString("Question"));
                study.setImageURL(resultSet.getString("ImageURL"));
                study.setRequestedparticipants(resultSet.getString("ReqParticipants"));
                study.setNumOfParitipants(resultSet.getString("ActParticipants"));
                study.setStatus(resultSet.getString("SStatus"));
                studies.add(study);
            }
            return studies;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public List<Study> getStudies() {
        List<Study> studies = new ArrayList<>();

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "SELECT * from Study";

        try {
            ps = connection.prepareStatement(query);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Study study = new Study();
                study.setName(resultSet.getString("SName"));
                study.setStudyCode(resultSet.getString("SCode"));
                study.setDescription(resultSet.getString("Description"));
                study.setEmail(resultSet.getString("Email"));
                study.setDateCreated(resultSet.getDate("DateCreated"));
                study.setQuestion(resultSet.getString("Question"));
                study.setImageURL(resultSet.getString("ImageURL"));
                study.setRequestedparticipants(resultSet.getString("ReqParticipants"));
                study.setNumOfParitipants(resultSet.getString("ActParticipants"));
                study.setStatus(resultSet.getString("SStatus"));
                studies.add(study);
            }
            return studies;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public List<Study> getStudiesParticipate(String email) {
        List<Study> studies = new ArrayList<>();

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;

        String query = "SELECT * from Study WHERE email != ? and SStatus='start' and "
                + "SCode not in (SELECT SCode from Answer WHERE email = ?)";

        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, email);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Study study = new Study();
                study.setName(resultSet.getString("SName"));
                study.setStudyCode(resultSet.getString("SCode"));
                study.setDescription(resultSet.getString("Description"));
                study.setEmail(resultSet.getString("Email"));
                study.setDateCreated(resultSet.getDate("DateCreated"));
                study.setQuestion(resultSet.getString("Question"));
                study.setImageURL(resultSet.getString("ImageURL"));
                study.setRequestedparticipants(resultSet.getString("ReqParticipants"));
                study.setNumOfParitipants(resultSet.getString("ActParticipants"));
                study.setStatus(resultSet.getString("SStatus"));
                studies.add(study);
            }
            return studies;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public int addStudy(Study study) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO Study (SName, SCode, Description, Email, DateCreated, "
                + "Question, ImageURL,ReqParticipants, ActParticipants, SStatus) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, study.getName());
            ps.setString(2, study.getStudyCode());
            ps.setString(3, study.getDescription());
            ps.setString(4, study.getEmail());
            ps.setDate(5, new java.sql.Date(study.getDateCreated().getTime()));
            ps.setString(6, study.getQuestion());
            ps.setString(7, study.getImageURL());
            ps.setString(8, study.getRequestedparticipants());
            ps.setString(9, study.getNumOfParitipants());
            ps.setString(10, study.getStatus());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public int editStudy(Study study) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query;
        if(study.getImageURL() != null){
            query = "UPDATE Study SET "
                + "SName = ?, Question = ?, ReqParticipants = ?, Description = ? , ImageURL = ?  WHERE SCode = ?";
        } else {
            query = "UPDATE Study SET "
                + "SName = ?, Question = ?, ReqParticipants = ?, Description = ? WHERE SCode = ?";
        }

        /**String query
                = "INSERT INTO Study (SName, SCode, Description, Email, DateCreated, "
                + "Question, ImageURL,ReqParticipants, ActParticipants, SStatus) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";**/
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, study.getName());
            ps.setString(2, study.getQuestion());
            ps.setString(3, study.getRequestedparticipants());
            ps.setString(4, study.getDescription());            
            
            if(study.getImageURL() != null){
                ps.setString(5, study.getImageURL());
                ps.setString(6, study.getStudyCode()); 
            } else {
                ps.setString(5, study.getStudyCode());
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
    public int editStatus(Study study) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE Study SET "
                + "SStatus = ? WHERE SCode = ?";

        /**String query
                = "INSERT INTO Study (SName, SCode, Description, Email, DateCreated, "
                + "Question, ImageURL,ReqParticipants, ActParticipants, SStatus) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";**/
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, study.getStatus());           
            ps.setString(2, study.getStudyCode());
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public int submitAnswer(String sCode, String answer, String email){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query
                = "INSERT INTO Answer (Email, Choice, SCode, DateSubmitted) "
                + "VALUES (?, ?, ?, now())";
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, answer);
            ps.setString(3, sCode);
            int status = ps.executeUpdate();
            if(status == 1){
                return updateParticipants(sCode);
            }
            return 0;
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }

    public int updateParticipation(String email){
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE User SET Participation = Participation + 1, Coins = Coins + 1  "
                + "WHERE Email = ?";
        
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
    
    public int updateParticipants(String sCode){
       ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        String query = "UPDATE Study SET ActParticipants = ActParticipants + 1 "
                + "WHERE SCode = ?";
        
        try {
            ps = connection.prepareStatement(query);
            ps.setString(1, sCode);           
            
            return ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            return 0;
        } finally {
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
}
