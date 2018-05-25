/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simo.mi6.project.tier2;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author noemie
 */
@Path("/")
public class ServiceTwitter {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServiceTwitter
     */
    public ServiceTwitter() {
    }

    /**
     * Retrieves representation of an instance of simo.mi6.project.tier2.ServiceTwitter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        return "<test>hello</test>";
    }

    /**
     * PUT method for updating or creating an instance of ServiceTwitter
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
