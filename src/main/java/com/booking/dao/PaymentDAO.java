package com.booking.dao;

import com.booking.DBConnection;
import com.booking.model.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class PaymentDAO {

    private static final Logger logger =
            Logger.getLogger(PaymentDAO.class.getName());

    // ================= CREATE =================

    private static final String INSERT_PAYMENT =
            "INSERT INTO payment " +
                    "(booking_id, amount, payment_status, transaction_ref, paid_at) " +
                    "VALUES (?, ?, ?, ?, ?)";

    // ================= READ BY ID =================

    private static final String SELECT_PAYMENT_BY_ID =
            "SELECT payment_id, booking_id, amount, payment_status, " +
                    "transaction_ref, paid_at, created_at " +
                    "FROM payment WHERE payment_id = ?";

    // ================= READ BY BOOKING ID =================

    private static final String SELECT_PAYMENTS_BY_BOOKING_ID =
            "SELECT payment_id, booking_id, amount, payment_status, " +
                    "transaction_ref, paid_at, created_at " +
                    "FROM payment WHERE booking_id = ? " +
                    "ORDER BY payment_id";

    // ================= READ ALL =================

    private static final String SELECT_ALL_PAYMENTS =
            "SELECT payment_id, booking_id, amount, payment_status, " +
                    "transaction_ref, paid_at, created_at " +
                    "FROM payment ORDER BY payment_id";

    // ================= UPDATE =================

    private static final String UPDATE_PAYMENT =
            "UPDATE payment SET " +
                    "booking_id = ?, amount = ?, payment_status = ?, " +
                    "transaction_ref = ?, paid_at = ? " +
                    "WHERE payment_id = ?";

    // ================= DELETE =================

    private static final String DELETE_PAYMENT =
            "DELETE FROM payment WHERE payment_id = ?";


    // =========================================================
    // CREATE
    // =========================================================

    public Long createPayment(Payment payment) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     INSERT_PAYMENT,
                     Statement.RETURN_GENERATED_KEYS)) {

            stmt.setLong(1, payment.getBookingId());
            stmt.setBigDecimal(2, payment.getAmount());
            stmt.setString(3, payment.getPaymentStatus());
            stmt.setString(4, payment.getTransactionRef());

            if (payment.getPaidAt() != null) {
                stmt.setTimestamp(
                        5,
                        Timestamp.valueOf(payment.getPaidAt())
                );
            } else {
                stmt.setNull(5, Types.TIMESTAMP);
            }

            int rows = stmt.executeUpdate();

            if (rows > 0) {

                try (ResultSet rs = stmt.getGeneratedKeys()) {

                    if (rs.next()) {

                        Long generatedId = rs.getLong(1);

                        payment.setPaymentId(generatedId);

                        logger.info(
                                "Payment created successfully with ID: "
                                        + generatedId
                        );

                        return generatedId;
                    }
                }
            }

        } catch (SQLException e) {

            logger.severe(
                    "Error creating payment: " + e.getMessage()
            );
        }

        return null;
    }


    // =========================================================
    // READ BY ID
    // =========================================================

    public Payment getPaymentById(Long paymentId) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(SELECT_PAYMENT_BY_ID)) {

            stmt.setLong(1, paymentId);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {

                    Payment payment =
                            mapResultSetToPayment(rs);

                    logger.info(
                            "Payment fetched successfully with ID: "
                                    + paymentId
                    );

                    return payment;
                }
            }

        } catch (SQLException e) {

            logger.severe(
                    "Error fetching payment: " + e.getMessage()
            );
        }

        return null;
    }


    // =========================================================
    // READ BY BOOKING ID
    // =========================================================

    public List<Payment> getPaymentsByBookingId(Long bookingId) {

        List<Payment> payments = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(
                             SELECT_PAYMENTS_BY_BOOKING_ID)) {

            stmt.setLong(1, bookingId);

            try (ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {

                    Payment payment =
                            mapResultSetToPayment(rs);

                    payments.add(payment);
                }
            }

            logger.info(
                    "Payments fetched for booking ID: "
                            + bookingId
                            + ". Total: "
                            + payments.size()
            );

        } catch (SQLException e) {

            logger.severe(
                    "Error fetching payments by booking ID: "
                            + e.getMessage()
            );
        }

        return payments;
    }


    // =========================================================
    // READ ALL
    // =========================================================

    public List<Payment> getAllPayments() {

        List<Payment> payments = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(SELECT_ALL_PAYMENTS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Payment payment =
                        mapResultSetToPayment(rs);

                payments.add(payment);
            }

            logger.info(
                    "Payments fetched successfully. Total: "
                            + payments.size()
            );

        } catch (SQLException e) {

            logger.severe(
                    "Error fetching payments: "
                            + e.getMessage()
            );
        }

        return payments;
    }


    // =========================================================
    // UPDATE
    // =========================================================

    public boolean updatePayment(Payment payment) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(UPDATE_PAYMENT)) {

            stmt.setLong(1, payment.getBookingId());
            stmt.setBigDecimal(2, payment.getAmount());
            stmt.setString(3, payment.getPaymentStatus());
            stmt.setString(4, payment.getTransactionRef());

            if (payment.getPaidAt() != null) {
                stmt.setTimestamp(
                        5,
                        Timestamp.valueOf(payment.getPaidAt())
                );
            } else {
                stmt.setNull(5, Types.TIMESTAMP);
            }

            stmt.setLong(6, payment.getPaymentId());

            int rows = stmt.executeUpdate();

            if (rows > 0) {

                logger.info(
                        "Payment updated successfully with ID: "
                                + payment.getPaymentId()
                );

                return true;
            }

        } catch (SQLException e) {

            logger.severe(
                    "Error updating payment: "
                            + e.getMessage()
            );
        }

        return false;
    }


    // =========================================================
    // DELETE
    // =========================================================

    public boolean deletePayment(Long paymentId) {

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt =
                     conn.prepareStatement(DELETE_PAYMENT)) {

            stmt.setLong(1, paymentId);

            int rows = stmt.executeUpdate();

            if (rows > 0) {

                logger.info(
                        "Payment deleted successfully with ID: "
                                + paymentId
                );

                return true;
            }

        } catch (SQLException e) {

            logger.severe(
                    "Error deleting payment: "
                            + e.getMessage()
            );
        }

        return false;
    }


    // =========================================================
    // RESULT SET → PAYMENT OBJECT
    // =========================================================

    private Payment mapResultSetToPayment(ResultSet rs)
            throws SQLException {

        Payment payment = new Payment();

        payment.setPaymentId(
                rs.getLong("payment_id")
        );

        payment.setBookingId(
                rs.getLong("booking_id")
        );

        payment.setAmount(
                rs.getBigDecimal("amount")
        );

        payment.setPaymentStatus(
                rs.getString("payment_status")
        );

        payment.setTransactionRef(
                rs.getString("transaction_ref")
        );

        Timestamp paidAt =
                rs.getTimestamp("paid_at");

        if (paidAt != null) {
            payment.setPaidAt(
                    paidAt.toLocalDateTime()
            );
        }

        Timestamp createdAt =
                rs.getTimestamp("created_at");

        if (createdAt != null) {
            payment.setCreatedAt(
                    createdAt.toLocalDateTime()
            );
        }

        return payment;
    }
}