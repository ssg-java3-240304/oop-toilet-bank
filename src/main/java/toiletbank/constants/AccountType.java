package toiletbank.constants;

public enum AccountType {
    SAVINGS_ACCOUNT("입출금통장"),
    TERM_DEPOSIT("정기예금"),
    FIXED_DEPOSIT("정기적금");

    private final String name;

    AccountType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}


