/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.persistance;

import com.mycompany.methotels.entities.Sobe;
import java.util.List;

/**
 *
 * @author Rados
 */
public interface SobeDao {

    public List<Sobe> getListaSoba();

    public Sobe getSobaById(Integer id);

    public void dodajSobu(Sobe soba);

    public void obrisiSobu(int id);
}
