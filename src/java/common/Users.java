package common;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import common.User;

@XmlRootElement(name = "Villes")
public class Users 
{
	@XmlElement
	public ArrayList<User> liste = new ArrayList<User>();
}
