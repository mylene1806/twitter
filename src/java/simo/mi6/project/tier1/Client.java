package simo.mi6.project.tier1;

import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import simo.mi6.project.tier3.TwitterDBService;

public class Client
{
    public Client() throws Exception
    {
        System.setProperty("java.rmi.server.hostname", "86.76.4.24");        
        TwitterDBService service = (TwitterDBService) Naming.lookup("rmi://86.76.4.24:3200/TwitterDBService");
    }
    
    
    /*public static void main(String [] args) throws Exception
    {
        System.setProperty("java.rmi.server.hostname", "86.76.4.24");        
        TwitterDBService service = (TwitterDBService) Naming.lookup("rmi://86.76.4.24:3200/TwitterDBService");

        System.out.println("Nous sommes connectés");
        
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
    }*/
}