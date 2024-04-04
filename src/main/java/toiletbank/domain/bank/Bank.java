package toiletbank.domain.bank;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import toiletbank.constants.Banks;
import toiletbank.domain.Customer;
import toiletbank.domain.Customers;
import toiletbank.domain.Transactions;
import toiletbank.domain.account.Account;

public abstract class Bank {
    private final Banks name;
    private final Customers customers;


    public Bank(Banks name) {
        loadCustomers(name);
        this.name = name;
        this.customers = loadCustomers(name);
    }

    // 파일에서 데이터를 로드하여 Map에 저장하는 메서드
    @SuppressWarnings("unchecked")
    private Customers loadCustomers(Banks name) {
        Customers loadedCustomers = new Customers();
        File customerFile = new File("src/main/java/toiletbank/repository/", name.getName().replace(" ", "") + ".dat");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(customerFile))) {
            loadedCustomers = (Customers) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedCustomers;
    }


    public List<String> getAccountDetails(Customer customer, Account customerAccount) {
        DecimalFormat df = new DecimalFormat("#,###");
        Account result = null;
        for (Account account : customers.getCustomers().get(customer)) {
            if (account.equals(customerAccount)) {
                result = account;
            }
        }

        if (result == null) {
            return null;
        }

        return List.of(result.getBank().getName(), result.getType().getName(), result.getNumber(), String.valueOf(result.getInterestRate()) + " %", df.format(result.getBalance()) + "원");
    }

    public Transactions getTransaction(Customer customer, Account customerAccount) {
        Account result = null;
        for (Account account : customers.getCustomers().get(customer)) {
            if (account.equals(customerAccount)) {
                result = account;
            }
        }

        if (result == null) {
            return null;
        }

        return result.getTransactions();
    }


    public Customers getCustomers() {
        return customers;
    }

    public String getName() {
        return name.getName();
    }
}
