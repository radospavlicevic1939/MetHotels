/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages;

//import com.mycompany.methotels.data.Soba;
import com.mycompany.methotels.entities.Sobe;
import com.mycompany.methotels.persistance.SobeDao;
import com.mycompany.methotels.services.ProtectedPage;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Rados
 */
@ProtectedPage
@RolesAllowed(value={"Admin"})
public class DodajSobe {

    @Property
    private List<Sobe> sobeLista;

    @Inject
    private SobeDao sobeDao;

    @Property
    private Sobe tmpsoba;

    @Property
    private Sobe soba;

    void onActivate() {
        if (sobeLista == null) {
            sobeLista = new ArrayList<Sobe>();
        }
        // createCriteria metoda pravi Select * upit nad prosledjenom klasom
        sobeLista = (List<Sobe>) sobeDao.getListaSoba();
    }

    @CommitAfter
    Object onSuccess() {
        // persist metoda cuva objekat u bazi podataka
        sobeDao.dodajSobu(soba);
        return this;
    }

    @CommitAfter
    Object onDelete(int id) {
        sobeDao.obrisiSobu(id);
        return this;
    }

}
