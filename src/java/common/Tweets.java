package common;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Tweets")
public class Tweets 
{
	@XmlElement
	public ArrayList<Tweet> liste = new ArrayList<Tweet>();
}
