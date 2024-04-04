package toiletbank.repository;

import toiletbank.domain.Customer;
import toiletbank.domain.Customers;
import toiletbank.domain.account.Account;
import toiletbank.domain.account.FixedDeposit;
import toiletbank.domain.account.SavingsAccount;
import toiletbank.domain.account.TermDeposit;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {
    public Repository() {
        saveCustomers("KbBank", initKbBank());
        saveCustomers("HanaBank", initHanaBank());
        saveCustomers("ToiletBank", initToiletBank());
    }

    public Customers initKbBank() {
        final Customers customers = new Customers();
        final String name = "KbBank";


        // 김나경
        final List<Account> account1 = new ArrayList<>();
        SavingsAccount savingsAccount = new SavingsAccount("1234");
        savingsAccount.setBalance(new BigInteger("1000000"));

        FixedDeposit fixedDeposit = new FixedDeposit(new BigInteger("200000000"), "1234");

        account1.add(savingsAccount);
        account1.add(fixedDeposit);
        customers.getCustomers().put(new Customer("김나경", "990101-2111116"), account1);

        // 심재람
        final List<Account> account2 = new ArrayList<>();
        TermDeposit termDeposit = new TermDeposit(new BigInteger("300000000"), "2345");
        account2.add(termDeposit);
        customers.getCustomers().put(new Customer("심재람", "980101-2122226"), account2);

        return customers;
    }

    public Customers initHanaBank() {
        final Customers customers = new Customers();
        // 변성일
        final List<Account> account1 = new ArrayList<>();
        SavingsAccount savingsAccount = new SavingsAccount("1234");
        savingsAccount.setBalance(new BigInteger("10000000"));
        FixedDeposit fixedDeposit = new FixedDeposit(new BigInteger("200000000"), "1234");

        account1.add(savingsAccount);
        account1.add(fixedDeposit);
        customers.getCustomers().put(new Customer("변성일", "980101-2133336"), account1);

        // 김나경
        final List<Account> account2 = new ArrayList<>();

        TermDeposit termDeposit = new TermDeposit(new BigInteger("530000"), "1234");
        account2.add(termDeposit);
        customers.getCustomers().put(new Customer("김나경", "990101-2111116"), account2);

        return customers;

    }

    public Customers initToiletBank() {
        final Customers customers = new Customers();

        // 변성일
        final List<Account> account1 = new ArrayList<>();
        SavingsAccount savingsAccount = new SavingsAccount("1234");
        savingsAccount.setBalance(new BigInteger("670000"));
        FixedDeposit fixedDeposit = new FixedDeposit(new BigInteger("230000"), "1234");

        account1.add(savingsAccount);
        account1.add(fixedDeposit);
        customers.getCustomers().put(new Customer("변성일", "980101-2133336"), account1);

        // 심재람
        final List<Account> account2 = new ArrayList<>();

        TermDeposit termDeposit = new TermDeposit(new BigInteger("530000"), "1234");
        account2.add(termDeposit);
        customers.getCustomers().put(new Customer("심재람", "980101-2122226"), account2);

        return customers;
    }

    // 파일에 데이터를 저장하는 메서드
    private void saveCustomers(String name, Customers customers) {
        File customerFile = new File("src/main/java/toiletbank/repository/", name + ".dat");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(customerFile))) {
            oos.writeObject(customers);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
