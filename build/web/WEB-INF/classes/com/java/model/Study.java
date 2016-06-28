/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Pinkesh
 */
public class Study implements Serializable {
    private String studyCode;
    private String question;
    private String name;
    private Date dateCreated;
    private String email;
    private String imageURL;
    private String requestedparticipants;
    private String numOfParitipants;
    private String description;
    private String status;
    private List<String> answers;
    private String average;
    private String min;
    private String max;
    private String standardDeviation;

    public Study() {
    }
    
    public Study(String question, String studyCode, String name, Date dateCreated, String email, String imageURL, String requestedparticipants, String numOfParitipants, String description, String status, List<String> answers) {
        this.question = question;
        this.studyCode = studyCode;
        this.name = name;
        this.dateCreated = dateCreated;
        this.email = email;
        this.imageURL = imageURL;
        this.requestedparticipants = requestedparticipants;
        this.numOfParitipants = numOfParitipants;
        this.description = description;
        this.status = status;
        this.answers = answers;
    }

    
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    
    public String getStudyCode() {
        return studyCode;
    }

    public void setStudyCode(String studyCode) {
        this.studyCode = studyCode;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getRequestedparticipants() {
        return requestedparticipants;
    }

    public void setRequestedparticipants(String requestedparticipants) {
        this.requestedparticipants = requestedparticipants;
    }

    public String getNumOfParitipants() {
        return numOfParitipants;
    }

    public void setNumOfParitipants(String numOfParitipants) {
        this.numOfParitipants = numOfParitipants;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
    
    public String getAverage() {
        return average;
    }

    public void setAverage(String average) {
        this.average = average;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getStandardDeviation() {
        return standardDeviation;
    }

    public void setStandardDeviation(String standardDeviation) {
        this.standardDeviation = standardDeviation;
    }
}
