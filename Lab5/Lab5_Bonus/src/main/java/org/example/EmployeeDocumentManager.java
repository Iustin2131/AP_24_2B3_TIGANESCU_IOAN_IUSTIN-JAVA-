package org.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class EmployeeDocumentManager {
    private String masterDirectoryPath;

    public EmployeeDocumentManager(String masterDirectoryPath) {
        this.masterDirectoryPath = masterDirectoryPath;
    }

    public void manageDocuments() {
        File masterDirectory = new File(masterDirectoryPath);
        if (!masterDirectory.exists() || !masterDirectory.isDirectory()) {
            System.out.println("Master directory does not exist.");
            return;
        }

        List<Employee> employees = readEmployeesFromExcel();

        if (employees.isEmpty()) {
            System.out.println("No employee data found.");
            return;
        }

        List<Employee> maximalGroup = findMaximalGroup(employees);

        if (maximalGroup.isEmpty()) {
            System.out.println("No maximal group found.");
        } else {
            System.out.println("Maximal Group with Common Abilities (" + maximalGroup.get(0).getAbilities() + "): ");
            for (Employee employee : maximalGroup) {
                System.out.println("- Name: " + employee.getName() + "   Company: " + employee.getCompany());
            }
        }
    }

    private List<Employee> readEmployeesFromExcel() {
        List<Employee> employees = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(new File(masterDirectoryPath, "employees.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Assuming employees' data is in the first sheet
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);

            // Iterate over each row in the sheet
            for (Row row : sheet) {
                Cell nameCell = row.getCell(0);
                Cell companyCell = row.getCell(1);
                Cell abilitiesCell = row.getCell(2);

                if (nameCell != null && companyCell != null && abilitiesCell != null) {
                    String name = nameCell.getStringCellValue();
                    String company = companyCell.getStringCellValue();
                    String abilitiesString = abilitiesCell.getStringCellValue();

                    // Split abilities string by comma and trim each ability
                    List<String> abilities = Arrays.asList(abilitiesString.split(","));
                    List<String> trimmedAbilities = new ArrayList<>();
                    for (String ability : abilities) {
                        trimmedAbilities.add(ability.trim());
                    }

                    // Add employee to the list
                    employees.add(new Employee(name, company, trimmedAbilities));
                }
            }

            workbook.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private List<Employee> findMaximalGroup(List<Employee> employees) {
        List<Employee> maximalGroup = new ArrayList<>();

        for (Employee employee : employees) {
            List<Employee> group = new ArrayList<>();
            group.add(employee);

            for (Employee otherEmployee : employees) {
                if (!employee.equals(otherEmployee) && hasCommonAbilities(employee.getAbilities(), otherEmployee.getAbilities())) {
                    group.add(otherEmployee);
                }
            }

            if (group.size() > maximalGroup.size()) {
                maximalGroup = group;
            }
        }

        return maximalGroup;
    }

    private boolean hasCommonAbilities(List<String> abilities1, List<String> abilities2) {
        for (String ability : abilities1) {
            if (abilities2.contains(ability)) {
                return true;
            }
        }
        return false;
    }
}
