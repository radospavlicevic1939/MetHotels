/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.persistance;

import com.mycompany.methotels.entities.Korisnik;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Rados
 */
public class KorisnikDaoImpl implements KorisnikDao {

    @Inject
    private Session session;

    @Override
    public ArrayList<Korisnik> getListaSvihKorisnika() {
        return (ArrayList<Korisnik>) session.createCriteria(Korisnik.class).list();
    }

    @Override
    public Korisnik getKorisnikById(Integer id) {
        return (Korisnik) session.createCriteria(Korisnik.class).add(Restrictions.eq("id", id)).uniqueResult();
    }
    
    @Override
    public Korisnik getKorisnikByUsername(String username){
        return (Korisnik) session.createCriteria(Korisnik.class).add(Restrictions.eq("username", username)).uniqueResult();
    }

    @Override
    public void dodajKorisnika(Korisnik korisnik) {
        session.persist(korisnik);
    }

    @Override
    public void obrisiKorisnika(Integer id) {
        Korisnik exKorisnik = (Korisnik) session.createCriteria(Korisnik.class).add(Restrictions.eq("id", id)).uniqueResult();
        session.delete(exKorisnik);
    }

    @Override
    public Korisnik proveriKorisnika(String user, String password) {
        try {
            Korisnik k = (Korisnik) session.createCriteria(Korisnik.class).add(Restrictions.eq("username",
                    user)).add(Restrictions.eq("password", password)).uniqueResult();
            if (k != null) {
                return k;
            }
            return null;
        } catch (NullPointerException e) {
            return null;
        }
    }

    @Override
    public Korisnik registrujKorisnika(Korisnik korisnik) {
        return (Korisnik) session.merge(korisnik);
    }

    @Override
    public boolean korisnikPostoji(String user) {
        long rows = (Long) session.createCriteria(Korisnik.class).add(Restrictions.eq("username",
                user)).setProjection(Projections.rowCount()).uniqueResult();
        return (rows != 0);
    }

    @Override
    public List<Korisnik> getListaKorisnikaPoImenu(String ime) {
        return session.createCriteria(Korisnik.class).add(Restrictions.or(Restrictions.ilike("ime", ime + "%"), Restrictions.ilike("prezime", ime + "%"))).list();
    }

    @Override
    public List<Korisnik> getListaKorisnikaOd(int from) {
        int page = (from - 1) * 5;
        List<Korisnik> lista = session.createCriteria(Korisnik.class).setFirstResult(page).setMaxResults(5).addOrder(Order.asc("id")).
                setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
        return lista;
    }

    @Override
    public int ukupanBrojKorisnika() {
        Long l = (Long) session.createCriteria(Korisnik.class).setProjection(Projections.rowCount()).uniqueResult();
        return l.intValue();
    }
}
