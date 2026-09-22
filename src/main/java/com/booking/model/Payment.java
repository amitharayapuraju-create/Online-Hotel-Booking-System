package com.booking.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Payment {

    private Long paymentId;
    private Long bookingId;
    private BigDecimal amount;
    private String paymentStatus;
    private String transactionRef;
    private LocalDateTime paidAt;
    private LocalDateTime createdAt;

    public Payment() {
    }

    public Payment(Long paymentId, Long bookingId, BigDecimal amount,
                   String paymentStatus, String transactionRef,
                   LocalDateTime paidAt, LocalDateTime createdAt) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.amount = amount;
        this.paymentStatus = paymentStatus;
        this.transactionRef = transactionRef;
        this.paidAt = paidAt;
        this.createdAt = createdAt;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getTransactionRef() {
        return transactionRef;
    }

    public void setTransactionRef(String transactionRef) {
        this.transactionRef = transactionRef;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}