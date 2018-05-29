package com.example.model.pojos;

import com.example.model.exeptions.DiscountException;

import java.time.LocalDate;

public class Discount{
    private LocalDate fromDate;
    private LocalDate toDate;
    private double percent;

    public Discount(LocalDate fromDate, LocalDate toDate, double percent) throws DiscountException {
        setFromDate(fromDate);
        setToDate(toDate);
        setPercent(percent);
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) throws DiscountException {
        if (toDate.isBefore(fromDate))
            throw new DiscountException("Incorret date.");
        this.toDate = toDate;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) throws DiscountException {
       if (percent < 0)
            throw new DiscountException("Incorrenct value.");
       this.percent = percent;
    }

    public boolean isCurrent(){
        return (fromDate.isBefore(LocalDate.now()) || fromDate.equals(LocalDate.now()))
                && (toDate.equals(LocalDate.now()) || toDate.isAfter(LocalDate.now()));
    }

}
