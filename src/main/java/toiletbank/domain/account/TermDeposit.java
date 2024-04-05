package toiletbank.domain.account;

import toiletbank.constants.AccountType;
import toiletbank.constants.Banks;
import java.math.BigInteger;

public class TermDeposit extends Account{   // 정기 예금 계좌
    public TermDeposit(Banks bank, BigInteger balance, String password) {   // 정기 예금 계좌는 최초 개설 시 0원으로 개설이 불가능하므로, 생성자에는 잔액과 비밀번호 매개변수 작성
        super(bank, AccountType.TERM_DEPOSIT, balance, 0.034, password);
    }

    @Override
    void payInterest() {

    }
}
