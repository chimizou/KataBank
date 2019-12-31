import com.kata.bank.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class AccountTest {

    static Account myAccountTest;

    @BeforeAll
    static void initializeAccount() {
        myAccountTest = new Account(1000);
    }

    @Test
    void getBalance() {
        Assertions.assertEquals(1000, myAccountTest.getBalance());
    }

    @Test
    void withdrawMoneySuccess() {
        Assertions.assertTrue(myAccountTest.withdrawMoney(200));
    }

    @Test
    void withdrawMoneyFail() {
        Assertions.assertFalse(myAccountTest.withdrawMoney(20000));
    }

    @Test
    void getAllOperations() {
        myAccountTest.depositMoney(100);
        myAccountTest.withdrawMoney(200);
        myAccountTest.depositMoney(400);
        myAccountTest.withdrawMoney(300);
        Assertions.assertEquals(5, myAccountTest.getAllOperations().size()); // On oublie pas le premier élément inséré dans le constructeur
    }

    @Test
    void addOperation() {
        double amountToDeposit = 200;
        myAccountTest.depositMoney(amountToDeposit);
        Assertions.assertEquals(1200, myAccountTest.getBalance());
        Assertions.assertEquals(6, myAccountTest.getAllOperations().size());
    }
}