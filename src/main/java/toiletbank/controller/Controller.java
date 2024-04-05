package toiletbank.controller;

import toiletbank.domain.customer.Customer;
import toiletbank.domain.bank.ToiletBank;
import toiletbank.utility.Converter;
import toiletbank.view.InputView;
import toiletbank.view.OutputView;

public class Controller {
    private final InputView inputView;
    private final OutputView outputView;
    private final Converter converter;

    public Controller() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.converter = new Converter();
    }

    public void run() {
        ToiletBank toiletBank = new ToiletBank();
        workBank(toiletBank);
    }

    private void showAccounts(ToiletBank toiletBank) {
        Customer customer = new Customer("김나경", "990101-2111116");
        // new Customer("변성일", "980101-2133336");
        // new Customer("심재람", "980101-2122226");
        outputView.printAccounts(toiletBank.getAccounts(customer)); // 김나경님으로 로그인상태

        while (true) {
            try {
                Integer[] selectedNumber = selectNumber();

                outputView.printAccountDetails(toiletBank.getAccount(customer, selectedNumber[0], selectedNumber[1]),
                        toiletBank.getTransaction(customer, selectedNumber[0], selectedNumber[1]));
                break;
            } catch (Exception e) {
                outputView.printError("다시 입력해주세요.");
            }
        }

    }

    private Integer[] selectNumber() {
        Integer[] selectedNumber = null;

        while (true) {
            try {
                selectedNumber = converter.convertToNumbers(inputView.getAccountSelectNumber());
                break;
            } catch (IllegalArgumentException e) {
                outputView.printError("다시 입력해주세요.");
            }
        }

        return selectedNumber;
    }

    private void workBank(ToiletBank toiletBank) {
        while (true) {
            switch (inputView.getWork()) {
                case "1":
                    showAccounts(toiletBank);
                    break;
                case "2":
                    break;
                case "3":
                    break;
                case "4":
                    break;
                case "5":
                    return;
                default:
                    outputView.printError("> 잘못 입력하셨습니다.");
            }
        }
    }
}
