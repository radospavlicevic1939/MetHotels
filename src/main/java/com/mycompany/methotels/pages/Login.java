/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages;

import com.mycompany.methotels.entities.Korisnik;
import com.mycompany.methotels.persistance.KorisnikDao;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SessionState;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.ioc.annotations.Inject;

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
            return Index.class;
        } else {
            form.recordError("Uneli ste pogrešne parametre. ");
            System.out.println("losi parametri");
            return null;
        }
    }   

}
