package service;

import model.Payment;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PaymentService {

    private static final List<Payment> paymentDB = new ArrayList<>();
    static {
        //Add dummy data to payment table
        Payment p1 = new Payment("R-0001","123456789v", 1, BigDecimal.valueOf(100000), BigDecimal.valueOf(30000), 1, BigDecimal.valueOf(20000), 0, BigDecimal.valueOf(10000), "Test");
        Payment p2 = new Payment("R-0002","234567890v", 2, BigDecimal.valueOf(200000), BigDecimal.valueOf(50000), 1, BigDecimal.valueOf(20000), 0, BigDecimal.valueOf(30000), "Test");
        paymentDB.add(p1);
        paymentDB.add(p2);
    }

    public PaymentService(){

    }

    public void savePayment(Payment payment){
        paymentDB.add(payment);
    }

    public void updatePayment(Payment payment){
        Payment p = findPayment(payment.getReceiptNumber());
        int index = paymentDB.indexOf(p);
        paymentDB.set(index, payment);
    }

    public void deletePayment(String receiptNo){
        Payment payment = findPayment(receiptNo);
        paymentDB.remove(payment);
    }

    public List<Payment> findAllPayments(){
        return paymentDB;
    }

    public Payment findPayment(String receiptNo){
        for (Payment payment : paymentDB) {
            if (payment.getReceiptNumber().equals(receiptNo)){
                return payment;

            }
        }
        return null;
    }

    public List<Payment> findPayments(String query){
        List<Payment> result = new ArrayList<>();

        for (Payment payment : paymentDB) {

            if (payment.getReceiptNumber().contains(query) ||
                payment.getCourseId() == Integer.parseInt(query) ||
                payment.getNic().contains(query)){
                result.add(payment);
            }
        }
        return result;
    }
}
