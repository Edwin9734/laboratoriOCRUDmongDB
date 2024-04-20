package org.example;

import org.example.Clases.CRUD;
import org.example.Clases.ClsUsuariosMongo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        ClsUsuariosMongo D = new ClsUsuariosMongo();
        CRUD S = new CRUD();
        D.conexion();
        //D.crearUsuario2();
       // S.conexion();
        //S.crearUsuario2();
        D.leerUsuarios();




//        Scanner scanner = new Scanner(System.in);
//
//        do {
//            System.out.println("\nMenu Principal:");
//            System.out.println("1. actualizar ");
//            System.out.println("2. Opción 2");
//            System.out.println("3. Salir");
//
//            System.out.print("Ingrese su elección: ");
//            int choice = scanner.nextInt();
//
//            switch (choice) {
//                case 1:
//                    System.out.println("Ejecutando Opción 1...");
//                    // Implementar la funcionalidad de la Opción 1
//                    break;
//                case 2:
//                    System.out.println("Ejecutando Opción 2...");
//                    // Implementar la funcionalidad de la Opción 2
//                    break;
//                case 3:
//                    System.out.println("Saliendo del programa...");
//                    break;
//                default:
//                    System.out.println("Opción no válida. Intente nuevamente.");
//            }
//        } while (1 > 3);
//
//        scanner.close();










    }
}