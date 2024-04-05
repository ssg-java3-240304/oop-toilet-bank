package toiletbank.view;

import toiletbank.domain.Customer;
import java.math.BigInteger;
import java.util.Scanner;

public class InputView {
    private final Scanner sc = new Scanner(System.in);

    public String getPassword() {
        System.out.println("비밀번호를 입력해주세요.(4자리)");
        return sc.next();
    }

    public BigInteger getAmount() {
        System.out.println("저축할 금액을 입력해주세요.");
        return sc.nextBigInteger();
    }

    public String getWork() {
        System.out.print("""
                =========================
                   은행 업무를 선택해주세요.
                =========================
                1. 계좌 잔액 조회
                2. 계좌 이체
                3. Toilet Bank 계좌 생성
                4. Toilet Bank 계좌 삭제
                5. 종료
                =========================
                입력 : """);
        return sc.next();
    }

    public String getType() {
        System.out.print("""
                ===============================
                   생성할 계좌 타입을 입력해주세요.
                ===============================
                 1.보통예금(입출금 통장)
                 2.정기예금
                 3.정기적금
                =========================
                입력 : """);
        return sc.next();
    }

    // 이름/주민번호 입력 메소드
    public Customer getCustomerInfor() {
        System.out.println("고객님의 이름과 주민번호를 입력해주세요.");
        System.out.print("이름: ");
        String name = sc.next();
        System.out.print("주민번호: ");
        String rnn = sc.next();
        return new Customer(name, rnn);
    }
}
