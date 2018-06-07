package common;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Tweet")
public class Tweet {
    
    private String author;
    private String message;
    
    public Tweet(){}
    
    public Tweet(String author, String message)
    {
        this.author = author;
        this.message = message;
    }
    
    public String getAuthor()
    {
        return this.author;
    }
    
    public void setAuthor(String author)
    {
        this.author = author;
    }
    
    public String getMessage()
    {
        return this.message;
    }
    
    public void setMessage(String message)
    {
        this.message = message;
    }   
}
