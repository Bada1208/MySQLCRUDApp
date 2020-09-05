package com.sysoiev.app.view;

import java.util.Scanner;

public class CommonView {

    private AccountView accountView;
    private CustomerView customerView;
    private SpecialtyView specialtyView;
    private static CommonView view;

    private CommonView() {
        specialtyView = new SpecialtyView();
        accountView = new AccountView();
        customerView = new CustomerView();
    }

    public static CommonView getInstance() {
        if (view == null) {
            view = new CommonView();
        }
        return view;
    }


    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean go = true;
        while (go) {
            System.out.println("\nChoose file in order to do operations , please :");
            System.out.println("Enter number : ");
            System.out.println("1.Specialties");
            System.out.println("2.Accounts");
            System.out.println("3.Customers");
            System.out.println("4. End ");
            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    runSpecialty();
                    break;
                case 2:
                    runAccount();
                    break;
                case 3:
                    runCustomer();
                    break;
                case 4:
                    go = false;
                    break;
                default:
                    System.out.println("Wrong number");
                    System.out.println("Enter number from 1 to 4, please");
            }
        }
    }

    public void runSpecialty() {
        specialtyView.run();
    }

    public void runAccount() {
        accountView.run();
    }

    public void runCustomer() {
        customerView.run();
    }

}
