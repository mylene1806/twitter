package simo.mi6.project.tier1;

import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import simo.mi6.project.tier2.WebService;
import simo.mi6.project.tier3.TwitterDBService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import common.*;
import corba.ServiceCorba;
import corba.ServiceCorbaHelper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;


public class ClientWebService
{
    /*public Client() throws Exception
    {
        //System.setProperty("java.rmi.server.hostname", "86.76.4.24");        
        //TwitterDBService service = (TwitterDBService) Naming.lookup("rmi://86.76.4.24:3200/TwitterDBService");
    }*/
    private static WebResource webService = null;
    private static User user;
    private static Affichage affichage = new Affichage();
    
    public static void main(String [] args) throws Exception
    {
        
        webService = Client.create().resource("http://localhost:8080/twitter");
        
        
        boolean connecte = false;
        
        if (accueilAuthentification()){
            System.out.println("Connecté\n");
            connecte = true;
        } else {
            System.out.println("/!\\ Echec connexion !");
        }
        
        while(connecte){
            menuPrincipal();
        }
        
    }
    
    public static boolean accueilAuthentification(){
        String x;
        Boolean connexion = false;
        x = affichage.menuConnexion();
        switch(x) 
        {
            case "1":
                connexion = seConnecter();
                break;
            case "2":
                connexion = nouveauCompte();
                break;
            default:
                System.out.println("Choix incorrecte.");
                System.out.println();
        }
        return connexion;
    }
    
    public static boolean seConnecter(){
        String logins[] = affichage.seConnecter();
        
        user = new User(logins[0], logins[1]);
        return Boolean.valueOf(webService.path("connect").put(String.class, user));
    }
    
    public static boolean nouveauCompte(){
        String logins[] = affichage.creerCompte();
        user = new User(logins[0], logins[1]);
        return Boolean.valueOf(webService.path("create").put(String.class, user));
    }
    
    public static void menuPrincipal(){
        // CHOIX DE L'ACTION
        String x = affichage.menuPrincipal();
        Scanner s = new Scanner(System.in);
        
        switch(x) 
        {
            case "1":
                System.out.println("\n-----------------------\nS'ABBONNER A UN UTILISATEUR.");
                Users users = webService.path("users").get(Users.class);
                for(int i = 0; i < users.liste.size(); i++) 
                {
                    System.out.println(i+1 + "\t" + users.liste.get(i).getUsername());
		} 
                System.out.print("Choisir un utilisateur : ");
                int y = s.nextInt(); 
                users.liste.get(y-1);
                webService.path("follow/" + user.getUsername() + "/" + users.liste.get(y-1).getUsername()).put();
                break;
            case "2":
                System.out.println("\n-----------------------\nSE DESABONNER A UN UTILISATEUR.");
                users = webService.path("followedBy/" + user.getUsername()).get(Users.class);
                
                for(int i = 0; i < users.liste.size(); i++) 
                {
                    System.out.println(i+1 + "\t" + users.liste.get(i).getUsername());
		} 
                System.out.print("Choisir un utilisateur : ");
                y = s.nextInt(); 
                users.liste.get(y-1);
                webService.path("stopFollow/" + user.getUsername() + "/" + users.liste.get(y-1).getUsername()).put();
                break;
            case "3":
                System.out.println("\n-----------------------\nLISTE DES ABONNEMENTS.");
                users = webService.path("followedBy/" + user.getUsername()).get(Users.class);
                for(int i = 0; i < users.liste.size(); i++) 
                {
                    System.out.println(i + "\t" + users.liste.get(i).getUsername());
		} 
                System.out.print("Choisir un utilisateur : ");
                y = s.nextInt(); 
                users.liste.get(y-1);
                Tweets tweets = webService.path("tweets/" + users.liste.get(y-1).getUsername()).get(Tweets.class); 
                for(int i = 0; i < tweets.liste.size(); i++) 
                {
                    System.out.println(i + "\t" + tweets.liste.get(i).getMessage());
                    System.out.println("----------------");
		} 
                
                for(int i = 0; i < users.liste.size(); i++) 
                {
                    System.out.println(i + "\t" + users.liste.get(i).getUsername());
		} 
                
                //System.out.print("Tweets de "+ user +" : ");
                break;
            case "4":
                System.out.println("\n-----------------------\nMES TWEETS");
                System.out.println(webService.path("tweets/" + user.getUsername()).get(String.class));
                break; 
            case "5":
                System.out.println("\n-----------------------\nECRIRE UN TWEET");
                x = s.next();
                //x = s.nextLine();
                webService.path("createTweet/" + user.getUsername() + "/" + x).put();
                break;
            case "6":
                System.out.println(webService.path("remove").delete(String.class, user));
                break;
            case "7":
                System.out.println("Lancement corba.");
                break;
            default:
                System.out.println("Choix incorrecte.");
                System.out.println();
        }
    }
}


