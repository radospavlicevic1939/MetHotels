/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.persistance;

import com.mycompany.methotels.entities.Sobe;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Rados
 */
public class SobeDaoImpl implements SobeDao {

    @Inject
    private Session session;

    @Override
    public List<Sobe> getListaSoba() {
        List<Sobe> listaSoba = session.createCriteria(Sobe.class).list();
        if (listaSoba == null) {
            return new ArrayList<Sobe>();
        }
        return listaSoba;
    }

    @Override
    public Sobe getSobaById(Integer id) {
        return (Sobe) session.createCriteria(Sobe.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public void dodajSobu(Sobe soba) {
        session.persist(soba);
    }

    @Override
    public void obrisiSobu(int id) {
        Sobe exSoba = (Sobe) session.createCriteria(Sobe.class).add(Restrictions.eq("id", id)).uniqueResult();
        session.delete(exSoba);
    }

}
