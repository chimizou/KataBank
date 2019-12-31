package com.kata.bank;

public class BankOperations {

    public static void main(String[] args) {
        Account myAccount = new Account(1000);
        myAccount.withdrawMoney(200);
        myAccount.depositMoney(200.20);
        myAccount.withdrawMoney(100);
        myAccount.withdrawMoney(40);
        myAccount.printAllOperations();
    }
}
