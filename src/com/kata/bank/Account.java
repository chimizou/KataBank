package com.kata.bank;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Contient toutes les informations du compte (numéro du compte, liste des opération, solde etc.)
 * Permet de retirer / déposer de l'argent sur son compte, ainsi que d'afficher la liste des opérations.
 */
public class Account {

    private int accountNumber;
    private Map<Double, LocalDateTime> operations;

    // lastAccountNumber est partagé par tous les comptes.
    private static int lastAccountNumber = 0;

    /**
     * Constructeur de la classe Account.
     *
     * @param initialBalance solde initial lors de l'ouverture du compte.
     */
    public Account(double initialBalance) {
        operations = new LinkedHashMap<>();
        this.operations.put(initialBalance, LocalDateTime.now());
        this.accountNumber = lastAccountNumber + 1;
    }

    /**
     * Retourne le solde du compte.
     *
     * @return solde du compte.
     */
    public double getBalance() {
        return operations.keySet().stream().reduce(0.0, Double::sum);
    }

    /**
     * Ajoute une opération (retrait/depôt).
     *
     * @param amountToWithdrawOrToDeposit le montant à retirer ou à déposer, peut être positif ou négatif.
     */
    private void addOperation(double amountToWithdrawOrToDeposit) {
        this.operations.put(amountToWithdrawOrToDeposit, LocalDateTime.now());
    }

    /**
     * Retire de l'argent.
     *
     * @param amountToWithdraw montant à retirer.
     * @return true si solde suffisant, false sinon.
     */
    public boolean withdrawMoney(double amountToWithdraw) {
        if (amountToWithdraw <= getBalance()) {
            this.addOperation(-amountToWithdraw);
            return true;
        } else {
            System.out.println("Solde insuffisant!");
            return false;
        }
    }

    /**
     * Déposer de l'argent dans le compte. Cette méthode fait appel à la methode privée addOperation et sa seul utilité
     * est d'avoir un nom de méthode plus significatif dans le contrat.
     *
     * @param amountToDeposit le montant à retirer ou à déposer, peut être positif ou négatif.
     */
    public void depositMoney(double amountToDeposit) {
        this.addOperation(amountToDeposit);
    }

    /**
     * Retourne la liste des opérations.
     *
     * @return liste des opération
     */
    public Map<Double, LocalDateTime> getAllOperations() {
        return this.operations;
    }

    /**
     * Affiche la liste des opérations.
     */
    public void printAllOperations() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/YYYY hh:mm:ss.SS");
        System.out.println("liste des mouvements du compte: " + this.accountNumber);

        for (Map.Entry operation : this.operations.entrySet()) {
            System.out.println("montant : " + operation.getKey()
                    + " | date de l'opération: " + LocalDateTime.now().format(formatter));
        }
        System.out.println("le solde actuel est : " + this.getBalance());
    }

}
