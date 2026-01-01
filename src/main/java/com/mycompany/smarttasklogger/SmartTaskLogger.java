package com.mycompany.smarttasklogger;

import java.util.Scanner;

public class SmartTaskLogger {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        FileManager fileManager = new FileManager("Tareas.txt");

        System.out.println("--- AI Task Logger (v0.1) ---");
        System.out.println("Que te gustaria anotar?");

        String userInput = sc.nextLine();
            
         // si "userInput.isBlank()" NO es true, se guarda.
        if (!userInput.isBlank()) {
            fileManager.saveTask(userInput);
            System.out.println("Tarea(s) guardada en tareas.txt con exito!");

        } else {
            System.out.println("La tarea no puede estar vacia");
        }
        sc.close();
    }
}
