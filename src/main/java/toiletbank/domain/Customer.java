package toiletbank.domain;

public class Customer {
    private String name;
    private String rrn; // 주민번호

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
