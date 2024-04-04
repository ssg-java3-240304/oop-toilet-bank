package toiletbank;

import toiletbank.controller.Controller;

public class Application {
    public static void main(String[] args) {
        // ❌❌❌❌❌❌❌❌❌❌❌❌❌ 주석 절대로 해제하지마세요. ❌❌❌❌❌❌❌❌❌❌❌❌❌
        //Repository repository = new Repository();

        Controller controller = new Controller();
        controller.run();
    }
}
