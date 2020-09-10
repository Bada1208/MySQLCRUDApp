package com.sysoiev.app.view;

import com.sysoiev.app.controller.CustomerController;
import com.sysoiev.app.model.Account;
import com.sysoiev.app.model.Customer;
import com.sysoiev.app.model.Specialty;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CustomerView {
    private Scanner scanner = new Scanner(System.in);
    private CustomerController customerController = new CustomerController();


    public void printCustomers() {
        System.out.println("List of all customers : ");
        System.out.println(customerController.printAll());
    }

    public void deleteCustomer() {
        System.out.println("Enter id in order to delete row : ");
        Long index = Long.parseLong(scanner.next());
        customerController.deleteCustomer(index);
    }

    public void getByIdCustomer() {
        System.out.println("Enter id in order to get customer :");
        Long id = Long.parseLong(scanner.next());
        try {
            if (customerController.getValueByIndex(id)!= null)
                System.out.println(customerController.getValueByIndex(id).toString());

        } catch (NullPointerException e) {
            System.out.println("There is no such number ");
            System.out.println("Try one more time, please");
            getByIdCustomer();
        }
    }


    public void saveCustomer() {
        System.out.println("Enter id : ");
        Long id = Long.parseLong(scanner.next());
        System.out.println("Enter name :");
        String name = scanner.next();
        System.out.println("Enter surname :");
        String surname = scanner.next();
        System.out.println("Enter id of specialty :");
        Set<Specialty> specialtySet = new HashSet<>();
        Long idSpecialty = Long.parseLong(scanner.next());
        specialtySet.add(new Specialty(idSpecialty));
        boolean go = true;
        while (go) {
            System.out.println("Do You want to add new id of specialty? 1.Yes 2.No");
            String yesOrNo = scanner.next();
            switch (yesOrNo) {
                case ("1"):
                    System.out.println("Enter id of new specialty :");
                    Long newIdSpecialty = Long.parseLong(scanner.next());
                    specialtySet.add(new Specialty(newIdSpecialty));
                    break;
                case ("2"):
                    System.out.println("You choose do not add new specialty");
                    go = false;
                    break;
            }
        }
        System.out.println("Enter id of account :");
        Long idAccount = Long.parseLong(scanner.next());
        Account account = new Account(idAccount);
        Customer newCustomer = new Customer(id,name, surname, specialtySet, account);
        customerController.saveCustomer(newCustomer);
    }

    public void updateCustomer() {
        System.out.println("Enter id in order to find element :");
        Long id = Long.parseLong(scanner.next());
        System.out.println("Enter name :");
        String name = scanner.next();
        System.out.println("Enter surname :");
        String surname = scanner.next();
        System.out.println("Enter id of specialty :");
        Set<Specialty> specialtySet = new HashSet<>();
        Long idSpecialty = Long.parseLong(scanner.next());
        specialtySet.add(new Specialty(idSpecialty));

        boolean go = true;
        while (go) {
            System.out.println("Do You want to add new id of specialty? 1.Yes 2.No");
            String yesOrNo = scanner.next();
            switch (yesOrNo) {
                case ("1"):
                    System.out.println("Enter new id of specialty :");
                    Long newIdSpecialty = Long.parseLong(scanner.next());
                    specialtySet.add(new Specialty(newIdSpecialty));
                    break;
                case ("2"):
                    System.out.println("You choose do not add new specialty");
                    go = false;
                    break;
            }
        }
        System.out.println("Enter id of account :");
        Long idAccount = Long.parseLong(scanner.next());
        Account account = new Account(idAccount);
        Customer newCustomer = new Customer(id,name, surname, specialtySet, account);
        customerController.updateCustomer(newCustomer);
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
                    printCustomers();
                    break;
                case 2:
                    saveCustomer();
                    break;
                case 3:
                    deleteCustomer();
                    break;
                case 4:
                    updateCustomer();
                    break;
                case 5:
                    getByIdCustomer();
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
