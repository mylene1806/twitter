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
import org.omg.CosNaming.NamingContextExt;
import org.omg.CosNaming.NamingContextExtHelper;

/**
 *
 * @author noemie
 */
public class ClientCorba {
    
    	public static void main(String[] args) throws Exception
	{
		Properties props = new Properties();
		props.put("org.omg.CORBA.ORBInitialPort", "2000");
		props.put("org.omg.CORBA.ORBInitialHost", "127.0.0.1");
		ORB requestBroker = ORB.init((String[]) null, props);

		org.omg.CORBA.Object namingServiceRef = requestBroker.resolve_initial_references("NameService");
		NamingContextExt namingContext = NamingContextExtHelper.narrow(namingServiceRef);

		ServiceCorba serviceCorba = ServiceCorbaHelper.narrow(namingContext.resolve_str("ServiceCorba"));

		String[] users = serviceCorba.getUsers();
                System.out.println("test");

	}

    
}
