package prototypetest;

//imges and resources name, hold information
public class Resource {
    private String name;          // Name of the resource
    private String description;   // Description of the resource
    private String imagePath;     // Path to the resource image

    // Constructor
    public Resource(String name, String description, String imagePath) {
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }
    
    
    private void AccountIDInputActionPerformed(java.awt.event.ActionEvent evt) {                                               
        
    }      
    
    
}
