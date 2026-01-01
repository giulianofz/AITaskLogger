package com.mycompany.smarttasklogger;

import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

        //privado para que nadie pueda cambiar 
    private String filePath;

    // define como se llamará el archivo
    public FileManager(String filePath) {
        this.filePath = filePath;
    }

    public void saveTask(String content) {
        try {
                         //append: True 
            FileWriter writer = new FileWriter(this.filePath, true);
            writer.write("\n==== LISTA DE TAREAS =====\n");
           // writer.write("\n==== TAREAS =====\n");
            writer.write(content);

            writer.close();

        } catch (IOException e) {
            System.out.println("Error al escribir el archivo: " + e.getMessage());
        }
    }
}
