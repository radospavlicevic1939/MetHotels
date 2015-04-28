/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.pages;

import com.mycompany.methotels.components.GenericEditor;
import com.mycompany.methotels.entities.Korisnik;
import com.mycompany.methotels.services.ProtectedPage;
import javax.annotation.security.RolesAllowed;

/**
 *
 * @author Rados
 */
@ProtectedPage
@RolesAllowed(value={"Admin"})
public class DodajKorisnika extends GenericEditor<Korisnik>{   
}
