package com.example.jyuen1.jyuen1_sizebook;

/**
 * Created by Jennifer on 2017-02-02.
 */

/**
 * Thrown by PersonContainer class when the user tries to remove a person
 * from an empty person list.  By design of this application (i.e. user
 * can only remove within a record), this exception should never be thrown.
 * But it is there in the event of design changes.
 */
public class EmptyContainerException extends Exception {
    /**
     * Constructor for EmptyContainerException that takes no parameters
     */
    public EmptyContainerException() {

    }

    /**
     * Constructor for EmptyContainerException that takes a single parameter
     * @param detailMessage contains detailed information about the cause
     *                      of the exception.
     */
    public EmptyContainerException(String detailMessage) {
        super(detailMessage);
    }
}
