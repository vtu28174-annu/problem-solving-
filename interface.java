```java
import java.util.*;

interface Payment {
    void pay(double amount);
}

class CreditCardPayment implements Payment {
    public void pay(double amount) {
        System.out.println("CreditCard " + String.format("%.2f", amount));
    }
}

class UPIPayment implements Payment {
    public void pay(double amount) {
        System.out.println("UPI " + String.format("%.2f", amount));
    }
}

class NetBankingPayment implements Payment {
    public void pay(double amount) {
        System.out.println("NetBanking " + String.format("%.2f", amount));
    }
}

abstract class PaymentProcessor {
    abstract double processPayment(Payment payment, double amount);
}

class OnlinePaymentProcessor extends PaymentProcessor {
    double processPayment(Payment payment, double amount) {
        double fee = 0;

        if (payment instanceof CreditCardPayment) {
            fee = amount * 0.02;
        } else if (payment instanceof UPIPayment) {
            fee = amount * 0.01;
        } else if (payment instanceof NetBankingPayment) {
            fee = amount * 0.015;
        }

        return amount + fee;
    }
}

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        OnlinePaymentProcessor processor = new OnlinePaymentProcessor();

        for (int i = 0; i < n; i++) {
            int type = sc.nextInt();
            double amount = sc.nextDouble();

            Payment payment;
            String name;

            if (type == 1) {
                payment = new CreditCardPayment();
                name = "CreditCard";
            } else if (type == 2) {
                payment = new UPIPayment();
                name = "UPI";
            } else {
                payment = new NetBankingPayment();
                name = "NetBanking";
            }

            double finalAmount = processor.processPayment(payment, amount);

            System.out.printf("%s %.2f%n", name, finalAmount);
        }

        sc.close();
    }
}
```