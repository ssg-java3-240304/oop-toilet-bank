package toiletbank.domain.bank;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import toiletbank.constants.Banks;
import toiletbank.domain.Customers;

public abstract class Bank {
    private final Integer order;
    private final Banks name;
    private final Customers customers;

    public Bank(Integer order, Banks name) {
        this.order = order;
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

    public Customers getCustomers() {
        return customers;
    }

    public String getName() {
        return name.getName();
    }

    public Integer getOrder() {
        return order;
    }
}
