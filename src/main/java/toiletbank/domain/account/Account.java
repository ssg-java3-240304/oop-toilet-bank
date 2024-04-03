package toiletbank.domain.account;

import java.util.ArrayList;
import java.util.List;
import toiletbank.constants.AccountType;
import toiletbank.constants.Bank;
import java.math.BigInteger;
import toiletbank.domain.Transaction;

public abstract class Account {

    private final List<Transaction> transactions;
    private final Bank bank;
    private final AccountType type;
    private final BigInteger balance;
    private double interestRate;
    private final String number;
    private final String password;

    public Account(Bank bank, AccountType type, BigInteger balance, double interestRate, String password) {   // AccountType에 뭐가 들어가는지 잘 모르겠어요
        this.bank = bank;
        this.type = type;
        this.balance = balance;
        this.interestRate = interestRate;
        this.number = String.valueOf((int) (Math.random() * 9000) + 1000);      // 1000 ~ 9999, 계좌번호 4자리 랜덤으로 생성
        this.password = password;
        this.transactions = new ArrayList<>();
    }

    public Bank getBank() {
        return bank;
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

    public List<Transaction> getTransactions() {
        return transactions;
    }

    // 이자 지급 메소드
    // 이자 지급 방식 구현이 어려워 보류
    abstract void payInterest();

}
