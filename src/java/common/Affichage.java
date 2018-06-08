/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package common;

import java.util.Scanner;

/**
 *
 * @author mylen
 */
public class Affichage {
    
    private Scanner s;
    
    public Affichage() {
        this.s = new Scanner(System.in);
    }
    
    public String menuPrincipal(){
        System.out.println("-----------------------------");
        System.out.println("Que souhaitez vous faire ?");
        System.out.println("1 - M'abonner à un utilisateur");
        System.out.println("2 - Me désabonner d'un utilisateur");
        System.out.println("3 - Consulter les tweets d'un abonné");
        System.out.println("4 - Consulter mes tweets");
        System.out.println("5 - Ecrire un tweet");
        System.out.println("6 - Supprimer mon compte");
        System.out.println("7 - Déconnexion");
        System.out.print("Choix : ");
        String x = s.next();  
        System.out.println("-----------------------------");
        
        return x;
    }
    
    public String menuConnexion(){
        String x;
        System.out.println("BONJOUR");
        System.out.println("1 - Me connecter");
        System.out.println("2 - Créer un compte");
        System.out.print("Choix : ");
        x = s.next();
        System.out.println();
        return x;
    }
    
    public String[] seConnecter(){
        String[] logins = new String[2];
        
        System.out.println("SE CONNECTER");
        System.out.print("Login: ");
        logins[0] = s.next();
        System.out.print("Password: ");
        logins[1] = s.next();
        System.out.println();
        
        return logins;
    }
    
    public String[] creerCompte(){
        String[] logins = null;
        
        System.out.println("CREER UN COMPTE");
        System.out.print("Login: ");
        logins[0] = s.next();
        System.out.print("Password: ");
        logins[1] = s.next();
        
        return logins;
    }
    /*
    public int choisirUser(){
        
        
        
        return x;
    }
    */
    
    
}
