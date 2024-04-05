package toiletbank.domain.customer;

import toiletbank.domain.account.Account;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import toiletbank.domain.customer.Customer;

public class Customers implements Serializable {
    private final Map<Customer, List<Account>> customers;

    public Customers() {
        this.customers = new HashMap<>();
    }

    public Map<Customer, List<Account>> getCustomers() {
        return customers;
    }
}
