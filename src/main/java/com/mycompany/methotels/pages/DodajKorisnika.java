/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages;

import com.mycompany.methotels.data.Rola;
import com.mycompany.methotels.entities.Korisnik;
import com.mycompany.methotels.persistance.KorisnikDao;
import com.mycompany.methotels.services.ProtectedPage;
import javax.annotation.security.RolesAllowed;
import org.apache.tapestry5.annotations.Component;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.BeanEditForm;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Rados
 */
@ProtectedPage
@RolesAllowed(value={"Admin"})
public class DodajKorisnika {

    @Inject
    private KorisnikDao korisnikDao;

    @Property
    private Korisnik noviKorisnik;

    @Property
    private Korisnik ulogovanKorisnik;

    @Component
    private BeanEditForm form;

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

    @CommitAfter
    Object onSuccess() {
        if (!korisnikDao.korisnikPostoji(noviKorisnik.getUsername())) {
            String unhashPassword = noviKorisnik.getPassword();
            noviKorisnik.setPassword(getMD5Hash(unhashPassword));
            noviKorisnik.setRola(Rola.Zaposleni);
            Korisnik u = korisnikDao.registrujKorisnika(noviKorisnik);
            ulogovanKorisnik = u;
            return Index.class;
        } else {
            form.recordError("Username koji ste uneli vec postoji");
            return null;
        }
    }

}
