package toiletbank.domain.account;

import toiletbank.constants.AccountType;
import toiletbank.constants.Bank;

import java.math.BigInteger;

abstract class Account {
    private Bank bank;
    private AccountType type;
    private BigInteger balance;
    private double interestRate;
    private String number;
    private String password;

    public Account(Bank bank, AccountType type, BigInteger balance, double interestRate, String password) {   // AccountType에 뭐가 들어가는지 잘 모르겠어요
        this.bank = bank;
        this.type = type;
        this.balance = balance;
        this.interestRate = interestRate;
        this.number = String.valueOf((int) (Math.random() * 9000) + 1000);      // 1000 ~ 9999, 계좌번호 4자리 랜덤으로 생성
        this.password = password;
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

    public String getAccountNumber() {
        return number;
    }

    public String getPassword() {
        return password;
    }

    // 이자 지급 메소드
    // 이자 지급 방식 구현이 어려워 보류
    abstract void payInterest();

}
