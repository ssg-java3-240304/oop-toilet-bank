package toiletbank.domain;

import toiletbank.domain.account.SavingsAccount;

import java.math.BigInteger;
import java.util.Scanner;

public class ToiletBank implements Bank {
    private Scanner sc = new Scanner(System.in);

    public void menu() {    // 은행 업무 선택 메소드

        // String block문법 (여러줄, 들여쓰기 표현가능)
        String menu = """
                =========================
                   은행 업무를 선택해주세요.
                =========================
                1. 계좌 잔액 조회
                2. 계좌 이체
                3. Toilet Bank 계좌 생성
                4. Toilet Bank 계좌 삭제
                5. 종료
                =========================
                입력 : """;

        while (true) {
            System.out.print(menu);
            String choice = sc.next();
            switch (choice) {
                case "1":
                    // createAccount();
                    break;
                case "2": // driver.accelerate(); break;
                case "3": // driver.brake(); break;
                case "4": // driver.stopEngine(); break;
                case "5":
                    return; // 현재메소드를 호출한 곳을 리턴
                default:
                    System.out.println("> 잘못 입력하셨습니다.");
            }
        }
    }

}
