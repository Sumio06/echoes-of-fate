/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package echoesoffate;

/**
 *
 * @author Kean Saligue
 */

public class UserData extends EchoesOfFateAbstractClass {
    private String username;
    private String password;
    private String characterSelected;
    
    public UserData(String username, String password) {
        this.username = username;
        this.password = password;    
    }
    
    public UserData() {
        this.username = "";
        this.password = "";    
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCharacter() {
        return characterSelected;
    }

    public void setCharacter(String characterSelected) {
        this.characterSelected = characterSelected;
    }

    @Override
    public void username() {
        System.out.println("Username: " + username);
    }

    @Override
    public void password() {
        System.out.println("Password: Password is set");
    }

    @Override
    public void character() {
        if (characterSelected != null && !characterSelected.isEmpty()) {
            System.out.println("Character: " + characterSelected);
        } else {
            System.out.println("No character has been selected.");
        }
    }

    @Override
    public void displayAllInfo() {
        username();
        password();
        character();
    }
}