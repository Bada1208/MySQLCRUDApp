package com.sysoiev.app.view;

import com.sysoiev.app.controller.SpecialtyController;
import com.sysoiev.app.model.Specialty;

import java.util.Scanner;

public class SpecialtyView {
    private Scanner scanner = new Scanner(System.in);
    private SpecialtyController specialtyController = new SpecialtyController();

    public void printSpecialties() {
        System.out.println("List of all specialties : ");
        System.out.println(specialtyController.printAll());
    }

    public void deleteSpecialty() {
        System.out.println("Enter id in order to delete row : ");
        Long index = Long.parseLong(scanner.next());
        specialtyController.deleteSpecialty(index);
    }

    public void getByIdSpecialty() {
        System.out.println("Enter id in order to get specialty :");
        Long id = Long.parseLong(scanner.next());
        try {
            if (specialtyController.getValueByIndex(id) != null)
                System.out.println(specialtyController.getValueByIndex(id).toString());

        } catch (NullPointerException e) {
            System.out.println("There is no such number ");
            System.out.println("Try one more time, please");
            getByIdSpecialty();
        }
    }

    public void saveSpecialty() {
        System.out.println("Enter id : ");
        Long id = Long.parseLong(scanner.next());
        System.out.println("Enter specialty : ");
        String specialty = scanner.next();
        Specialty newSpecialty = new Specialty(id, specialty);
        specialtyController.saveSpecialty(newSpecialty);
    }

    public void updateSpecialty() {
        System.out.println("Enter id in order to find element :");
        Long id = Long.parseLong(scanner.next());
        System.out.println("Enter new specialty : ");
        String specialty = scanner.next();
        Specialty newSpecialty = new Specialty(id, specialty);
        specialtyController.updateSpecialty(newSpecialty);
    }

    public void run() {
        boolean go = true;
        while (go) {
            System.out.println("\nChoose option, please :");
            System.out.println("Enter number : ");
            System.out.println("1. Show all rows");
            System.out.println("2. Insert new row");
            System.out.println("3. Delete row ");
            System.out.println("4. Update row  ");
            System.out.println("5. Search by id ");
            System.out.println("6. End ");
            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    printSpecialties();
                    break;
                case 2:
                    saveSpecialty();
                    break;
                case 3:
                    deleteSpecialty();
                    break;
                case 4:
                    updateSpecialty();
                    break;
                case 5:
                    getByIdSpecialty();
                    break;
                case 6:
                    go = false;
                    break;
                default:
                    System.out.println("Wrong number");
                    System.out.println("Enter number from 1 to 6, please");
            }
        }
    }
}

