package toiletbank.view;

import toiletbank.domain.Transactions;
import toiletbank.domain.bank.Bank;
import java.util.List;
import java.util.Map;

public class OutputView {

    // 이동될 것 사용금지
    public void printUnableToCreateAccount() {  // 계좌 생성 불가 출력
        // 정기 예금 또는 정기 적금 계좌 생성 시 입출금 통장이 없다면 최초 저축 금액 이체할 계좌가 없어 생성 불가
        String str = """
                보유하고 계신 입출금 통장이 없어, 정기 예금 또는 정기 적금 계좌 생성이 불가합니다.
                먼저, 입출금 통장을 개설해주세요.""";
        System.out.println(str);
    }

    public void printAccountDetails(List<String> accountInfo, Transactions transactions) {
        System.out.println("계좌 은행 : " + accountInfo.get(0));
        System.out.println("계좌 타입 : " + accountInfo.get(1));
        System.out.println("계좌 번호 : " + accountInfo.get(2));
        System.out.println("이자율 : " + accountInfo.get(3));
        System.out.println("잔액 : " + accountInfo.get(4));
        System.out.println();
        System.out.println("거래기록");
        System.out.println(transactions);
    }

    public void printAccounts(Map<Bank, List<List<String>>> customers) {
        int order1 = 1;
        System.out.println();
        System.out.println("계좌를 선택해 주세요. (ex. 1-1)");
        for (Map.Entry<Bank, List<List<String>>> bankEntry : customers.entrySet()) {
            System.out.println((order1++)+". "+bankEntry.getKey().getName());
            int order2 = 1;
            for (List<String> account : bankEntry.getValue()) {

                System.out.println("    -" + (order2++) + ". " + account.get(0) + " : " + account.get(1) + " / "
                        + account.get(2) + "원");
            }
            if (bankEntry.getValue().isEmpty()) {
                System.out.println("    - 없음");
            }
        }
        System.out.println();
    }

    public void printError(String message) {
        System.out.println(message);
    }
}