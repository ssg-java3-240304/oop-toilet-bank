package toiletbank.domain.account;

import java.io.Serializable;
import toiletbank.constants.AccountType;
import toiletbank.constants.Banks;
import java.math.BigInteger;
import java.util.Objects;
import toiletbank.domain.Transactions;

public abstract class Account implements Serializable {
    private static Integer value = 0;
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
        this.number = "1234-" + String.format("%06d", value++);// 10자리
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

//    //입금 메서드
//
//    // amount(입금금액)
//    public void deposit(BigInteger amount) {
//        if (isAmountValid(amount)) {
//            balance = balance.add(amount);
//        } else {
//            System.out.println("유효하지 않은 금액입니다.");
//        }
//    }
//
//    // 출금 메서드
//    public void withdraw(BigInteger amount) {
//        if (isAmountValid(amount) && balance.subtract(amount).compareTo(BigInteger.ZERO) >= 0) {
//            balance = balance.subtract(amount);
//        } else {
//            System.out.println("유효하지 않은 금액이거나 잔액이 부족합니다.");
//        }
//    }
//
//    // 금액 검증 메서드
//    private boolean isAmountValid(BigInteger amount) {
//        return amount.compareTo(BigInteger.ZERO) > 0;
//    }
//
//    // 계좌 이체 메서드
//    public static void transfer(Account from, Account to, BigInteger amount) {
//
//
//        // 계좌 종류 검증: 입출금 통장인지 확인
//        if (!from.isSavingsOrCheckingAccount()) {
//            System.out.println("이체 실패: 입출금 통장이 아닙니다.");
//            return;
//        }
//
//        // 잔액이 0원인지 확인
//        if (from.getBalance().compareTo(BigInteger.ZERO) == 0) {
//            System.out.println("이체 실패: 잔액이 0원입니다.");
//            return;
//        }
//
//        // 이체 로직
//        if (from.isAmountValid(amount) && from.getBalance().subtract(amount).compareTo(BigInteger.ZERO) >= 0) {
//            from.withdraw(amount);
//            to.deposit(amount);
//        } else {
//            System.out.println("이체할 수 없습니다: 유효하지 않은 금액이거나 출금 계좌의 잔액이 부족합니다.");
//        }
//
//    }
//
//    //입출금통장인지 확인
//    private boolean isSavingsOrCheckingAccount() {
//        return this.type == AccountType.SAVINGS_ACCOUNT;
//    }
//
//    // 비밀번호 검증 메소드
//    public boolean verifyPassword(String inputPassword) {
//        return this.password.equals(inputPassword);
//
//
//    }
}
