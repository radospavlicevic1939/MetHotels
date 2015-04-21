/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.persistance;

import com.mycompany.methotels.entities.Korisnik;
import java.util.ArrayList;

/**
 *
 * @author Rados
 */
public interface KorisnikDao {

    public ArrayList<Korisnik> getListaSvihKorisnika();

    public Korisnik getKorisnikById(Integer id);

    public void dodajKorisnika(Korisnik korisnik);

    public void obrisiKorisnika(Integer id);

    public Korisnik proveriKorisnika(String email, String password);

    public Korisnik registrujKorisnika(Korisnik user);

    public boolean korisnikPostoji(String email);

}
