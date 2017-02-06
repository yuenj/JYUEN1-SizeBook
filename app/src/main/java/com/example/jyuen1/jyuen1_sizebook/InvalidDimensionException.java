package com.example.jyuen1.jyuen1_sizebook;

/**
 * Created by Jennifer Yuen on 2/3/2017.
 */

/**
 * Thrown when dimensions are out of valid ranges.
 * Valid ranges are determined in the Person class.
 */
public class InvalidDimensionException extends Exception {
    /**
     * Constructor for InvalidDimensionException,
     * takes no parameters
     */
    public InvalidDimensionException() {

    }

    /**
     * Constructor for InvalidDimensionException,
     * takes a single parameter.
     * @param detailMessage contains detailed information about the exception
     *                      i.e. the body part it pertains to.
     */
    public InvalidDimensionException(String detailMessage) {
        super(detailMessage);
    }
}
