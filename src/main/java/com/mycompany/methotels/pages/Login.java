/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages;

import com.mycompany.methotels.data.Rola;
import com.mycompany.methotels.entities.Korisnik;
import com.mycompany.methotels.persistance.KorisnikDao;
import com.mycompany.methotels.services.FacebookService;
import com.mycompany.methotels.services.FacebookServiceInformation;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import java.io.IOException;
import net.smartam.leeloo.common.exception.OAuthProblemException;
import net.smartam.leeloo.common.exception.OAuthSystemException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.tapestry5.annotations.ActivationRequestParameter;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.tynamo.security.services.SecurityService;

/**
 *
 * @author Rados
 */
@Import(stylesheet = "context:css/style.css")
public class Login {

    @Inject
    private KorisnikDao korisnikDao;

    @Property
    private Korisnik loginKorisnik;

    @SessionState
    private Korisnik ulogovanKorisnik;

    @Component
    private BeanEditForm form;

    @Inject
    private SecurityService securityService;

    @Inject
    private FacebookService facebookService;
    @SessionState
    @Property
    private FacebookServiceInformation facebookServiceInformation;
    @SessionState
    @Property
    private FacebookServiceInformation information;
    @Property
    private com.restfb.types.User userfb;

    @Property
    @ActivationRequestParameter
    private String code;

    Object onActivate() {
        if (ulogovanKorisnik.getUsername() != null) {
            return Index.class;
        }
        return null;
    }

    public String getMD5Hash(String yourString) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(yourString.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception e) {
            return "";
        }
    }

    Object onSuccessFromForm() {
        //String password = getMD5Hash(loginKorisnik.getPassword());
        //System.out.println(password);
        Korisnik k = korisnikDao.proveriKorisnika(loginKorisnik.getUsername(), loginKorisnik.getPassword());
        if (k != null) {
            ulogovanKorisnik = k;
            Subject currentUser = securityService.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(k.getUsername(),
                    loginKorisnik.getPassword());
            try {
                currentUser.login(token);
            } catch (Exception e) {
                form.recordError("Uneli ste pogrešne parametre");
            }
            return Index.class;
        } else {
            form.recordError("Uneli ste pogrešne parametre. ");
            System.out.println("losi parametri");
            return null;
        }
    }

    public String getFacebookAuthentificationLink() throws OAuthSystemException {
        return facebookService.getFacebookAuthentificationLink();
    }

    @CommitAfter
    public boolean getLoggedInFb() {
        if (facebookServiceInformation.getAccessToken() != null) {
            Korisnik fbuser = new Korisnik(userfb.getEmail(), " ", Rola.Zaposleni, userfb.getId());
            Korisnik exist = null;
            System.out.println("proverava");
            if(korisnikDao.korisnikPostoji(userfb.getId())){
                exist = korisnikDao.getKorisnikByUsername(userfb.getId());
            }            
            if (exist == null) {
                ulogovanKorisnik = korisnikDao.registrujKorisnika(fbuser);
                System.out.println("registruje");
            } else {
                ulogovanKorisnik = exist;
                System.out.println("postoji");
            }
        }
        return facebookServiceInformation.getAccessToken() != null;
    }

    @SetupRender
    public void setup() throws IOException, OAuthSystemException,
            OAuthProblemException {
        if (code != null) {
            facebookService.getUserAccessToken(code,
                    information.getAccessToken());
        }
        code = null;
        FacebookClient facebookClient = new DefaultFacebookClient(information.getAccessToken());
        if (information.isLoggedIn()) {
            userfb = facebookClient.fetchObject("me", com.restfb.types.User.class);
        }
    }

}
