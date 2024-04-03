package toiletbank.domain.account;

import toiletbank.constant.AccountType;
import toiletbank.constant.Bank;

import java.math.BigInteger;
import java.util.Scanner;

abstract class Account {
    private Bank bank;
    private AccountType type;
    private BigInteger balance;
    private double interestRate;
    private String accountNumber;
//    private String number;                // 계좌 번호 필드명이 더 구체적이면 좋을 것 같아서 number에서 accountNumber로 변경하였습니다.
    private String password;
//    private String UserName; // 미정

    public Account(Bank bank, AccountType type, BigInteger balance, double interestRate, String password) {   // AccountType에 뭐가 들어가는지 잘 모르겠어요
        this.bank = bank;
        this.type = type;
        this.balance = balance;
        this.interestRate = interestRate;
        this.accountNumber = String.valueOf((int) (Math.random() * 9000) + 1000);      // 1000 ~ 9999, 계좌번호 4자리 랜덤으로 생성
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
        return accountNumber;
    }

    public String getPassword() {
        return password;
    }

    // 이자 지급 메소드
    // 이자 지급 방식 구현이 어려워 보류
    abstract void payInterest();

}
