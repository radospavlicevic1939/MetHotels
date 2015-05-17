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
import com.mycompany.methotels.services.ProtectedPage;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.apache.tapestry5.SelectModel;
import org.apache.tapestry5.annotations.InjectComponent;
import org.apache.tapestry5.annotations.OnEvent;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.corelib.components.Zone;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.Request;
import org.apache.tapestry5.services.SelectModelFactory;
import org.got5.tapestry5.jquery.components.InPlaceEditor;

/**
 *
 * @author Rados
 */
@ProtectedPage
@RolesAllowed(value={"Admin"})
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

    @InjectComponent
    private Zone zoneGrid;

    @Inject
    private Request request;      

    public void setupRender() {
        rezervacijeLista = rezervacijaDao.getListaRezervacija();
    }

    public SelectModel getSobaModel() {
        sobeLista = sobeDao.getListaSoba();
        return smf.create(sobeLista, "ime");
    }

    @CommitAfter
    Object onSuccessFromRezervacijeForma() {
        rezervacija.setSobaId(soba);
        rezervacijaDao.dodajRezervaciju(rezervacija);
        rezervacijeLista = rezervacijaDao.getListaRezervacija();
        return request.isXHR() ? zoneGrid.getBody() : null;
    }

    @CommitAfter
    Object onDelete(Integer id) {
        rezervacijaDao.obrisiRezervaciju(id);
        rezervacijeLista = rezervacijaDao.getListaRezervacija();
        return request.isXHR() ? zoneGrid.getBody() : null;
    }

    public String getSobaIme() {
        if (tmprezervacija.getSobaId() != null) {
            return tmprezervacija.getSobaId().getIme();
        } else {
            return "";
        }
    }
    
    @CommitAfter
    @OnEvent(component = "ime", value = InPlaceEditor.SAVE_EVENT)
    void changeIme(Long id, String value) {
        Rezervacije reserve = rezervacijaDao.getRezervacijaById(id.intValue());
        reserve.setIme(value);
        rezervacijaDao.dodajIliUpdatujRezervaciju(reserve);
    }
    
    @CommitAfter
    @OnEvent(component = "prezime", value = InPlaceEditor.SAVE_EVENT)
    void changePrezime(Long id, String value) {
        Rezervacije reserve = rezervacijaDao.getRezervacijaById(id.intValue());
        reserve.setPrezime(value);
        rezervacijaDao.dodajIliUpdatujRezervaciju(reserve);
    }
    
    @CommitAfter
    @OnEvent(component = "brojDana", value = InPlaceEditor.SAVE_EVENT)
    void setChangeBrojDana(Long id, Integer value) {
        Rezervacije reserve = rezervacijaDao.getRezervacijaById(id.intValue());
        reserve.setBrojDana(value);
        rezervacijaDao.dodajIliUpdatujRezervaciju(reserve);
    }
    
     public JSONObject getOptionsJSON() {
        JSONObject params = new JSONObject();
        params.put("tooltip", "Promena vrednosti");
        params.put("height", "25px");
        params.put("width", "80px");

        return params;
    }        
}
