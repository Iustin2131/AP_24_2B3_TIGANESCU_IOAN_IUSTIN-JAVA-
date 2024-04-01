package org.example;

import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        while (true) {
            System.out.print("Enter command: ");
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            String[] parts = input.split(" ", 2);
            String commandName = parts[0];

            Command command;
            switch (commandName.toLowerCase()) {
                case "view":
                    command = new View();
                    break;
                case "report":
                    command = new Report();
                    break;
                case "export":
                    command = new Export();
                    break;
                default:
                    System.out.println("Invalid command");
                    System.out.println();
                    continue;
            }

            command.execute(parts);
        }
    }
}


/*

view C:\Users\IUSTIN\Desktop\JAVA\Lab5_Homework\exported_classes.json

report C:\Users\IUSTIN\Desktop\JAVA\Lab5_Homework
view C:\Users\IUSTIN\Desktop\JAVA\Lab5_Homework\repository_hierarchy.html


export C:\Users\IUSTIN\Desktop\JAVA\Lab5_Homework\src\main\java\org\example
view C:\Users\IUSTIN\Desktop\JAVA\Lab5_Homework\exported_classes.json

 */