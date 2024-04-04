package toiletbank.controller;

import toiletbank.view.InputView;
import toiletbank.view.OutputView;

public class Controller {

    private final InputView inputView;
    private final OutputView outputView;

    public Controller() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public static void main(String[] args) {

    }

    private void workBank() {
        while (true) {
            switch (inputView.getWork()) {
                case "1":

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

//    private void AccountTypeInput() {
//
//
//        while (true) {
//
//            switch (inputView.getType()) {
////                case "1": new SavingsAccount(initializePassword()); break;
////                case "2": new TermDeposit(initializePassword()); break;
////                case "3": new FixedDeposit(initializePassword()); break;
//                default:
//                    System.out.println("> 잘못 입력하셨습니다.");
//            }
//        }
//    }
}
