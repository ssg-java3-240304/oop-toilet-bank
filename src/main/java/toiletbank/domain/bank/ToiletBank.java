package toiletbank.domain.bank;

import java.util.LinkedHashMap;
import toiletbank.constants.Banks;
import toiletbank.domain.Customer;
import toiletbank.domain.Customers;
import toiletbank.domain.Transactions;
import toiletbank.domain.account.Account;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ToiletBank extends Bank {
    private final Map<Bank, Customers> banks;

    public ToiletBank() {
        super(3, Banks.TOILET_BANK);
        this.banks = loadBanks();
    }

    public Map<Bank, Customers> getBanks() {
        return banks;
    }

    private Map<Bank, Customers> loadBanks() {
        Map<Bank, Customers> loadBanks = new LinkedHashMap<>();

        List<Bank> banks = new ArrayList<>();
        banks.add(new HanaBank());
        banks.add(new KbBank());
        banks.add(this);

        for (Bank bank : banks) {
            File customerFile = new File("src/main/java/toiletbank/repository/",
                    bank.getName().replace(" ", "") + ".dat");
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(customerFile))) {
                loadBanks.put(bank, (Customers) ois.readObject());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return loadBanks;
    }

    public Map<Bank, List<List<String>>> getAccounts(Customer customer) {
        Map<Bank, List<List<String>>> result = new LinkedHashMap<>();
        DecimalFormat df = new DecimalFormat("#,###");

        for (Map.Entry<Bank, Customers> banksEntry : banks.entrySet()) {
            List<List<String>> accounts = new ArrayList<>();
            Map<Customer, List<Account>> customers = banksEntry.getValue().getCustomers();
            List<Account> customerAccounts = customers.get(customer);
            if (customerAccounts != null) {
                for (Account account : customerAccounts) {
                    accounts.add(
                            List.of(account.getType().getName(), account.getNumber(), df.format(account.getBalance())));
                }
            }
            result.put(banksEntry.getKey(), accounts);
        }

        return result;
    }

    public List<String> getAccount(Customer customer, Integer bankOrder, Integer accountOrder) {
        Map<Bank, List<List<String>>> accounts = getAccounts(customer);
        Bank result = null;
        for (Bank bank : accounts.keySet()) {
            if (bank.getOrder().equals(bankOrder)) {
                result = bank;

            }
        }

        return getAccountDetails(result, customer, banks.get(result).getCustomers().get(customer).get(accountOrder-1));
    }

    public Transactions getTransaction(Customer customer,  Integer bankOrder, Integer accountOrder) {
        Map<Bank, List<List<String>>> accounts = getAccounts(customer);
        Bank result = null;
        for (Bank bank : accounts.keySet()) {
            if (bank.getOrder().equals(bankOrder)) {
                result = bank;
            }
        }

        return getTransaction(result,customer, banks.get(result).getCustomers().get(customer).get(accountOrder-1));
    }

    private List<String> getAccountDetails(Bank bank, Customer customer, Account customerAccount) {
        DecimalFormat df = new DecimalFormat("#,###");
        Account result = null;
        for (Account account : banks.get(bank).getCustomers().get(customer)) {
            if (account.equals(customerAccount)) {
                result = account;
            }
        }

        if (result == null) {
            return null;
        }

        return List.of(result.getBank().getName(), result.getType().getName(), result.getNumber(), String.valueOf(result.getInterestRate()) + " %", df.format(result.getBalance()) + "원");
    }

    private Transactions getTransaction(Bank bank, Customer customer, Account customerAccount) {
        Account result = null;
        for (Account account : banks.get(bank).getCustomers().get(customer)) {
            if (account.equals(customerAccount)) {
                result = account;
            }
        }

        if (result == null) {
            return null;
        }

        return result.getTransactions();
    }
}
