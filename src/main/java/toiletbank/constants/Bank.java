package toiletbank.constants;

public enum Bank {
    TOILET_BANK("토일렛은행"),
    KB_BANK("국민은행"),
    HANA_BANK("하나은행");

    private final String name;

    Bank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
