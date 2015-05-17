/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.persistance;

import com.mycompany.methotels.entities.Rezervacije;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Rados
 */
public class RezervacijaDaoImpl implements RezervacijaDao {

    @Inject
    private Session session;

    @Override
    public List<Rezervacije> getListaRezervacija() {
        return session.createCriteria(Rezervacije.class).list();
    }

    @Override
    public Rezervacije getRezervacijaById(Integer id) {
        return (Rezervacije) session.createCriteria(Rezervacije.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void dodajRezervaciju(Rezervacije soba) {
        session.persist(soba);
    }
    
    @Override
    public Rezervacije dodajIliUpdatujRezervaciju(Rezervacije rezervacija){
        return (Rezervacije) session.merge(rezervacija);
    }

    @Override
    public void obrisiRezervaciju(int id) {
        Rezervacije exRezervacija = (Rezervacije) session.createCriteria(Rezervacije.class).add(Restrictions.eq("id", id)).uniqueResult();
        session.delete(exRezervacija);
    }

}
