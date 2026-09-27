package com.booking.controller;

import com.booking.model.Payment;
import com.booking.service.PaymentService;

import java.util.List;

public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController() {
        this.paymentService = new PaymentService();
    }

    // ================= CREATE =================

    public Long createPayment(Payment payment) {

        if (payment == null) {
            System.out.println("Invalid payment");
            return null;
        }

        System.out.println("Creating payment...");

        Long paymentId = paymentService.createPayment(payment);

        if (paymentId != null) {
            System.out.println("Payment created successfully");
            System.out.println("Payment ID: " + paymentId);
        } else {
            System.out.println("Payment creation failed");
        }

        return paymentId;
    }

    // ================= READ BY ID =================

    public Payment getPaymentById(Long paymentId) {

        if (paymentId == null || paymentId <= 0) {
            System.out.println("Invalid payment ID");
            return null;
        }

        System.out.println(
                "Fetching payment with ID: " + paymentId);

        Payment payment =
                paymentService.getPaymentById(paymentId);

        if (payment != null) {
            System.out.println("Payment found");
            System.out.println(
                    "Payment ID: " + payment.getPaymentId());
        } else {
            System.out.println("Payment not found");
        }

        return payment;
    }

    // ================= READ BY BOOKING ID =================

    public List<Payment> getPaymentsByBookingId(Long bookingId) {

        if (bookingId == null || bookingId <= 0) {
            System.out.println("Invalid booking ID");
            return null;
        }

        System.out.println(
                "Fetching payments for booking ID: "
                        + bookingId);

        List<Payment> payments =
                paymentService.getPaymentsByBookingId(bookingId);

        System.out.println(
                "Total payments found: " + payments.size());

        return payments;
    }

    // ================= READ ALL =================

    public List<Payment> getAllPayments() {

        System.out.println("Fetching all payments...");

        List<Payment> payments =
                paymentService.getAllPayments();

        System.out.println(
                "Total payments: " + payments.size());

        return payments;
    }

    // ================= UPDATE =================

    public boolean updatePayment(Payment payment) {

        if (payment == null ||
                payment.getPaymentId() == null ||
                payment.getPaymentId() <= 0) {

            System.out.println("Invalid payment for update");
            return false;
        }

        System.out.println(
                "Updating payment with ID: "
                        + payment.getPaymentId());

        boolean updated =
                paymentService.updatePayment(payment);

        if (updated) {
            System.out.println("Payment updated successfully");
        } else {
            System.out.println("Payment update failed");
        }

        return updated;
    }

    // ================= DELETE =================

    public boolean deletePayment(Long paymentId) {

        if (paymentId == null || paymentId <= 0) {
            System.out.println("Invalid payment ID");
            return false;
        }

        System.out.println(
                "Deleting payment with ID: " + paymentId);

        boolean deleted =
                paymentService.deletePayment(paymentId);

        if (deleted) {
            System.out.println("Payment deleted successfully");
        } else {
            System.out.println("Payment deletion failed");
        }

        return deleted;
    }
}