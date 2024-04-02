package org.example;

public class Main {
    public static void main(String[] args) {
        String masterDirectoryPath = "C:\\Users\\IUSTIN\\Desktop\\JAVA\\Lab5_Bonus";
        EmployeeDocumentManager documentManager = new EmployeeDocumentManager(masterDirectoryPath);
        documentManager.manageDocuments();
    }
}
