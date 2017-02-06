package com.example.jyuen1.jyuen1_sizebook;

/**
 * Created by Jennifer Yuen on 2/3/2017.
 */

/**
 * Thrown when the name is too short.
 * Name length determined by initiator.
 */
public class nameTooShortException extends Exception {
    /**
     * Constructor for nameTooShortException that takes no parameters
     */
    public nameTooShortException() {

    }

    /**
     * Constructor for nameTooShortException that takes a single parameter
     * @param detailMessage contains detailed information about the exception
     *                      i.e. name length must be >= 3.
     */
    public nameTooShortException(String detailMessage) {
        super(detailMessage);
    }
}
