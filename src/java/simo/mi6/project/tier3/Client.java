package simo.mi6.project.tier3;

import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class Client
{
    public static void main(String [] args) throws Exception
    {
        System.setProperty("java.rmi.server.hostname", "86.76.4.24");        
        TwitterDBService service = (TwitterDBService) Naming.lookup("rmi://86.76.4.24:3200/TwitterDBService");

        System.out.println("Nous sommes connectés");
    }
}