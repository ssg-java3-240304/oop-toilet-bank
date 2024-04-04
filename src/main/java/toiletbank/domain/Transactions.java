package toiletbank.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Transactions implements Serializable {
    private final List<Transaction> transactions;

    public Transactions() {
        transactions = new ArrayList<>();
    }

    public void add(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Transaction transaction : transactions) {
            sb.append(transaction.toString() + "\n");
        }

        return sb.toString();
    }
}
