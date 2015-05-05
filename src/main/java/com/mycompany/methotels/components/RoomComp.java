package com.mycompany.methotels.components;

import com.mycompany.methotels.entities.Sobe;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rados
 */
@Import(stylesheet = "context:css/style.css")
public class RoomComp {

    @Parameter
    private Sobe soba;

    @Parameter
    @Property
    private String slika;

    public String getNazivSobe() {
        return soba.getIme();
    }
    
    public String getCena(){
        return String.valueOf(soba.getCena());
    }
}
