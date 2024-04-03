package toiletbank.domain.bank;

import java.util.HashMap;
import java.util.Map;
import toiletbank.domain.Customer;
import toiletbank.domain.account.Account;

public abstract class Bank {
    private final Map<Customer, Account> customers;
    private final String name;

    public Bank(String name) {
        this.customers = new HashMap<>();
        this.name = name;
    }

    public Map<Customer, Account> getCustomers() {
        return customers;
    }

    public String getName() {
        return name;
    }
}
