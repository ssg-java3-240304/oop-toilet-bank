package toiletbank.constants;

public enum Bank {
    TOILET_BANK("Toilet Bank"),
    KB_BANK("KB Bank"),
    HANA_BANK("HANA Bank");

    private final String name;

    Bank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
