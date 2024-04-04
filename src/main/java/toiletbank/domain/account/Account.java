package toiletbank.domain.account;

import java.io.Serializable;
import toiletbank.constants.AccountType;
import toiletbank.constants.Banks;
import java.math.BigInteger;
import java.util.Objects;

import toiletbank.domain.Transactions;

public abstract class Account implements Serializable {

    private static Integer value =0;
    private final Transactions transactions;
    private final Banks banks;
    private final AccountType type;
    private BigInteger balance;
    private final double interestRate;
    private final String number;
    private final String password;

    public Account(Banks banks, AccountType type, BigInteger balance, double interestRate, String password) {   // AccountType에 뭐가 들어가는지 잘 모르겠어요
        this.banks = banks;
        this.type = type;
        this.balance = balance;
        this.interestRate = interestRate;
        this.number = "1234-"+  String.format("%06d", value++);// 10자리
        this.password = password;
        this.transactions = new Transactions();
    }

    public Banks getBank() {
        return banks;
    }

    public AccountType getType() {
        return type;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public String getNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }

    public Transactions getTransactions() {
        return transactions;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    // 이자 지급 메소드
    // 이자 지급 방식 구현이 어려워 보류
    abstract void payInterest();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return banks == account.banks && type == account.type && Objects.equals(number, account.number) && Objects.equals(password, account.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(banks, type, number, password);
    }
}
