/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.persistance;

import com.mycompany.methotels.entities.Rezervacije;
import java.util.List;

/**
 *
 * @author Rados
 */
public interface RezervacijaDao {

    public List<Rezervacije> getListaRezervacija();

    public Rezervacije getRezervacijaById(Integer id);

    public void dodajRezervaciju(Rezervacije soba);

    public void obrisiRezervaciju(int id);
}
