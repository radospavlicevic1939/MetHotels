package com.mycompany.methotels.components;

import com.mycompany.methotels.entities.Korisnik;
import org.apache.tapestry5.annotations.*;

/**
 * Layout component for pages of application test-project.
 */
@Import(module = "bootstrap/collapse", stylesheet = "context:css/style.css")
public class Layout {
    
    @SessionState
    private Korisnik ulogovanKorisnik;
    
    public boolean getLoggedIn() {
        if (ulogovanKorisnik.getUsername() != null) {
            return true;
        }
        return false;
    }

    public void onActionFromLogout() {
        ulogovanKorisnik = null;
    }
}
