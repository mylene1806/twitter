/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simo.mi6.project.tier1;

import common.Affichage;
import common.Tweets;
import common.User;
import common.Users;
import corba.ServiceCorba;
import corba.ServiceCorbaHelper;
import java.util.Properties;
import java.util.Scanner;
import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

/**
 *
 * @author noemie
 */
public class ClientCorba {

    private static ServiceCorba serviceCorba = null;
    private static User user;
    private static Affichage affichage = new Affichage();

    public static void main(String[] args) throws Exception {

        Properties props = new Properties();
        props.put("org.omg.CORBA.ORBInitialPort", "2000");
        props.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
        ORB requestBroker = ORB.init((String[]) null, props);

        org.omg.CORBA.Object namingServiceRef = requestBroker.resolve_initial_references("NameService");
        NamingContextExt namingContext = NamingContextExtHelper.narrow(namingServiceRef);

        serviceCorba = ServiceCorbaHelper.narrow(namingContext.resolve_str("ServiceCorba"));

        String[] users = serviceCorba.getUsers();
        System.out.println("test");

        boolean connecte = false;

        if (accueilAuthentification().equals("true")) {
            System.out.println("Connecté\n");
            connecte = true;
        } else {
            System.out.println("/!\\ Echec connexion !");
        }

        while (connecte) {
            menuPrincipal();
        }

    }

    public static String accueilAuthentification() {
        String x;
        String connexion = "false";
        x = affichage.menuConnexion();
        switch (x) {
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

    public static String seConnecter() {
        String logins[] = affichage.seConnecter();

        user = new User(logins[0], logins[1]);
        return serviceCorba.isUserPasswordCorrect(logins[0], logins[1]);
    }

    public static String nouveauCompte() {
        String logins[] = affichage.creerCompte();
        user = new User(logins[0], logins[1]);
        return serviceCorba.createNewUser(logins[0], logins[1]);
    }

    public static void menuPrincipal() {
        // CHOIX DE L'ACTION
        String x = affichage.menuPrincipal();
        Scanner s = new Scanner(System.in);
        String[] users;

        switch (x) {
            case "1":
                System.out.println("\n-----------------------\nS'ABBONNER A UN UTILISATEUR.");
                users = serviceCorba.getUsers();
                for (int i = 0; i < users.length; i++) {
                    System.out.println(i + 1 + "\t" + users[i]);
                }
                System.out.print("Choisir un utilisateur : ");
                int y = s.nextInt();
                serviceCorba.startFollowing(user.getUsername(), users[y - 1]);
                break;
            case "2":
                System.out.println("\n-----------------------\nSE DESABONNER A UN UTILISATEUR.");
                users = serviceCorba.getUsersFollowedBy(user.getUsername());
                for(int i = 0; i < users.length; i++) 
                {
                    System.out.println(i+1 + "\t" + users[i]);
		} 
                System.out.print("Choisir un utilisateur : ");
                y = s.nextInt(); 
                serviceCorba.stopFollowing(user.getUsername(), users[y-1]);
                break;
            case "3":
                System.out.println("\n-----------------------\nLISTE DES ABONNEMENTS.");
                users = serviceCorba.getUsersFollowedBy(user.getUsername());
                for(int i = 0; i < users.length; i++) 
                {
                    System.out.println(i + "\t" + users[i]);
		} 
                System.out.print("Choisir un utilisateur : ");
                y = s.nextInt(); 
                String[] tweets = serviceCorba.getTweetsOfUser(users[y-1]);
                for(int i = 0; i < tweets.length; i++) 
                {
                    System.out.println(i + "\t" + tweets[i]);
                    System.out.println("----------------");
		} 
                
                for(int i = 0; i < users.length; i++) 
                {
                    System.out.println(i + "\t" + users[i]);
		}                 
                //System.out.print("Tweets de "+ user +" : ");  // déjà commenté
                break;
            case "4":
                System.out.println("\n-----------------------\nMES TWEETS");
//                System.out.println(webService.path("tweets/" + user.getUsername()).get(String.class));
                break;
            case "5":
                System.out.println("\n-----------------------\nECRIRE UN TWEET");
//                x = s.next();
//                //x = s.nextLine();
//                webService.path("createTweet/" + user.getUsername() + "/" + x).put();
                break;
            case "6":
//                System.out.println(webService.path("remove").delete(String.class, user));
                break;
            case "7":
//                System.out.println("Lancement corba.");
                break;
            default:
                System.out.println("Choix incorrecte.");
                System.out.println();
        }
    }

}
