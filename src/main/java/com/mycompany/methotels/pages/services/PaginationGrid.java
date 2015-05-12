/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages.services;

import com.mycompany.methotels.entities.Korisnik;
import com.mycompany.methotels.persistance.KorisnikDao;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.RequestParameter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.util.TextStreamResponse;

/**
 *
 * @author Rados
 */
public class PaginationGrid {
    @Inject
    private KorisnikDao korisnikDao;
    private int size = 5;

    Object onActivate(@RequestParameter("page") int page) {
        Class<?> act = null;
        int sizeOfAll = korisnikDao.ukupanBrojKorisnika();
        List<Korisnik> lista = new ArrayList<>();
        String response = "<thead>\n"
                 +"<tr>"
                + " <th>Ime</th> \n"
                + " <th>Prezime</th> \n"
                + " <th>Username</th> \n"
                + " <th>Password</th> \n"
                + " <th>Rola</th> \n"
                + "</tr>\n"
                + "</thead>\n"
                + "<tbody>\n";
        lista = korisnikDao.getListaKorisnikaOd(page);
        for (Korisnik d : lista) {
            response += (" <tr>\n"
                    + " <td> " + d.getIme() + "</td>\n"
                    + " <td> " + d.getPrezime() + "</td>\n"
                    + " <td> " + d.getUsername() + "</td>\n"
                    + " <td> " + d.getPassword() + "</td>\n"
                    + " <td> " + d.getRola()+ "</td>\n"
                    + " </tr>\n");
        }
        response += "</tbody>\n";
        float ceil = (float) sizeOfAll / (float) size;
        int totalPageSizes = (int) Math.ceil(ceil);
        response += "&";
        for (int i = 1; i <= totalPageSizes; i++) {
            if (page == i) {
                response += ("  <li class='callservice active'><a href='#'>" + i + "</a></li>\n");
            } else {
                response += ("  <li class='callservice'><a href='#'>" + i + "</a></li>\n");
            }
        }
        //response += "</ul>";
        return new TextStreamResponse("text/plain", response);
    }
}
