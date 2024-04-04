package toiletbank.view;

public class OutputView {

    // 이동될 것 사용금지
    public void printUnableToCreateAccount() {  // 계좌 생성 불가 출력
        // 정기 예금 또는 정기 적금 계좌 생성 시 입출금 통장이 없다면 최초 저축 금액 이체할 계좌가 없어 생성 불가
        String str = """
                보유하고 계신 입출금 통장이 없어, 정기 예금 또는 정기 적금 계좌 생성이 불가합니다.
                먼저, 입출금 통장을 개설해주세요.""";
        System.out.println(str);
    }
}