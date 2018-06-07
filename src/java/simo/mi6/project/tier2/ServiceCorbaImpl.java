/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simo.mi6.project.tier2;

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
    public ServiceCorbaImpl() throws Exception {
        // Appel au service RMI
        System.setProperty("java.rmi.server.hostname", "86.76.4.24");        
        service = (TwitterDBService) Naming.lookup("rmi://86.76.4.24:3200/TwitterDBService");
    }
    
    
    
    
    @Override
    public String[] getUsers() {
    
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
    public void createNewUser(String u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String isUserPasswordCorrect(String u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeUser(String u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getUsersFollowing(String u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getUsersFollowedBy(String u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void startFollowing(String follower, String followed) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stopFollowing(String follower, String followed) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void createNewTweet(String username, String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String[] getTweetsOfUser(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
