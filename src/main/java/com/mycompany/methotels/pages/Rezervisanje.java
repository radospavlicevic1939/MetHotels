/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages;

import com.mycompany.methotels.entities.Rezervacije;
import com.mycompany.methotels.entities.Sobe;
import com.mycompany.methotels.persistance.RezervacijaDao;
import com.mycompany.methotels.persistance.SobeDao;
import java.util.List;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.services.SelectModelFactory;

/**
 *
 * @author Rados
 */
public class Rezervisanje {

    @Inject
    private RezervacijaDao rezervacijaDao;

    @Inject
    private SobeDao sobeDao;

    @Inject
    private SelectModelFactory smf;

    @Property
    private Rezervacije rezervacija;

    @Property
    private Rezervacije tmprezervacija;

    @Property
    private List<Rezervacije> rezervacijeLista;

    @Property
    private List<Sobe> sobeLista;

    @Property
    private Sobe soba;

    public void setupRender() {
        rezervacijeLista = rezervacijaDao.getListaRezervacija();
    }

    public SelectModel getSobaModel() {
        sobeLista = sobeDao.getListaSoba();
        return smf.create(sobeLista, "ime");
    }

    @CommitAfter
    void onSuccessFromRezervacijeForma() {
        rezervacija.setSobaId(soba);
        rezervacijaDao.dodajRezervaciju(rezervacija);
    }

    @CommitAfter
    void onDelete(Integer id) {
        rezervacijaDao.obrisiRezervaciju(id);
    }

    public String getSobaIme() {
        if (tmprezervacija.getSobaId() != null) {
            return tmprezervacija.getSobaId().getIme();
        } else {
            return "";
        }
    }
}
