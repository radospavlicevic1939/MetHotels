/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages;

import com.mycompany.methotels.entities.Rezervacije;
import com.mycompany.methotels.persistance.RezervacijaDao;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Rados
 */
public class ReserveTest {
        
    @Property
    private Rezervacije tmpRezervacija;
    
    @Inject
    private RezervacijaDao rezervacijaDao;
    
    public List<Rezervacije> getRezervacijeList(){
        return rezervacijaDao.getListaRezervacija();
    }
}
