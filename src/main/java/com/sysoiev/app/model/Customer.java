package com.sysoiev.app.model;

import java.util.Set;

public class Customer {
    private Long id;
    private String name;
    private String surname;
    private Account account;
    private Set<Specialty> specialties;

    private Customer(CustomerBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.specialties = builder.specialties;
        this.account = builder.account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSpecialties(Specialty specialty) {
        specialties.add(specialty);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public Account getAccount() {
        return account;
    }

    public String getSpecialties() {
        String specialtyString = "";
        for (Specialty s : specialties) {
            specialtyString += "{" + s.getId() + "}";
        }
        return specialtyString;
    }

    @Override
    public String toString() {
        return id + "/ " + name + "/ " + surname + "/ " + getSpecialties() + " /" + account.getId();
    }

    public static class CustomerBuilder {
        private Long id;
        private String name;
        private String surname;
        private Account account;
        private Set<Specialty> specialties;

        public CustomerBuilder(String name, String surname, Set<Specialty> specialties, Account account) {
            this.name = name;
            this.surname = surname;
            this.specialties = specialties;
            this.account = account;
        }

        public CustomerBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public Customer buildCustomer() {
            Customer customer = new Customer(this);
            return customer;
        }
    }
}