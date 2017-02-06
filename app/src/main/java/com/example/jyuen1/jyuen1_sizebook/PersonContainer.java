package com.example.jyuen1.jyuen1_sizebook;

import java.util.ArrayList;

/**
 * Created by Jennifer Yuen on 2017-02-02.
 */

/**
 * This class represents a container (or list) of Person objects.
 * It also keeps track of the number of people in the container
 * by incrementing the count when a person is added or decrementing
 * the count when a person is removed.  This is useful for providing
 * constant time access in retrieving the count, rather than
 * iterating over the list to retrieve the count every time it is needed.
 * Importantly, this class behaves as a singleton class, that is, it
 * has a single instance that is accessed by various activities
 * This is to allow less cumbersome information sharing across activities.
 * Its state is stored and loaded as the application is shutdown and relaunched.
 * This class will be referred to as the PersonContainer singleton
 * instance elsewhere.
 */
public class PersonContainer{
    private static PersonContainer instance = null;

    private ArrayList<Person> personList;
    private int personCount;

    /**
     * A private constructor for the PersonContainer class.
     * Creates a new personList and initiates the person count to 0.
     */
    private PersonContainer() {
        personList = new ArrayList<Person>();
        personCount = 0;
    }

    /**
     * Returns the PersonContainer singleton instance
     * or creates a new instance if it is the first time
     * this function is being called and returns it.
     * @return
     */
    public static PersonContainer getInstance() {
        if (instance == null) {
            instance = new PersonContainer();
        }
        return instance;
    }

    /**
     * Returns the person list attribute of PersonContainer singleton instance.
     * @return
     */
    public ArrayList<Person> getPersonList() {
        return personList;
    }

    /**
     * Returns the number of people in personList attribute of
     * PersonContainer singleton instance.
     * @return
     */
    public int getPersonCount() {
        return personCount;
    }

    /**
     * Adds a person to person list and increments the person count.
     * @param person is the person to add
     */
    public void addPerson(Person person) {
        personList.add(person);
        personCount++;
    }

    /**
     * Removes the indicated person from person list and decrements
     * the person count.
     * @param person is the person to remove
     * @throws EmptyContainerException
     */
    public void removePerson(Person person) throws EmptyContainerException {
        if (personCount == 0) {
            throw new EmptyContainerException();
        } else {
            personList.remove(personList.indexOf(person));
            personCount--;
        }
    }

    // Handle restoring the state of the singleton class i.e when the application exits and is reloaded

    /**
     * This function returns the singleton instance if it exists, otherwise null i.e.
     * if it is the first time the application has been launched.
     * @return
     */
    public static PersonContainer isLoaded() {
        return instance;
    }

    /**
     * This function loads the singleton instance that was saved by the main activity class
     * @param instance is the instance that was saved.
     */
    public void loadInstance(PersonContainer instance) {
        this.instance = instance;
    }
}
