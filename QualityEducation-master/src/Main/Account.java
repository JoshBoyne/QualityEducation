/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author owen
 */
public class Account {
 
    private String name;
    private String password;
    private boolean loggedIn = false;
    private File dir;//User's personal directory for storing all their data.
    
    public Account(String name, String pass) throws IOException {
        this.name = name;
        this.password = pass;
        dir = IOHandler.makeAccountDir(name);
        login();
    }
    
    public void login() throws IOException {
        this.loggedIn = false;
        loggedIn = IOHandler.dailyLogin(dir);
    }
    
    public void saveState() throws IOException {
        IOHandler.saveState(this);
    }
//Getters
    public String getName() {
        return name;
    }

    public File getDir() {
        return dir;
    }
    
    
    
}
//Set up filepath to .sr for saving the object instance
//Create method for saving