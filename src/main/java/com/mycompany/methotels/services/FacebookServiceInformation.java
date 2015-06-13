/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.services;

/**
 *
 * @author Rados
 */
public class FacebookServiceInformation {

    private String actionToken;

    public boolean isLoggedIn() {
        return actionToken != null;
    }

    public String getAccessToken() {
        return actionToken;
    }

    public void setActionToken(String actionToken) {
        this.actionToken = actionToken;
    }

}
