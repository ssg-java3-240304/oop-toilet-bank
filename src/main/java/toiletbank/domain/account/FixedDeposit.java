package toiletbank.domain.account;

import toiletbank.constant.AccountType;
import toiletbank.constant.Bank;

import java.math.BigInteger;

public class FixedDeposit extends Account{ // 정기 적금 계좌

    public FixedDeposit(BigInteger balance, String password) {  // 정기 적금 계좌는 최초 개설 시 0원으로 개설이 불가능하므로, 생성자에는 잔액과 비밀번호 매개변수 작성
        super(Bank.TOILETBANK, AccountType.FIXEDDEPOSIT, balance, 0.038, password);
    }

    @Override
    void payInterest() {

    }
}
