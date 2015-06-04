/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.methotels.restser;

import com.mycompany.methotels.entities.Korisnik;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;

/**
 *
 * @author Rados
 */
@Path("/korisnik")
public interface KorisnikServiceInterface {

    @GET
    @Produces({"application/json"})
    public List<Korisnik> getAll();

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public Korisnik getById(@PathParam("id") Integer id);

    @POST
    @CommitAfter
    @Produces({"application/json"})
    @Consumes({"application/json"})
    public Response post(Korisnik korisnik);

}
