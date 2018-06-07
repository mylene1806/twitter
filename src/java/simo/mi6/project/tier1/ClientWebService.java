package simo.mi6.project.tier1;

import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import simo.mi6.project.tier2.WebService;
import simo.mi6.project.tier3.TwitterDBService;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import common.User;
import java.io.IOException;


public class ClientTwitter
{
    /*public Client() throws Exception
    {
        //System.setProperty("java.rmi.server.hostname", "86.76.4.24");        
        //TwitterDBService service = (TwitterDBService) Naming.lookup("rmi://86.76.4.24:3200/TwitterDBService");
    }*/
    private static WebResource webService = null;
    
    public static void main(String [] args) throws Exception
    {
        //WebService webService = new WebService();
        webService = Client.create().resource("http://localhost:8080/twitter");
        //choixConnexion();
        
        if (accueilAuthentification()){
            System.out.print("Connecté");
        } else {
            System.out.print("Echec connexion");
        }
        
        System.out.println(webService.path("users").get(String.class));
        
        /*while(seConnecter()){
            menuPrincipal();
        }*/
        
        //webService.path("users").get();
        
        /*switch(x) 
        {
                case "1":
                    System.out.println();
                    System.out.println("-----------------------");
                    System.out.println();
                    System.out.println("LISTE DES UTILISATEURS.");
                    System.out.println(webService.getUsers());
                    
                    System.out.print("Choix un abonné ");
                    x = s.next(); 
         
                    break;
                case "2":
                    System.out.println("LISTE DES ABONNEES.");
                    break;
                case "3":
                    System.out.println("Lancement corba.");
                    break;
                case "4":
                    System.out.println("Lancement corba.");
                    break; 
                case "5":
                    System.out.println("Lancement corba.");
                    break;
                default:
                    System.out.println("Choix incorrecte.");
                    System.out.println();
        }*/
        
    }
    
    
    public static boolean accueilAuthentification(){
        String x;
        Boolean connexion = false;
        Scanner s = new Scanner(System.in);
        System.out.println("BONJOUR");
        System.out.println("1 - Me connecter");
        System.out.println("2 - Créer un compte");
        System.out.print("Choix : ");
        x = s.next();
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
        System.out.println("SE CONNECTER");
        Scanner s = new Scanner(System.in);
        String login, password;
        System.out.print("Login: ");
        login = s.next();
        System.out.print("Password: ");
        password = s.next();
        
        User user = new User(login, password);
        return Boolean.valueOf(webService.path("connect").put(String.class, user));
    }
    
    public static boolean nouveauCompte(){
        Scanner s = new Scanner(System.in);
        String login, password;
        System.out.print("Login: ");
        login = s.next();
        System.out.print("Password: ");
        password = s.next();
        User user = new User(login, password);
        return Boolean.valueOf(webService.path("create").put(String.class, user));
    }
    
    public static void menuPrincipal(){
        // CHOIX DE L'ACTION
        Scanner s = new Scanner(System.in);
        System.out.println("Que souhaitez vous faire ?");
        System.out.println("1 - M'abonner à un utilisateur");
        System.out.println("2 - Me désabonner d'un utilisateur");
        System.out.println("3 - Consulter les tweets d'un abonné");
        System.out.println("4 - Consulter mes tweets");
        System.out.println("5 - Ecrire un tweet");
        System.out.println("6 - Déconnexion");
        String x = s.next();        
    }
    
    
    public static void choixConnexion(){
        // CHOIX DU MOYEN DE CONNEXION
        Scanner s = new Scanner(System.in);
        System.out.println("Comment souhaitez vous vous authentifier ?");
        System.out.println("1 - Service web");
        System.out.println("2 - Corba");
        String x = s.next(); 
        
        switch(x) 
        {
            case "1":
                System.out.println("Lancement service web.");
                break;
            case "2":
                System.out.println("Lancement corba.");
                break;
            default:
                System.out.println("Choix incorrecte.");
                System.out.println();
        }
    }
    
}