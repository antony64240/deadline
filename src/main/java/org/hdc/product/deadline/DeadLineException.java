package org.hdc.product.deadline;


public class DeadLineException extends java.lang.Exception {

    private int vendorCode;

    public DeadLineException(String reason, int vendorCode) {
        super(reason);
        this.vendorCode = vendorCode;
    }

}
