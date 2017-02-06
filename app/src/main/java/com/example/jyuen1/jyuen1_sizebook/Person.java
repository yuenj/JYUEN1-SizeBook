package com.example.jyuen1.jyuen1_sizebook;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jennifer on 2017-01-31.
 */

/**
 * This class represents a person in a sizing book world.  That is, we are
 * interested in knowing the person's name, his/her's sizing dimensions
 * including neck, bust, chest, waist, hip, circumferences and inseam length,
 * including the date that these dimensions were last known to be valid.
 * The person also has a comment attribute where additional textual
 * information could be included about the person.
 */
public class Person {
    private String name;
    private Date date;
    private String comment;

    private HalfDimension neck;
    private HalfDimension bust;
    private HalfDimension chest;
    private HalfDimension waist;
    private HalfDimension hip;
    private HalfDimension inseam;

    /**
     * The constructor for the Person class.
     * @param name of the person.
     * @throws nameTooShortException when the name length is less than 3 characters.
     */
    public Person(String name) throws nameTooShortException {
        this.setName(name);
    }

    /**
     * Sets the name attribute of the person object.
     * @param name
     * @throws nameTooShortException when the name length is less than 3 characters.
     */
    public void setName(String name) throws nameTooShortException {
        if (name.length() < 3) {
            throw new nameTooShortException("Please enter a name (min 3 chars)!");
        } else {
            this.name = name;
        }
    }

    /**
     * Sets the date attribute of the person object.
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Sets the neck attribute of the person object.
     * Useful when the user wants to reset the neck circumference to null.
     * @param neck has type HalfDimension
     */
    public void setNeck(HalfDimension neck) {
        this.neck = neck;
    }

    /**
     * Sets the neck attribute of the person object.
     * @param neck type int, is the numerical portion
     * @param halfInch type bool, represents the decimal portion (half inches legal)
     * @throws InvalidDimensionException when the neck circumference is not in
     * the appropriate range of 13-21.5.
     */
    public void setNeck(int neck, boolean halfInch) throws InvalidDimensionException {
        if (neck < 13 || neck > 21) {
            throw new InvalidDimensionException("Please enter a neck dimension between 13\" and 21.5\"");
        } else if (this.neck != null){
            this.neck.setNum(neck);
            this.neck.setHalf(halfInch);
        } else
            this.neck = new HalfDimension(neck, halfInch);
    }

    /**
     * Sets the bust attribute of the person object.
     * Useful when the user wants to reset the bust circumference to null.
     * @param bust has type HalfDimension
     */
    public void setBust(HalfDimension bust) {
        this.bust = bust;
    }

    /**
     * Sets the bust attribute of the person object.
     * @param bust type int, is the numerical portion
     * @param halfInch type bool, represents the decimal portion (half inches legal)
     * @throws InvalidDimensionException when the bust circumference is not in
     * the appropriate range of 31-68.5.
     */
    public void setBust(int bust, boolean halfInch) throws InvalidDimensionException {
        if (bust < 31 || bust > 68) {
            throw new InvalidDimensionException("Please enter a bust dimension between 31\" and 68.5\"");
        } else if (this.bust != null){
            this.bust.setNum(bust);
            this.bust.setHalf(halfInch);
        } else
            this.bust = new HalfDimension(bust, halfInch);
    }

    /**
     * Sets the chest attribute of the person object.
     * Useful when the user wants to reset the chest circumference to null.
     * @param chest has type HalfDimension
     */
    public void setChest(HalfDimension chest) {
        this.chest = chest;
    }


    /**
     * Sets the chest attribute of the person object.
     * @param chest type int, is the numerical portion
     * @param halfInch type bool, represents the decimal portion (half inches legal)
     * @throws InvalidDimensionException when the chest circumference is not in
     * the appropriate range of 30-64.
     */
    public void setChest(int chest, boolean halfInch) throws InvalidDimensionException {
        if (chest < 30 || chest > 64 || (chest == 64 && halfInch)) {
            throw new InvalidDimensionException("Please enter a chest dimension between 30\" and 64\"");
        } else if (this.chest != null){
            this.chest.setNum(chest);
            this.chest.setHalf(halfInch);
        } else
            this.chest = new HalfDimension(chest, halfInch);
    }

    /**
     * Sets the waist attribute of the person object.
     * Useful when the user wants to reset the waist circumference to null.
     * @param waist has type HalfDimension
     */
    public void setWaist(HalfDimension waist) {
        this.waist = waist;
    }

    /**
     * Sets the waist attribute of the person object.
     * @param waist type int, is the numerical portion
     * @param halfInch type bool, represents the decimal portion (half inches legal)
     * @throws InvalidDimensionException when the waist circumference is not in
     * the appropriate range of 23-60.5.
     */
    public void setWaist(int waist, boolean halfInch) throws InvalidDimensionException {
        if (waist < 23 || waist > 60) {
            throw new InvalidDimensionException("Please enter a waist dimension between 23\" and 60.5\"");
        } else if (this.waist != null){
            this.waist.setNum(waist);
            this.waist.setHalf(halfInch);
        } else
            this.waist = new HalfDimension(waist, halfInch);
    }

    /**
     * Sets the hip attribute of the person object.
     * Useful when the user wants to reset the hip circumference to null.
     * @param hip has type HalfDimension
     */
    public void setHip(HalfDimension hip) {
        this.hip = hip;
    }

    /**
     * Sets the hip attribute of the person object.
     * @param hip type int, is the numerical portion
     * @param halfInch type bool, represents the decimal portion (half inches legal)
     * @throws InvalidDimensionException when the hip circumference is not in
     * the appropriate range of 33-70.5.
     */
    public void setHip(int hip, boolean halfInch) throws InvalidDimensionException {
        if (hip < 33 || hip > 70) {
            throw new InvalidDimensionException("Please enter a hip dimension between 33\" and 70.5\"");
        } else if (this.hip != null){
            this.hip.setNum(hip);
            this.waist.setHalf(halfInch);
        } else
            this.hip = new HalfDimension(hip, halfInch);
    }

    /**
     * Sets the inseam attribute of the person object.
     * Useful when the user wants to reset the inseam length to null.
     * @param inseam has type HalfDimension
     */
    public void setInseam(HalfDimension inseam) {
        this.inseam = inseam;
    }

    /**
     * Sets the inseam attribute of the person object.
     * @param inseam type int, is the numerical portion
     * @param halfInch type bool, represents the decimal portion (half inches legal)
     * @throws InvalidDimensionException when the inseam circumference is not in
     * the appropriate range of 26-34.5.
     */
    public void setInseam(int inseam, boolean halfInch) throws InvalidDimensionException {
        if (inseam < 26 || inseam > 34 || (inseam == 34 && halfInch)) {
            throw new InvalidDimensionException("Please enter a inseam dimension between 26\" and 34.5\"");
        } else if (this.inseam != null){
            this.inseam.setNum(inseam);
            this.inseam.setHalf(halfInch);
        } else
            this.inseam = new HalfDimension(inseam, halfInch);
    }

    /**
     * Sets the comment attribute of the person object
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Returns the name attribute of the person object
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the date attribute of the person object
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Returns the neck attribute of the person object
     * @return neck
     */
    public HalfDimension getNeck() {
        return neck;
    }

    /**
     * Returns the bust attribute of the person object
     * @return bust
     */
    public HalfDimension getBust() {
        return bust;
    }

    /**
     * Returns the chest attribute of the person object
     * @return chest
     */
    public HalfDimension getChest() {
        return chest;
    }

    /**
     * Returns the waist attribute of the person object
     * @return waist
     */
    public HalfDimension getWaist() {
        return waist;
    }

    /**
     * Returns the hip attribute of the person object
     * @return hip
     */
    public HalfDimension getHip() {
        return hip;
    }

    /**
     * Returns the inseam attribute of the person object
     * @return inseam
     */
    public HalfDimension getInseam() {
        return inseam;
    }

    /**
     * Returns the comment attribute of the person object
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Implements the toString method for the Person class
     * @return a string representing the person object that includes
     * information about the persons name, and optionally; bust, chest,
     * waist, and inseam info if available.
     */
    @Override
    public String toString() { // bust, chest, waist, and inseam values.
        if (bust == null && chest == null && waist == null && inseam == null)
            return name + "\n";

        String bustStr = (bust != null ? ("Bust: " + String.valueOf((double) bust.getNum() + (bust.getHalf() ? 0.5 : 0)) + "\n") : "");
        String chestStr = (chest != null ? ("Chest: " + String.valueOf((double) chest.getNum() + (chest.getHalf() ? 0.5 : 0)) + "\n") : "");
        String waistStr = (waist != null ? ("Waist: " + String.valueOf((double) waist.getNum() + (waist.getHalf() ? 0.5 : 0)) + "\n") : "");
        String inseamStr = (inseam != null ? ("Inseam: " + String.valueOf((double) inseam.getNum() + (inseam.getHalf() ? 0.5 : 0)) + "\n") : "");

        return name + "\n" + bustStr + chestStr + waistStr + inseamStr;
    }
}
