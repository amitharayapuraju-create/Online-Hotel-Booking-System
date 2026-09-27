package com.booking.service;

import com.booking.dao.PaymentDAO;
import com.booking.model.Payment;

import java.util.List;

public class PaymentService {

    private final PaymentDAO paymentDAO;

    public PaymentService() {
        this.paymentDAO = new PaymentDAO();
    }

    // ================= CREATE =================

    public Long createPayment(Payment payment) {
        return paymentDAO.createPayment(payment);
    }

    // ================= READ BY ID =================

    public Payment getPaymentById(Long paymentId) {
        return paymentDAO.getPaymentById(paymentId);
    }

    // ================= READ BY BOOKING ID =================

    public List<Payment> getPaymentsByBookingId(Long bookingId) {
        return paymentDAO.getPaymentsByBookingId(bookingId);
    }

    // ================= READ ALL =================

    public List<Payment> getAllPayments() {
        return paymentDAO.getAllPayments();
    }

    // ================= UPDATE =================

    public boolean updatePayment(Payment payment) {
        return paymentDAO.updatePayment(payment);
    }

    // ================= DELETE =================

    public boolean deletePayment(Long paymentId) {
        return paymentDAO.deletePayment(paymentId);
    }
}