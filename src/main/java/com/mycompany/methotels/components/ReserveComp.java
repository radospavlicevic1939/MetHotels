/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.components;

import com.mycompany.methotels.entities.Rezervacije;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;

/**
 *
 * @author Rados
 */
@Import(stylesheet = "context:css/style.css")
public class ReserveComp {
    
    @Parameter
    Rezervacije rezervacija;
        
    public String getIme(){
        return rezervacija.getIme() + " " + rezervacija.getPrezime();
    }
    
    public String getSoba(){
        return rezervacija.getSobaId().getIme() + "(" + rezervacija.getBrojDana() +  " dana)";
    }
    
    public String getCena(){
        return "" + (rezervacija.getBrojDana() * rezervacija.getSobaId().getCena()) + "E";
    }
}
