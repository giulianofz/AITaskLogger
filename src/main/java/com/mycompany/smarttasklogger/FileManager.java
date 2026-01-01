package com.mycompany.smarttasklogger;

import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

    private String filePath;

    // define como se llamará el archivo
    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    public void saveTask(String content) {
        try {
            //append: True 
            FileWriter writer = new FileWriter(this.filePath, true);

            writer.write("\n" + "\n" + content);

            writer.close();
        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
}
