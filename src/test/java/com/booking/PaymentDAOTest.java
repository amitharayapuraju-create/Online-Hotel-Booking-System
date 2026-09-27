package com.booking;

import com.booking.dao.PaymentDAO;
import com.booking.model.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PaymentDAOTest {

    public static void main(String[] args) {

        PaymentDAO paymentDAO = new PaymentDAO();

        // =====================================================
        // CREATE PAYMENT
        // =====================================================

        System.out.println("=================================");
        System.out.println("       CREATE PAYMENT");
        System.out.println("=================================");

        Payment payment = new Payment();

        // IMPORTANT:
        // This booking ID must already exist in your database.
        payment.setBookingId(4L);

        payment.setAmount(new BigDecimal("500.00"));
        payment.setPaymentStatus("SUCCESS");
        payment.setTransactionRef("TXN_TEST_001");
        payment.setPaidAt(LocalDateTime.now());

        Long paymentId = paymentDAO.createPayment(payment);

        System.out.println("Created Payment ID: " + paymentId);

        if (paymentId == null) {
            System.out.println("Payment creation failed.");
            return;
        }


        // =====================================================
        // READ PAYMENT BY ID
        // =====================================================

        System.out.println();
        System.out.println("=================================");
        System.out.println("       READ PAYMENT");
        System.out.println("=================================");

        Payment savedPayment =
                paymentDAO.getPaymentById(paymentId);

        if (savedPayment != null) {

            System.out.println("Payment ID: "
                    + savedPayment.getPaymentId());

            System.out.println("Booking ID: "
                    + savedPayment.getBookingId());

            System.out.println("Amount: "
                    + savedPayment.getAmount());

            System.out.println("Payment Status: "
                    + savedPayment.getPaymentStatus());

            System.out.println("Transaction Ref: "
                    + savedPayment.getTransactionRef());

            System.out.println("Paid At: "
                    + savedPayment.getPaidAt());

            System.out.println("Created At: "
                    + savedPayment.getCreatedAt());

        } else {

            System.out.println("Payment could not be found.");
        }


        // =====================================================
        // READ PAYMENTS BY BOOKING ID
        // =====================================================

        System.out.println();
        System.out.println("=================================");
        System.out.println("   PAYMENTS BY BOOKING ID");
        System.out.println("=================================");

        List<Payment> payments =
                paymentDAO.getPaymentsByBookingId(
                        payment.getBookingId());

        System.out.println(
                "Payments found: " + payments.size()
        );

        for (Payment p : payments) {

            System.out.println(
                    "Payment ID: " + p.getPaymentId()
                            + " | Amount: " + p.getAmount()
                            + " | Status: " + p.getPaymentStatus()
            );
        }


        // =====================================================
        // READ ALL PAYMENTS
        // =====================================================

        System.out.println();
        System.out.println("=================================");
        System.out.println("       ALL PAYMENTS");
        System.out.println("=================================");

        List<Payment> allPayments =
                paymentDAO.getAllPayments();

        System.out.println(
                "Total payments: " + allPayments.size()
        );

        for (Payment p : allPayments) {

            System.out.println(
                    "Payment ID: " + p.getPaymentId()
                            + " | Booking ID: " + p.getBookingId()
                            + " | Amount: " + p.getAmount()
                            + " | Status: " + p.getPaymentStatus()
            );
        }


        // =====================================================
        // UPDATE PAYMENT
        // =====================================================

        System.out.println();
        System.out.println("=================================");
        System.out.println("       UPDATE PAYMENT");
        System.out.println("=================================");

        savedPayment.setAmount(
                new BigDecimal("750.00")
        );

        savedPayment.setPaymentStatus("SUCCESS");

        savedPayment.setTransactionRef(
                "TXN_TEST_UPDATED"
        );

        boolean updated =
                paymentDAO.updatePayment(savedPayment);

        System.out.println(
                "Payment updated: " + updated
        );


        // =====================================================
        // READ UPDATED PAYMENT
        // =====================================================

        System.out.println();
        System.out.println("=================================");
        System.out.println("       READ UPDATED PAYMENT");
        System.out.println("=================================");

        Payment updatedPayment =
                paymentDAO.getPaymentById(paymentId);

        if (updatedPayment != null) {

            System.out.println("Payment ID: "
                    + updatedPayment.getPaymentId());

            System.out.println("Amount: "
                    + updatedPayment.getAmount());

            System.out.println("Payment Status: "
                    + updatedPayment.getPaymentStatus());

            System.out.println("Transaction Ref: "
                    + updatedPayment.getTransactionRef());
        }


        // =====================================================
        // DELETE PAYMENT
        // =====================================================

        System.out.println();
        System.out.println("=================================");
        System.out.println("       DELETE PAYMENT");
        System.out.println("=================================");

        boolean deleted =
                paymentDAO.deletePayment(paymentId);

        System.out.println(
                "Payment deleted: " + deleted
        );


        // =====================================================
        // TEST COMPLETE
        // =====================================================

        System.out.println();
        System.out.println("=================================");
        System.out.println("   PAYMENT DAO CRUD TEST DONE");
        System.out.println("=================================");
    }
}