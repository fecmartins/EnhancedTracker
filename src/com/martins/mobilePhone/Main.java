package com.martins.mobilePhone;

public class Main {

    public static void main(String[] args) {
        MobilePhone mobilePhone = new MobilePhone("1234567890");
        Contact contact = new Contact("John Doe", "1234567890");
        mobilePhone.addNewContact(contact);
        mobilePhone.printContacts();
    }
}
