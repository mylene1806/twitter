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
import java.io.StringReader;
import java.util.Properties;
import java.util.Scanner;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
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
        String[] usersFollowed;

        switch (x) {
            case "1":
                System.out.println("\n-----------------------\nS'ABBONNER A UN UTILISATEUR.");
                users = serviceCorba.getUsers();
                for (int i = 0; i < users.length; i++) {
                    System.out.println(i + 1 + "\t" + users[i]);
                }
                System.out.print("Choisir un utilisateur : ");
                int y = s.nextInt();
                if (0 < y && y <= users.length) {
                    serviceCorba.startFollowing(user.getUsername(), users[y - 1]);
                } else {
                    System.out.println("Saisie incorrecte");
                    menuPrincipal();
                }
                break;

            case "2":
                System.out.println("\n-----------------------\nSE DESABONNER A UN UTILISATEUR.");
                System.out.println(user.getUsername());
                usersFollowed = serviceCorba.getUsersFollowedBy(user.getUsername());
                for (int i = 0; i < usersFollowed.length; i++) {
                    System.out.println(i + 1 + "\t" + usersFollowed[i]);
                }
                System.out.print("Choisir un utilisateur : ");
                y = s.nextInt();
                if (0 < y && y <= usersFollowed.length) {
                    serviceCorba.stopFollowing(user.getUsername(), usersFollowed[y - 1]);
                } else {
                    System.out.println("Saisie incorrecte");
                    menuPrincipal();
                }
                break;

            case "3":
                System.out.println("\n-----------------------\nLISTE DES ABONNEMENTS.");
                usersFollowed = serviceCorba.getUsersFollowedBy(user.getUsername());
                for (int i = 0; i < usersFollowed.length; i++) {
                    System.out.println(i + 1 + "\t" + usersFollowed[i]);
                }
                System.out.print("Choisir un utilisateur : ");
                y = s.nextInt();
                if (0 < y && y <= usersFollowed.length) {
                    String[] tweets = serviceCorba.getTweetsOfUser(usersFollowed[y - 1]);
                    System.out.print("Tweets de " + usersFollowed[y - 1] + " : ");
                    if (tweets.length > 0) {
                        for (int j = 0; j < tweets.length; j++) {
                            System.out.println("\n" + j + 1 + "\t" + tweets[j]);
                            System.out.println("----------------");
                        }
                    }
                }

                break;
            case "4":
                System.out.println("\n-----------------------\nMES TWEETS\n");
                String[] tweets = serviceCorba.getTweetsOfUser(user.getUsername());
//                System.out.print("Tweets de " + user.getUsername() + " : ");
                if (tweets.length > 0) {
                    for (int j = 0; j < tweets.length; j++) {
                        System.out.println(j + 1 + "\t" + tweets[j]);
                        System.out.println("----------------");
                    }
                }
                break;
            case "5":
                String tweet = affichage.creerTweet();
                serviceCorba.createNewTweet(user.getUsername(), tweet);
                break;
            case "6":
                serviceCorba.removeUser(user.getUsername());
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
