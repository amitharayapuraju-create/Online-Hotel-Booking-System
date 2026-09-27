package com.booking.service;

import com.booking.model.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PaymentServiceTest {

    public static void main(String[] args) {

        PaymentService paymentService = new PaymentService();

        // =========================================================
        // CREATE PAYMENT
        // =========================================================
        System.out.println("========================================");
        System.out.println("           CREATE PAYMENT");
        System.out.println("========================================");

        Payment payment = new Payment();

        // IMPORTANT:
        // Booking ID 4 already exists in your database.
        payment.setBookingId(4L);

        payment.setAmount(new BigDecimal("500.00"));
        payment.setPaymentStatus("SUCCESS");
        payment.setTransactionRef("SERVICE_TEST_001");
        payment.setPaidAt(LocalDateTime.now());

        Long paymentId = paymentService.createPayment(payment);

        System.out.println("Created Payment ID: " + paymentId);

        if (paymentId == null) {
            System.out.println("Payment creation failed.");
            return;
        }

        // =========================================================
        // READ PAYMENT BY ID
        // =========================================================
        System.out.println();
        System.out.println("========================================");
        System.out.println("          READ PAYMENT BY ID");
        System.out.println("========================================");

        Payment fetchedPayment = paymentService.getPaymentById(paymentId);

        if (fetchedPayment != null) {
            System.out.println("Payment ID: " + fetchedPayment.getPaymentId());
            System.out.println("Booking ID: " + fetchedPayment.getBookingId());
            System.out.println("Amount: " + fetchedPayment.getAmount());
            System.out.println("Payment Status: " + fetchedPayment.getPaymentStatus());
            System.out.println("Transaction Ref: " + fetchedPayment.getTransactionRef());
        } else {
            System.out.println("Payment not found.");
        }

        // =========================================================
        // READ PAYMENTS BY BOOKING ID
        // =========================================================
        System.out.println();
        System.out.println("========================================");
        System.out.println("       READ PAYMENTS BY BOOKING ID");
        System.out.println("========================================");

        List<Payment> bookingPayments =
                paymentService.getPaymentsByBookingId(4L);

        System.out.println("Payments found: " + bookingPayments.size());

        for (Payment p : bookingPayments) {
            System.out.println(
                    "Payment ID: " + p.getPaymentId()
                            + " | Amount: " + p.getAmount()
                            + " | Status: " + p.getPaymentStatus()
            );
        }

        // =========================================================
        // READ ALL PAYMENTS
        // =========================================================
        System.out.println();
        System.out.println("========================================");
        System.out.println("            READ ALL PAYMENTS");
        System.out.println("========================================");

        List<Payment> allPayments = paymentService.getAllPayments();

        System.out.println("Total payments: " + allPayments.size());

        for (Payment p : allPayments) {
            System.out.println(
                    "Payment ID: " + p.getPaymentId()
                            + " | Booking ID: " + p.getBookingId()
                            + " | Amount: " + p.getAmount()
            );
        }

        // =========================================================
        // UPDATE PAYMENT
        // =========================================================
        System.out.println();
        System.out.println("========================================");
        System.out.println("            UPDATE PAYMENT");
        System.out.println("========================================");

        fetchedPayment.setAmount(new BigDecimal("750.00"));
        fetchedPayment.setPaymentStatus("SUCCESS");
        fetchedPayment.setTransactionRef("SERVICE_TEST_UPDATED");

        boolean updated = paymentService.updatePayment(fetchedPayment);

        System.out.println("Payment updated: " + updated);

        // Verify update
        Payment updatedPayment =
                paymentService.getPaymentById(paymentId);

        if (updatedPayment != null) {
            System.out.println("Updated Amount: " + updatedPayment.getAmount());
            System.out.println(
                    "Updated Status: " + updatedPayment.getPaymentStatus()
            );
            System.out.println(
                    "Updated Transaction Ref: "
                            + updatedPayment.getTransactionRef()
            );
        }

        // =========================================================
        // DELETE PAYMENT
        // =========================================================
        System.out.println();
        System.out.println("========================================");
        System.out.println("            DELETE PAYMENT");
        System.out.println("========================================");

        boolean deleted = paymentService.deletePayment(paymentId);

        System.out.println("Payment deleted: " + deleted);

        // =========================================================
        // TEST COMPLETE
        // =========================================================
        System.out.println();
        System.out.println("========================================");
        System.out.println("       PAYMENT SERVICE TEST DONE");
        System.out.println("========================================");
    }
}