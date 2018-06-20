/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simo.mi6.project.tier2;

import common.Tweet;
import common.Tweets;
import corba.ServiceCorbaPOA;
import java.rmi.Naming;
import java.util.List;
import simo.mi6.project.tier3.TwitterDBService;
import common.User;
import common.Users;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 *
 * @author noemie
 */
public class ServiceCorbaImpl extends ServiceCorbaPOA {

    private TwitterDBService service;
    
     /**
     * Creates a new instance of ServiceTwitter
     */
    public ServiceCorbaImpl() throws Exception 
    {
        // Appel au service RMI
        System.setProperty("java.rmi.server.hostname", "86.76.4.24");        
        service = (TwitterDBService) Naming.lookup("rmi://86.76.4.24:3200/TwitterDBService");
    }
    
    
    
    
    @Override
    public String[] getUsers() 
    {
        List<String> listUsers;        
        String[] users = new String[1];
        
        try {
            listUsers = service.getAllUsers(); 
            int size = listUsers.size();
            users = new String[size];
            for(int i = 0; i < listUsers.size(); i++) {
            users[i] = listUsers.get(i);
            }           
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceCorbaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return users;
    }

    @Override
    public String createNewUser(String username, String password) 
    {      
        try {
            service.createNewUser(username, password);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceCorbaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "true";
    }


    @Override
    public String isUserPasswordCorrect(String username, String password)
    {
        boolean result = false;
        try {
            result = service.isUserPasswordCorrect(username, password);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceCorbaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Boolean.toString(result);
    }

    @Override
    public void removeUser(String username) 
    {
        try {
            service.removeUser(username);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceCorbaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String[] getUsersFollowing(String username) 
    {
        List<String> listUsersFollowing;        
        String[] usersFollowing = new String[1];
        
        try {
            listUsersFollowing = service.getUsersFollowing(username); 
            int size = listUsersFollowing.size();
            usersFollowing = new String[size];
            for(int i = 0; i < listUsersFollowing.size(); i++) {
            usersFollowing[i] = listUsersFollowing.get(i);
            }           
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceCorbaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usersFollowing;     
    }

    @Override
    public String[] getUsersFollowedBy(String username) 
    {        
        List<String> listUsersFollowedBy;        
        String[] usersFollowedBy = new String[1];
        
        try {
            listUsersFollowedBy = service.getUsersFollowing(username); 
            int size = listUsersFollowedBy.size();
            usersFollowedBy = new String[size];
            for(int i = 0; i < listUsersFollowedBy.size(); i++) {
            usersFollowedBy[i] = listUsersFollowedBy.get(i);
            }           
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceCorbaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return usersFollowedBy;    
    }

    @Override
    public void startFollowing(String follower, String followed) 
    {
        try {
            service.startFollowing(follower, followed);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceCorbaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void stopFollowing(String follower, String followed) 
    {
        try {
            service.stopFollowing(follower, followed);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceCorbaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createNewTweet(String username, String tweet) 
    {
        try {
            service.createNewTweet(username, tweet);
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceCorbaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @param username
     * @return
     * @throws Exception
     */
    @Override
    public String[] getTweetsOfUser(String username)
    {
        List<String> listTweets;
        String[] tweets = new String[0];
        int size = 0;
        try 
        {
            listTweets = service.getTweetsOfUser(username);
            size = listTweets.size();
            tweets = new String[size];
                                
            for(int i = 0; i < listTweets.size(); i++) 
            {           
                tweets[i]=listTweets.get(i);
            }
            
        } catch (RemoteException ex) {
            Logger.getLogger(ServiceCorbaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return tweets;
    }   
}
