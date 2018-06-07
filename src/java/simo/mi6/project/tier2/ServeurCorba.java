/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simo.mi6.project.tier2;

import corba.ServiceCorba;
import corba.ServiceCorbaHelper;
import java.util.Properties;

import org.omg.CORBA.ORB;

import org.omg.CosNaming.NameComponent;
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;

/**
 *
 * @author noemie
 */
public class ServeurCorba {
     public static void main(String[] args) throws Exception
    {	
        // Paramétrage pour la création de la couche ORDB :
        // Localisation de l'annuaire d'objets (service nommage)
        Properties props = new Properties();
		props.put("org.omg.CORBA.ORBInitialPort", "2000");
		props.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
                // Création de la couche ORB
                // Pour communiquer via un bus CORBA
		ORB requestBroker = ORB.init((String[]) null, props);
        
        // Instance du service RootPOA  et recherche d'une référence au service "RootPOA"
        POA rootpoa = POAHelper.narrow(requestBroker.resolve_initial_references("RootPOA"));
        rootpoa.the_POAManager().activate();

        org.omg.CORBA.Object namingServiceRef = requestBroker.resolve_initial_references("NameService");
        NamingContextExt namingContext = NamingContextExtHelper.narrow(namingServiceRef);

        ServiceCorbaImpl serviceCorbaImpl = new ServiceCorbaImpl();
        ServiceCorba serviceCorba = ServiceCorbaHelper.narrow(rootpoa.servant_to_reference(serviceCorbaImpl));
        NameComponent[] path = namingContext.to_name("ServiceCorba");
        namingContext.rebind(path, serviceCorba);
        
        System.out.println("service enregistre");
        
        requestBroker.run();
    }
    
}
