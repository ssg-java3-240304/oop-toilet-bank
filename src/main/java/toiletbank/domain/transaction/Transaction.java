package toiletbank.domain.transaction;

import java.io.Serializable;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction implements Serializable {
    private LocalDateTime date;
    private BigInteger amount;
    private BigInteger balance;

    public Transaction(LocalDateTime date, BigInteger amount, BigInteger balance) {
        this.date = date;
        this.amount = amount;
        this.balance = balance;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public BigInteger getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("#,###");

        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"))
                + " ("+ df.format(amount)+ "원) "
                + (amount.intValue()>0? "입금":"송금") + " /  "
                + "잔액 "+df.format(balance)+"원";
    }
}
