/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simo.mi6.project.tier2;

import java.rmi.Naming;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import simo.mi6.project.tier3.TwitterDBService;

/**
 * REST Web Service
 *
 * @author noemie
 */
@Path("/")
public class ServiceTwitter {

    @Context
    private UriInfo context;
    private TwitterDBService service;

    /**
     * Creates a new instance of ServiceTwitter
     */
    public ServiceTwitter() throws Exception {
        // Appel au service RMI
        System.setProperty("java.rmi.server.hostname", "86.76.4.24");        
        service = (TwitterDBService) Naming.lookup("rmi://86.76.4.24:3200/TwitterDBService");
    }

    /**
     * Retrieves representation of an instance of simo.mi6.project.tier2.ServiceTwitter
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() throws Exception {        
        return "<test>Hello</test>";
    }
    
    /***
     * Récupérer la liste des users
     * @return
     * @throws Exception 
     */
    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_XML)
    public String getUsers() throws Exception {
        List<String> users = service.getAllUsers();
        
        String message = "<users>";
        for(int i = 0; i < users.size(); i++) {
            message += ("<user>" + users.get(i) + "</user>");
        }
        message += "</users>";
        
        return message;
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
