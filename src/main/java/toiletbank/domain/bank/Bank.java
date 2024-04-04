package toiletbank.domain.bank;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import toiletbank.domain.Customer;
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
            System.out.println("a");
            loadedCustomers = (Map<Customer, List<Account>>) ois.readObject();
            System.out.println(loadedCustomers);
        } catch (FileNotFoundException e) {
            System.out.println("not file");

            // 파일이 없을 경우 빈 맵 반환
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return loadedCustomers;
    }


//    public List<String> getAccountDetails() {
//        Customer customer = new Customer()
//        return List.of(mapcustomers)
//    }


    public Map<Customer, List<Account>> getCustomers() {
        return customers;
    }

    public String getName() {
        return name;
    }
}
