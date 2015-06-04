/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.restser;

import com.mycompany.methotels.entities.Korisnik;
import com.mycompany.methotels.persistance.GenericDao;
import java.util.List;
import javax.ws.rs.core.Response;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Rados
 */
public class KorisnikWebService implements KorisnikServiceInterface {
    
    @Inject
    private GenericDao<Korisnik> genericDao;
    
    @Override
    public List<Korisnik> getAll() {
        return (List<Korisnik>) genericDao.loadAllActive(Korisnik.class);
    }

    @Override
    public Korisnik getById(Integer id) {
        return (Korisnik) genericDao.getElementById(id, Korisnik.class);
    }

    @Override
    public Response post(Korisnik korisnik) {
        genericDao.merge(korisnik);
        return Response.ok().entity(korisnik).build();
    }
    
}
