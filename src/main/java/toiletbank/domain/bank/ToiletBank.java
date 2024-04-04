package toiletbank.domain.bank;

import toiletbank.constants.Banks;
import toiletbank.domain.Customer;
import toiletbank.domain.Customers;
import toiletbank.domain.account.Account;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToiletBank extends Bank {
    private final Map<Bank, Customers> banks;

    public ToiletBank() {
        super(Banks.TOILET_BANK);
        this.banks = loadBanks();
    }

    public Map<Bank, Customers> getBanks() {
        return banks;
    }

    @SuppressWarnings("unchecked")
    private Map<Bank, Customers> loadBanks() {
        Map<Bank, Customers> loadBanks = new HashMap<>();


        List<Bank> banks = new ArrayList<>();
        banks.add(new HanaBank());
        banks.add(new KbBank());
        banks.add(this);

        for (Bank bank : banks) {
            File customerFile = new File("src/main/java/toiletbank/repository/",
                    bank.getName().replace(" ","") + ".dat");
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(customerFile))) {
                loadBanks.put(bank,(Customers) ois.readObject()) ;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return loadBanks;
    }

    public Map<Bank, List<List<String>>> getAccounts(Customer customer){
        Map<Bank, List<List<String>>> result = new HashMap<>();
        DecimalFormat df = new DecimalFormat("#,###");

        for (Map.Entry<Bank, Customers> banksEntry : banks.entrySet()) {
            List<List<String>> accounts = new ArrayList<>();
            Map<Customer, List<Account>> customers = banksEntry.getValue().getCustomers();
            List<Account> customerAccounts = customers.get(customer);
            if (customerAccounts != null) {
                for (Account account : customerAccounts) {
                    accounts.add(List.of(account.getType().getName(), account.getNumber(), df.format(account.getBalance())));
                    System.out.println(List.of(account.getType().getName(), account.getNumber(), df.format(account.getBalance())));
                }
            }
            result.put(banksEntry.getKey(), accounts);
        }

        return result;
    }
}
