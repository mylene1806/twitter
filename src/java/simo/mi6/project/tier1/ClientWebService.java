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
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Properties;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
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
    public static Boolean connecte = false;
    
    public static void main(String [] args) throws Exception
    {
        
        webService = Client.create().resource("http://localhost:8080/twitter");
        
        
        boolean connecte = false;
        
        connecte = accueilAuthentification();
        
        while(connecte){
            menuPrincipal();
        }
        
    }
    
    public static Boolean accueilAuthentification(){
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
        
        if (connexion){
            System.out.println("Connecté\n");
            connecte = true;
        } else {
            System.out.println("/!\\ Echec connexion !");
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
    
    public static void consulterTweet(User userTweet) throws JAXBException{
        String tweets_xml = webService.path("tweets/" + userTweet.getUsername()).get(String.class);
                
        // Initialisation du convertisseur XML <-> Objet Tweets
        JAXBContext context = JAXBContext.newInstance(Tweets.class); 
        Unmarshaller unmarshaller = context.createUnmarshaller();
        // Transformation et obtention d’un objet Tweets
        StringBuffer xmlStr = new StringBuffer(tweets_xml);
        JAXBElement<Tweets> root = unmarshaller.unmarshal(
        new StreamSource(new StringReader(xmlStr.toString())),   
        Tweets.class);
        Tweets tweets = root.getValue();
        System.out.println("\n\n\n----------TWEETS----------");
        for(int i = 0; i < tweets.liste.size(); i++) 
        {
            System.out.println(userTweet.getUsername() + ": " + tweets.liste.get(i).getMessage());
        }  
    }
    
    
    public static void menuPrincipal() throws JAXBException, IOException{
        // CHOIX DE L'ACTION
        String x = affichage.menuPrincipal();
        Scanner s = new Scanner(System.in);
        Users users;
        
        switch(x) 
        {
            case "1":
                System.out.println("\n\n\n----------S'ABBONNER A UN UTILISATEUR----------");
                users = webService.path("users").get(Users.class);
                for(int i = 0; i < users.liste.size(); i++) 
                {
                    System.out.println(i+1 + "\t" + users.liste.get(i).getUsername());
		} 
                System.out.print("Choisir un utilisateur (retour = 0): ");
                int y = s.nextInt(); 
                if(y != 0){
                    users.liste.get(y-1);
                    webService.path("follow/" + user.getUsername() + "/" + users.liste.get(y-1).getUsername()).put();
                }else{
                    menuPrincipal();
                }
                break;
            case "2":
                System.out.println("\n\n\n----------SE DESABONNER A UN UTILISATEUR----------");
                users = webService.path("followedBy/" + user.getUsername()).get(Users.class);
                
                for(int i = 0; i < users.liste.size(); i++) 
                {
                    System.out.println(i+1 + "\t" + users.liste.get(i).getUsername());
		} 
                System.out.print("Choisir un utilisateur (retour = 0): ");
                y = s.nextInt(); 
                if(y != 0){
                    users.liste.get(y-1);
                    webService.path("stopFollow/" + user.getUsername() + "/" + users.liste.get(y-1).getUsername()).put();
                }else{
                    menuPrincipal();
                }
                break;
            case "3":
                System.out.println("\n\n\n----------LISTE DES ABONNEMENTS----------");
                users = webService.path("followedBy/" + user.getUsername()).get(Users.class);
                for(int i = 0; i < users.liste.size(); i++) 
                {
                    System.out.println(i+1 + "\t" + users.liste.get(i).getUsername());
		} 
                System.out.print("Choisir un utilisateur (retour = 0): ");
                y = s.nextInt(); 
                if(y != 0){
                    users.liste.get(y-1);
                    User userTweet = users.liste.get(y-1);
                    consulterTweet(userTweet);
                }else{
                    menuPrincipal();
                }
                
                //System.out.print("Tweets de "+ user +" : ");
                break;
            case "4":
                consulterTweet(user);
                break; 
            case "5":
                
                String tweet = affichage.creerTweet();
                webService.path("createTweet/" + user.getUsername() + "/" + tweet).put();
                break;
            case "6":
                webService.path("remove/"+user.getUsername()).delete();
                System.exit(0);
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.println("Choix incorrecte.");
                System.out.println();
        }
    }
}


