/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages.services;

import com.mycompany.methotels.persistance.KorisnikDao;
import com.mycompany.methotels.entities.Korisnik;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.util.TextStreamResponse;

/**
 *
 * @author Rados
 */
public class FindUser {

    @Inject
    private Request request;
    @Property
    private List<Korisnik> korisnikLista;
    @Property
    private Korisnik korisnik;
    @Inject
    private KorisnikDao korisnikDao;

    Object onActivate(@RequestParameter("ime") String ime) {
        if (korisnikLista == null) {
            korisnikLista = new ArrayList<>();
        }
        String response = "<thead>"
                +"<tr>"
                + " <th>Ime</th> \n"
                + " <th>Prezime</th> \n"
                + " <th>Username</th> \n"
                + " <th>Password</th> \n"
                + " <th>Rola</th> \n"
                + "</tr>\n"
                + "</thead>\n"
                + "<tbody>\n";
        korisnikLista = korisnikDao.getListaKorisnikaPoImenu(ime);
        for (Korisnik d : korisnikLista) {
            response += (" <tr>\n"
                    + " <td> " + d.getIme() + "</td>\n"
                    + " <td> " + d.getPrezime() + "</td>\n"
                    + " <td> " + d.getUsername() + "</td>\n"
                    + " <td> " + d.getPassword() + "</td>\n"
                    + " <td> " + d.getRola()+ "</td>\n"
                    + " </tr>");
        }
        response += "</tbody>";
        return new TextStreamResponse("text/plain", response);
    }
}
