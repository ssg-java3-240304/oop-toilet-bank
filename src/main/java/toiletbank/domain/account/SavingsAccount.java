package toiletbank.domain.account;

import toiletbank.constants.AccountType;
import toiletbank.constants.Bank;
import java.math.BigInteger;

public class SavingsAccount extends Account {   // 보통 예금 계좌

    public SavingsAccount(String password) {    // 보통 예금 계좌는 최초 개설 시 0원으로 개설이 가능하므로, 생성자에는 비밀번호 매개변수만 작성
        super(Bank.TOILET_BANK, AccountType.SAVINGS_ACCOUNT, BigInteger.valueOf(0), 0.001, password);
    }

    @Override
    void payInterest() {
        // 하루이자 = (잔액 * 0.001 (기본금리)) / 365
        // getBalance().divide(BigInteger.valueOf(10000)).divide(BigInteger.valueOf(365));
        // Timer 스레드 ??
    }
}
