/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.model;

import java.io.Serializable;

/**
 *
 * @author Pinkesh
 */
public class User implements Serializable {

    private String name;
    private String email;
    private String coins;
    private String studies;
    private String participants;
    private String participation;
    private String referralCode;

    public User() {

    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public User(String name, String email, String coins, String studies, String participation, String participants) {
        this.name = name;
        this.email = email;
        this.coins = coins;
        this.studies = studies;
        this.participation = participation;
        this.participants = participants;
    }

    public User(String name, String email, String coins, String studies, String participation, String participants, String referralCode) {
        this.name = name;
        this.email = email;
        this.coins = coins;
        this.studies = studies;
        this.participants = participants;
        this.participation = participation;
        this.referralCode = referralCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCoins() {
        return coins;
    }

    public void setCoins(String coins) {
        this.coins = coins;
    }

    public String getStudies() {
        return studies;
    }

    public void setStudies(String studies) {
        this.studies = studies;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }

    public String getParticipation() {
        return participation;
    }

    public void setParticipation(String participation) {
        this.participation = participation;
    }

    public String getReferralCode() {
        return referralCode;
    }

    public void setReferralCode(String referralCode) {
        this.referralCode = referralCode;
    }
}
