package toiletbank;

import toiletbank.domain.Customer;
import toiletbank.domain.Transaction;
import toiletbank.domain.Transactions;
import toiletbank.domain.account.Account;
import toiletbank.domain.account.SavingsAccount;
import toiletbank.domain.bank.ToiletBank;
import toiletbank.repository.Repository;
import toiletbank.view.OutputView;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        // ❌❌❌❌❌❌❌❌❌❌❌❌❌ 주석 절대로 해제하지마세요. ❌❌❌❌❌❌❌❌❌❌❌❌❌
//        Repository repository = new Repository();

        // test code 입니다. 삭제될 예정
        ToiletBank toiletBank = new ToiletBank("ToiletBank");
        for (Map.Entry<Customer, List<Account>> customerListEntry : toiletBank.getCustomers().entrySet()) {
            System.out.print(customerListEntry.getKey().getName());
            for (Account account : customerListEntry.getValue()) {
                System.out.println(account.getBalance());
                System.out.println(account.getBank());
                System.out.println(account.getType());
            }
        }

        // transaction test code
        Transactions transactions = new Transactions();
        transactions.add(new Transaction(LocalDateTime.of(2024, 4,1,1,1), new BigInteger("-1000"), new BigInteger("10000")));
        transactions.add(new Transaction(LocalDateTime.now(), new BigInteger("1000"), new BigInteger("10000")));
        transactions.add(new Transaction(LocalDateTime.of(2024, 4,3,1,1), new BigInteger("-1000"), new BigInteger("10000")));

//        System.out.println(transactions);


        // printAccountDetails test code
        OutputView outputView = new OutputView();



        System.out.println(toiletBank.getCustomers().get(new Customer("변성일", "980101-2133336")));
        outputView.printAccountDetails(toiletBank.getAccountDetails(new Customer("변성일", "980101-2133336"),
                toiletBank.getCustomers().get(new Customer("변성일", "980101-2133336")).get(0)),
                transactions);
    }
}
