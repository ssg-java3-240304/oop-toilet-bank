package toiletbank.domain;

import toiletbank.constant.AccountType;
import toiletbank.constant.Bank;

import java.math.BigInteger;

public class Account {
    private Bank bank;
    private AccountType type;
    private BigInteger balance;
    private double interestRate;
    private String number;
    private String password;
//    private String UserName; // 미정


}
