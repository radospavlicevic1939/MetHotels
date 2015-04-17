/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.tapestry5.ioc.annotations.Inject;

/**
 *
 * @author Rados
 */
@Entity
@Table(name = "rezervacije")
@NamedQueries({
    @NamedQuery(name = "Rezervacije.findAll", query = "SELECT r FROM Rezervacije r")})
public class Rezervacije implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "ime")
    private String ime;
    @Basic(optional = false)
    @Column(name = "prezime")
    private String prezime;
    @Basic(optional = false)
    @Column(name = "datum")
    @Temporal(TemporalType.DATE)
    private Date datum;
    @Basic(optional = false)
    @Column(name = "broj_dana")
    private int brojDana;
    @JoinColumn(name = "soba_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Sobe sobaId;

    @Inject
    public Rezervacije() {
    }

    public Rezervacije(Integer id) {
        this.id = id;
    }

    public Rezervacije(Integer id, String ime, String prezime, Date datum, int brojDana) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.datum = datum;
        this.brojDana = brojDana;
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

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getBrojDana() {
        return brojDana;
    }

    public void setBrojDana(int brojDana) {
        this.brojDana = brojDana;
    }

    public Sobe getSobaId() {
        return sobaId;
    }

    public void setSobaId(Sobe sobaId) {
        this.sobaId = sobaId;
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
        if (!(object instanceof Rezervacije)) {
            return false;
        }
        Rezervacije other = (Rezervacije) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.methotels.entities.Rezervacije[ id=" + id + " ]";
    }
    
}
