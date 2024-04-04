package toiletbank.domain.bank;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import toiletbank.domain.Customer;
import toiletbank.domain.Transaction;
import toiletbank.domain.Transactions;
import toiletbank.domain.account.Account;

public abstract class Bank {
    private final String name;
    private final Map<Customer, List<Account>> customers;


    public Bank(String name) {
        loadCustomers(name);
        this.name = name;
        this.customers = loadCustomers(name);
    }

    // 파일에서 데이터를 로드하여 Map에 저장하는 메서드
    @SuppressWarnings("unchecked")
    private Map<Customer, List<Account>> loadCustomers(String name) {
        Map<Customer, List<Account>> loadedCustomers = new HashMap<>();
        File customerFile = new File("src/main/java/toiletbank/repository/", name + ".dat");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(customerFile))) {
            loadedCustomers = (Map<Customer, List<Account>>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedCustomers;
    }


    public List<String> getAccountDetails(Customer customer, Account customerAccount) {
        DecimalFormat df = new DecimalFormat("#,###");
        Account result = null;
        for (Account account : customers.get(customer)) {
            if (account.equals(customerAccount)) {
                result = account;
            }
        }

        return List.of(result.getBank().getName(), result.getType().getName(), result.getNumber(), String.valueOf(result.getInterestRate()) + " %", df.format(result.getBalance()) + "원");
    }

    public Transactions getTransaction(Customer customer, Account customerAccount) {
        Account result = null;
        for (Account account : customers.get(customer)) {
            if (account.equals(customerAccount)) {
                result = account;
            }
        }

        return result.getTransactions();
    }


    public Map<Customer, List<Account>> getCustomers() {
        return customers;
    }

    public String getName() {
        return name;
    }
}
