package com.example.windows;

import java.time.LocalDate;

public class Payment {
    String ReceiptNumber;
    String ProcessedBy;
    LocalDate PaymentDate;
    String ProcessedFor;
    LocalDate FirstDayOccupied;
    LocalDate LastDayOccupied;
    Integer TotalNights;
    Double AmountCharged;
    Double PhoneUse;
    Double SubTotal;
    Double TaxRate;
    Double TaxAmount;
    Double TotalAmountPaid;

    public Payment(String receiptNumber, String processedBy, LocalDate paymentDate, String processedFor, LocalDate firstDayOccupied, LocalDate lastDayOccupied, Integer totalNights, Double amountCharged, Double phoneUse,Double subTotal, Double taxRate, Double taxAmount, Double totalAmountPaid) {

        ReceiptNumber = receiptNumber;
        ProcessedBy = processedBy;
        PaymentDate = paymentDate;
        ProcessedFor = processedFor;
        FirstDayOccupied = firstDayOccupied;
        LastDayOccupied = lastDayOccupied;
        TotalNights = totalNights;
        AmountCharged = amountCharged;
        PhoneUse = phoneUse;
        SubTotal = subTotal;
        TaxRate = taxRate;
        TaxAmount = taxAmount;
        TotalAmountPaid = totalAmountPaid;
    }

    public Double getSubTotal() {
        return SubTotal;
    }

    public void setSubTotal(Double subTotal) {
        SubTotal = subTotal;
    }

    public String getReceiptNumber() {
        return ReceiptNumber;
    }

    public void setReceiptNumber(String receiptNumber) {
        ReceiptNumber = receiptNumber;
    }

    public String getProcessedBy() {
        return ProcessedBy;
    }

    public void setProcessedBy(String processedBy) {
        ProcessedBy = processedBy;
    }

    public LocalDate getPaymentDate() {
        return PaymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        PaymentDate = paymentDate;
    }

    public String getProcessedFor() {
        return ProcessedFor;
    }

    public void setProcessedFor(String processedFor) {
        ProcessedFor = processedFor;
    }

    public LocalDate getFirstDayOccupied() {
        return FirstDayOccupied;
    }

    public void setFirstDayOccupied(LocalDate firstDayOccupied) {
        FirstDayOccupied = firstDayOccupied;
    }

    public LocalDate getLastDayOccupied() {
        return LastDayOccupied;
    }

    public void setLastDayOccupied(LocalDate lastDayOccupied) {
        LastDayOccupied = lastDayOccupied;
    }

    public Integer getTotalNights() {
        return TotalNights;
    }

    public void setTotalNights(Integer totalNights) {
        TotalNights = totalNights;
    }

    public Double getAmountCharged() {
        return AmountCharged;
    }

    public void setAmountCharged(Double amountCharged) {
        AmountCharged = amountCharged;
    }

    public Double getPhoneUse() {
        return PhoneUse;
    }

    public void setPhoneUse(Double phoneUse) {
        PhoneUse = phoneUse;
    }

    public Double getTaxRate() {
        return TaxRate;
    }

    public void setTaxRate(Double taxRate) {
        TaxRate = taxRate;
    }

    public Double getTaxAmount() {
        return TaxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        TaxAmount = taxAmount;
    }

    public Double getTotalAmountPaid() {
        return TotalAmountPaid;
    }

    public void setTotalAmountPaid(Double totalAmountPaid) {
        TotalAmountPaid = totalAmountPaid;
    }
}
