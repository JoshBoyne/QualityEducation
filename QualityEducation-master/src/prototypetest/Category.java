/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prototypetest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author jason
 */
public class Category {
    private File file; 
    private ArrayList<String> categories;
    
     // Constructor initializes the file and loads data
    public Category(String filePath) {
        file = new File(filePath); //file object = fiile path
        categories = new ArrayList<>(); //initialize listy
        loadFile(); //load the exisiting file into the list
    }

    // Load data from the file into the categories list
    private void loadFile() {
                categories.clear(); //clear exisiting data in the lisrt
                if (file.exists()) { // checks if the file exists
                    try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = br.readLine()) != null) { //read the file line by line 
                            categories.add(line); //each line is added to list
                        }
                    } catch (IOException e) {
                        System.err.println("Error reading file: " + e.getMessage());
                    }
        }
    }
     // Save data from the categories/motes list to the file
    private void saveToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String category : categories) {
                bw.write(category);
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    // Create a new category/notes
    //ensure this works properly, slight errors 
    public void create(String category) {
        categories.add(category);
        saveToFile();
    }
     // Read all categories/motes as a single string
    public String readAll() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < categories.size(); i++) {
            sb.append(i).append(": ").append(categories.get(i)).append("\n");
        }
        return sb.toString();
    }

    // Update a category/note by index
    public void update(int index, String newCategory) {
        if (index >= 0 && index < categories.size()) {
            categories.set(index, newCategory);
            saveToFile();
        } else {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
    }
     // Delete a category/note by index
    public void delete(int index) {
        if (index >= 0 && index < categories.size()) {
            categories.remove(index);
            saveToFile();
        } else {
            throw new IndexOutOfBoundsException("Invalid index.");
        }
    }

    // Get the list size
    public int getCategoryCount() {
        return categories.size();
    }
}
