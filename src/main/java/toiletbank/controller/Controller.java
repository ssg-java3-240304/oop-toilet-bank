package toiletbank.controller;

import java.util.Scanner;
import toiletbank.domain.Customer;
import toiletbank.domain.bank.ToiletBank;
import toiletbank.view.InputView;
import toiletbank.view.OutputView;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public Controller() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void run() {
        ToiletBank toiletBank = new ToiletBank();
        workBank(toiletBank);
    }

    private void showAccounts(ToiletBank toiletBank) {
        Customer customer = new Customer("김나경", "990101-2111116");
        outputView.printAccounts(toiletBank.getAccounts(customer)); // 김나경님으로 로그인상태

        Scanner sc = new Scanner(System.in);
        String value = sc.next();

        String[] splitValue = value.split("-");
        Integer bankOrder = Integer.parseInt(splitValue[0]);
        Integer accountOrder = Integer.parseInt(splitValue[1]);
        outputView.printAccountDetails(toiletBank.getAccount(customer, bankOrder, accountOrder),
                toiletBank.getTransaction(customer, bankOrder, accountOrder));
    }


    private void workBank(ToiletBank toiletBank) {
        while (true) {
            switch (inputView.getWork()) {
                case "1":
                    showAccounts(toiletBank);
                    break;
                case "2":
                case "3":
                case "4":
                case "5":
                    return;
                default:
                    System.out.println("> 잘못 입력하셨습니다.");
            }
        }
    }
}
