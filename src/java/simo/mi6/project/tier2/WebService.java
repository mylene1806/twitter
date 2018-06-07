/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simo.mi6.project.tier2;

import common.User;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBElement;
import simo.mi6.project.tier3.TwitterDBService;

/**
 * REST Web Service
 *
 * @author noemie
 */
@Path("/")
public class WebService {

    @Context
    private UriInfo context;
    private TwitterDBService service;

    /**
     * Creates a new instance of ServiceTwitter
     */
    public WebService() throws Exception {
        // Appel au service RMI
        System.setProperty("java.rmi.server.hostname", "86.76.4.24");        
        service = (TwitterDBService) Naming.lookup("rmi://86.76.4.24:3200/TwitterDBService");
    }
    
    /***
     * Récupérer la liste des users
     * @return
     * @throws Exception 
     */
    @GET
    @Path("users")
    @Produces("text/plain")
    public List<String> getUsers() throws Exception {
        return service.getAllUsers();
    }
    
    /**
     * Insérer un nouveau user
     * @param u
     * @throws RemoteException 
     */
    @PUT
    @Path("create")
    @Consumes(MediaType.APPLICATION_XML)
    public void createNewUser(JAXBElement<User> u) throws RemoteException
    {
        User user = u.getValue();
        String username = user.getUsername();
        String password = user.getPassword();
        
        service.createNewUser(username, password);
    }
    
    /**
     * Vérifie que le user est bien enregistré
     * @param u
     * @return
     * @throws RemoteException 
     */
    @PUT
    @Path("connect")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces("text/plain")
    public String isUserPasswordCorrect(JAXBElement<User> u) throws RemoteException
    {
        User user = u.getValue();
        String username = user.getUsername();
        String password = user.getPassword();
        
        return Boolean.toString(service.isUserPasswordCorrect(username, password));
    }
    
    /**
     * Supprimer un user
     * @param u
     * @throws RemoteException 
     */
    @DELETE
    @Path("remove")
    @Consumes(MediaType.APPLICATION_XML)
    @Produces("text/plain")
    public void removeUser(JAXBElement<User> u) throws RemoteException
    {
        User user = u.getValue();
        String username = user.getUsername();
        
        service.removeUser(username);
    }
    
    /**
     * Récupérer les followers d'un user
     * @param username
     * @return
     * @throws RemoteException 
     */
    @GET
    @Path("followers/{user}")
    @Produces("text/plain")
    public List<String> getUsersFollowing(@PathParam("user") String username) throws RemoteException
    {        
        return service.getUsersFollowing(username);
    }
    
    /**
     * Récupérer les users suivis par un user
     * @param username
     * @return
     * @throws RemoteException 
     */
    @GET
    @Path("followedBy/{user}")
    @Produces("text/plain")
    public List<String> getUsersFollowedBy(@PathParam("user") String username) throws RemoteException
    {     
        return service.getUsersFollowedBy(username);
    }
    
    @GET
    @Path("{follower}/follow/{followed}")
    public void startFollowing(@PathParam("follower") String follower, @PathParam("followed") String followed) throws RemoteException
    {
        service.startFollowing(follower, followed);
    }
    
    @GET
    @Path("{follower}/stopFollow/{followed}")
    public void stopFollowing(@PathParam("follower") String follower, @PathParam("followed") String followed) throws RemoteException
    {
        service.stopFollowing(follower, followed);
    }
    
    @GET
    @Path("{user}/tweet/{tweet}")
    public void createNewTweet(@PathParam("user") String username, @PathParam("tweet") String tweet) throws RemoteException
    {
        service.createNewTweet(username, tweet);
    }
    
    @GET
    @Path("tweets/{user}")
    @Produces("text/plain")
    public List<String> getTweetsOfUser(@PathParam("user") String username) throws RemoteException
    {
        return service.getTweetsOfUser(username);
    }
}
