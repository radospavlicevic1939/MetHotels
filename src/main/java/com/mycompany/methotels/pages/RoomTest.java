/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages;

import com.mycompany.methotels.entities.Sobe;
import com.mycompany.methotels.persistance.GenericDao;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Rados
 */
public class RoomTest {

    @Inject
    private GenericDao<Sobe> sobaDao;

    @Property
    private Sobe soba;

    void setupRender() {
        soba = sobaDao.getElementById(10, Sobe.class);
    }
    
    public String getSlika(){
        return "images/lux.jpg";
    }
}
