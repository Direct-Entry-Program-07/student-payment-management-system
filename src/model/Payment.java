package model;

import java.math.BigDecimal;

public class Payment {
    private String receiptNumber;
    private String nic;
    private int courseId;
    private BigDecimal totalFee;
    private BigDecimal remaining;
    private int paymentReason;
    private BigDecimal paymentAmount;
    private int paymentMethod;
    private BigDecimal balance;
    private String note;

    public Payment() {
    }

    public Payment(String receiptNumber, String nic, int courseId, BigDecimal totalFee, BigDecimal remaining, int paymentReason, BigDecimal paymentAmount, int paymentMethod, BigDecimal balance, String note) {
        this.nic = nic;
        this.courseId = courseId;
        this.totalFee = totalFee;
        this.remaining = remaining;
        this.paymentReason = paymentReason;
        this.paymentAmount = paymentAmount;
        this.paymentMethod = paymentMethod;
        this.balance = balance;
        this.note = note;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public BigDecimal getTotalFee() {
        return totalFee;
    }

    public void setTotalFee(BigDecimal totalFee) {
        this.totalFee = totalFee;
    }

    public BigDecimal getRemaining() {
        return remaining;
    }

    public void setRemaining(BigDecimal remaining) {
        this.remaining = remaining;
    }

    public int getPaymentReason() {
        return paymentReason;
    }

    public void setPaymentReason(int paymentReason) {
        this.paymentReason = paymentReason;
    }

    public BigDecimal getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(BigDecimal paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public int getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(int paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getReceiptNumber() {
        return receiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "receiptNumber=" + receiptNumber +
                ", nic='" + nic + '\'' +
                ", courseId=" + courseId +
                ", totalFee=" + totalFee +
                ", remaining=" + remaining +
                ", paymentReason=" + paymentReason +
                ", paymentAmount=" + paymentAmount +
                ", paymentMethod=" + paymentMethod +
                ", balance=" + balance +
                ", note='" + note + '\'' +
                '}';
    }
}
