/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Rados
 */
@Entity
@Table(name = "sobe")
@NamedQueries({
    @NamedQuery(name = "Sobe.findAll", query = "SELECT s FROM Sobe s")})
public class Sobe extends AbstractEntity implements Serializable {
    private static final long serialVersionUID = 1L;
   
    @Basic(optional = false)
    @Column(name = "ime")
    private String ime;
    @Basic(optional = false)
    @Column(name = "sprat")
    private int sprat;
    @Basic(optional = false)
    @Column(name = "tv")
    private boolean tv;
    @Basic(optional = false)
    @Column(name = "internet")
    private boolean internet;
    @Basic(optional = false)
    @Column(name = "djakuzi")
    private boolean djakuzi;
    @Basic(optional = false)
    @Column(name = "cena")
    private Integer cena;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "sobaId")
    private List<Rezervacije> rezervacijeList;

    @Inject
    public Sobe() {
    }

    public Sobe(Integer id) {
        this.id = id;
    }

    public Sobe(Integer id, String ime, int sprat, boolean tv, boolean internet, boolean djakuzi) {
        this.id = id;
        this.ime = ime;
        this.sprat = sprat;
        this.tv = tv;
        this.internet = internet;
        this.djakuzi = djakuzi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public int getSprat() {
        return sprat;
    }

    public void setSprat(int sprat) {
        this.sprat = sprat;
    }

    public boolean getTv() {
        return tv;
    }

    public void setTv(boolean tv) {
        this.tv = tv;
    }

    public boolean getInternet() {
        return internet;
    }

    public void setInternet(boolean internet) {
        this.internet = internet;
    }

    public boolean getDjakuzi() {
        return djakuzi;
    }

    public void setDjakuzi(boolean djakuzi) {
        this.djakuzi = djakuzi;
    }

    public Integer getCena() {
        return cena;
    }

    public void setCena(Integer cena) {
        this.cena = cena;
    }        

    public List<Rezervacije> getRezervacijeList() {
        return rezervacijeList;
    }

    public void setRezervacijeList(List<Rezervacije> rezervacijeList) {
        this.rezervacijeList = rezervacijeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sobe)) {
            return false;
        }
        Sobe other = (Sobe) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.methotels.entities.Sobe[ id=" + id + " ]";
    }
    
}
