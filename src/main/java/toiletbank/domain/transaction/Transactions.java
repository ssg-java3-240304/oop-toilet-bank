package toiletbank.domain.transaction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import toiletbank.domain.transaction.Transaction;

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
        transactions.sort(Comparator.comparing((Transaction o) -> o.getDate().toString()).reversed());
        StringBuilder sb = new StringBuilder();

        for (Transaction transaction : transactions) {
            sb.append(transaction.toString()).append("\n");
        }

        return sb.toString();
    }
}
