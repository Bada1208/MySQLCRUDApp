package com.sysoiev.app.model;

import java.util.HashSet;
import java.util.Set;

public class Customer {
    private Long id;
    private String name;
    private String surname;
    private Account account;
    private Set<Specialty> customerSpecialties = new HashSet<>();

    public Customer() {
    }

    public Customer(Long id) {
        this.id = id;
    }

    public Customer(Long id, Set<Specialty> customerSpecialties) {
        this.id = id;
        this.customerSpecialties = customerSpecialties;
    }

    public Customer(Long id, String name, String surname, Set<Specialty> specialties, Account account) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.customerSpecialties = specialties;
        this.account = account;
    }

    public Customer(Long id, String name, String surname, Account account) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.account = account;
    }

    public void setCustomerSpecialties(Specialty customerSpecialty) {
        this.customerSpecialties.add(customerSpecialty);
    }

    public void setCustomerSpecialtiesSet(Set<Specialty> customerSpecialtiesSet) {
        this.customerSpecialties = customerSpecialtiesSet;
    }

    public Long getId() {
        return id;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public void setId(Long id) {
        this.id = id;
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
    public Long getAccountId() {
        return account.getId();
    }



    public Set<Specialty> getCustomerSpecialtiesSet() {
        return customerSpecialties;
    }

    public String getSpecialties() {
        String specialtyString = "";
        for (Specialty s : customerSpecialties) {
            specialtyString += "{" + s.getId() + "}";
        }
        return specialtyString;
    }

    @Override
    public String toString() {
        return id + "/ " + name + "/ " + surname + "/ " + getSpecialties() + " /" + account.getId();
    }

}
