/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package echoesoffate;

/**
 *
 * @author Kean Saligue
 */
public class UserData {
    private String username;
    private String password;
    private String characterSelected;
    
    public UserData(String username, String password){
        this.username = username;
        this.password = password;    
    }
    
    public UserData(){
        this.username = "";
        this.password = "";    
    }
    public void setUsername(String username){
        this.username = username;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getUsername(){
        return username;
    }
      
    public String getPassword(){
        return password;
    }
    
    public void setCharacter(String characterSelected){
        this.characterSelected = characterSelected;
    }
    
}