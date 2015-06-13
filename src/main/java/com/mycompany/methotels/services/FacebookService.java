package com.mycompany.methotels.services;

import com.mycompany.methotels.pages.Login;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.FacebookType;
import java.io.IOException;
import net.smartam.leeloo.client.OAuthClient;
import net.smartam.leeloo.client.URLConnectionClient;
import net.smartam.leeloo.client.request.OAuthClientRequest;
import net.smartam.leeloo.client.response.GitHubTokenResponse;
import net.smartam.leeloo.client.response.OAuthAccessTokenResponse;
import net.smartam.leeloo.common.exception.OAuthProblemException;
import net.smartam.leeloo.common.exception.OAuthSystemException;
import net.smartam.leeloo.common.message.types.GrantType;
import org.apache.tapestry5.services.ApplicationStateManager;
import org.apache.tapestry5.services.PageRenderLinkSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rados
 */
public class FacebookService {

    private final PageRenderLinkSource linkSource;
    private final ApplicationStateManager applicationStateManager;
    private final static String SCOPE = "email,public_profile,user_friends";
    private String callBackUrl = null;
    private final String clientId = "430847090450485";
    private final String clientSecret = "b8e82da4e092d2dbad24e657897e6327";

    public FacebookService(PageRenderLinkSource linkSource,
            ApplicationStateManager applicationStateManager) {
        super();
        this.linkSource = linkSource;
        this.applicationStateManager = applicationStateManager;
    }

    public void getUserAccessToken(String code, String token)
            throws IOException, OAuthSystemException,
            OAuthProblemException {
        OAuthClientRequest request = OAuthClientRequest
                .tokenLocation("https://graph.facebook.com/oauth/access_token")
                .setGrantType(GrantType.AUTHORIZATION_CODE)
                .setClientId(clientId).setClientSecret(clientSecret)
                .setRedirectURI(getCallBackUrl()).setCode(code)
                .setParameter("scope", SCOPE).buildBodyMessage();
        OAuthClient oAuthClient = new OAuthClient(new URLConnectionClient());
        OAuthAccessTokenResponse oAuthResponse = oAuthClient.accessToken(
                request, GitHubTokenResponse.class);
        String accessToken = oAuthResponse.getAccessToken();
        FacebookServiceInformation info = getFacebookServiceInformation();
        info.setActionToken(accessToken);

        System.out.println("Aciton token se setuje " + accessToken);
    }

    private FacebookServiceInformation getFacebookServiceInformation() {
        return applicationStateManager.get(FacebookServiceInformation.class);
    }

    public String getCallBackUrl() {
        if (callBackUrl == null) {
            callBackUrl = linkSource.createPageRenderLink(
                    Login.class).toAbsoluteURI()
                    + "/";
        }
        return callBackUrl;
    }

    public String getFacebookAuthentificationLink() throws OAuthSystemException {
        return OAuthClientRequest
                .authorizationLocation(
                        "https://www.facebook.com/dialog/oauth/?")
                .setClientId(clientId).setRedirectURI(getCallBackUrl())
                .setScope(SCOPE).buildQueryMessage().getLocationUri();
    }

    public void publishLink(String link) {
        FacebookClient facebookClient = new DefaultFacebookClient(
                getFacebookServiceInformation().getAccessToken());
        facebookClient.publish("me/feed", FacebookType.class,
                Parameter.with("link", link));
    }
}
