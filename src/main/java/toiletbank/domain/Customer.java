package toiletbank.domain;

import java.io.Serializable;

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

}
