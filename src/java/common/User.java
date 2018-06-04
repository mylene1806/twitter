package common;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "User")
public class User {
    
    private String username;
    private String password;
    
    public User(){}
    
    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
    
    public String getUsername()
    {
        return this.username;
    }
    
    public String getPassword()
    {
        return this.password;
    }
    
}
