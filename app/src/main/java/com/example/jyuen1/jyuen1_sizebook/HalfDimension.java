package com.example.jyuen1.jyuen1_sizebook;

/**
 * Created by Jennifer Yuen on 2/4/2017.
 */

/**
 * This class is used to keep track of numerical values (decimal) where there
 * is at most a single digit after the decimal point and the digit is either
 * a 0 or a 5.  For example, clothing sizing units are often in half inches.
 */

public class HalfDimension {
    private int num;
    private boolean half;

    /**
     * The constructor for HalfDimension
     * @param num is the numerical portion
     * @param half is a boolean value representing the decimal portion
     *             true: .5
     *             false: .0
     */
    public HalfDimension(int num, boolean half) {
        this.num = num;
        this.half = half;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setHalf(Boolean half) {
        this.half = half;
    }

    /** getter for num attribute */
    public int getNum() {
        return num;
    }

    /** getter for half attribute */
    public boolean getHalf() {
        return half;
    }
}
