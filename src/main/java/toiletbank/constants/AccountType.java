package toiletbank.constants;

public enum AccountType {
    SAVINGS_ACCOUNT("보통예금(입출금 통장)"),
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


