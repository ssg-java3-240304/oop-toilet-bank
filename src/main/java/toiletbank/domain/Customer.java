package toiletbank.domain;

import java.io.Serializable;
import java.util.Objects;

public class Customer implements Serializable {
    private final String name;
    private final String rrn; // 주민번호

    public Customer(String name, String rrn) {
        this.name = name;
        this.rrn = rrn;
    }

    public String getName() {
        return name;
    }

    public String getRrn() {
        return rrn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) && Objects.equals(rrn, customer.rrn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, rrn);
    }
}
