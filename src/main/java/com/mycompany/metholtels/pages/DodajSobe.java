/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.metholtels.pages;

import com.mycompany.methotels.data.Soba;
import java.util.ArrayList;
import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

/**
 *
 * @author Rados
 */
public class DodajSobe {    
    @Persist
    @Property
    private ArrayList<Soba> sobeLista;
    
    @Property
    private Soba soba;
    
    void onActivate(){
        if(sobeLista == null){
            sobeLista = new ArrayList<Soba>();
        }        
    }
    
    Object onSuccess(){
        sobeLista.add(soba);
        return this;
    }
    
    
}
