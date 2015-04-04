/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages;

//import com.mycompany.methotels.data.Soba;
import com.mycompany.methotels.entities.Sobe;
import java.util.ArrayList;
import java.util.List;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.hibernate.Session;

/**
 *
 * @author Rados
 */
public class DodajSobe {    
    
    @Property
    private List<Sobe> sobeLista;
    
    @Inject
    private Session session;
    
    @Property
    private Sobe soba;
    
    void onActivate(){
        if(sobeLista == null){
            sobeLista = new ArrayList<Sobe>();
        }    
        // createCriteria metoda pravi Select * upit nad prosle?enom klasom
        sobeLista = (List<Sobe>) session.createCriteria(Sobe.class).list();
    }
    
    @CommitAfter
    Object onSuccess(){
        // persist metoda cuva objekatu bazi podataka
        session.persist(soba);        
        return this;
    }
}
