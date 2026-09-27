package com.booking.controller;

import com.booking.model.Payment;

public class PaymentControllerTest {

    public static void main(String[] args) {

        System.out.println("==============================");
        System.out.println("     PAYMENT CONTROLLER TEST");
        System.out.println("==============================");

        PaymentController paymentController =
                new PaymentController();

        // ================= INVALID CREATE =================

        System.out.println("\n--- TEST CREATE WITH NULL ---");

        Long paymentId =
                paymentController.createPayment(null);

        System.out.println("Result: " + paymentId);


        // ================= INVALID GET BY ID =================

        System.out.println("\n--- TEST GET PAYMENT WITH INVALID ID ---");

        Payment payment =
                paymentController.getPaymentById(-1L);

        System.out.println("Result: " + payment);


        // ================= GET BY BOOKING ID =================

        System.out.println("\n--- TEST GET PAYMENTS BY INVALID BOOKING ID ---");

        paymentController.getPaymentsByBookingId(-1L);


        // ================= GET ALL =================

        System.out.println("\n--- TEST GET ALL PAYMENTS ---");

        paymentController.getAllPayments();


        // ================= INVALID UPDATE =================

        System.out.println("\n--- TEST UPDATE WITH NULL ---");

        boolean updated =
                paymentController.updatePayment(null);

        System.out.println("Updated: " + updated);


        // ================= INVALID DELETE =================

        System.out.println("\n--- TEST DELETE WITH INVALID ID ---");

        boolean deleted =
                paymentController.deletePayment(-1L);

        System.out.println("Deleted: " + deleted);


        System.out.println("\n==============================");
        System.out.println("   PAYMENT CONTROLLER TEST DONE");
        System.out.println("==============================");
    }
}