package com.example.jyuen1.jyuen1_sizebook;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Jennifer Yuen on 2/3/2017.
 */

/**
 * This activity allows the user to create new records for the sizebook.
 */
public class CreateActivity extends AppCompatActivity {
    private EditText nameView;
    private EditText neckView;
    private EditText bustView;
    private EditText chestView;
    private EditText waistView;
    private EditText hipView;
    private EditText inseamView;
    private EditText dateView;
    private EditText commentView;

    private CheckBox neckCheckBox;
    private CheckBox bustCheckBox;
    private CheckBox chestCheckBox;
    private CheckBox waistCheckBox;
    private CheckBox hipCheckBox;
    private CheckBox inseamCheckBox;

    private Button saveButton;
    private Button cancelButton;


    /**
     * Called when the activity is first created.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        // initialize views
        nameView = (EditText) findViewById(R.id.name);
        neckView = (EditText) findViewById(R.id.neck);
        bustView = (EditText) findViewById(R.id.bust);
        chestView = (EditText) findViewById(R.id.chest);
        waistView = (EditText) findViewById(R.id.waist);
        hipView = (EditText) findViewById(R.id.hip);
        inseamView = (EditText) findViewById(R.id.inseam);
        dateView = (EditText) findViewById(R.id.dateValid);
        commentView = (EditText) findViewById(R.id.comment);

        neckCheckBox = (CheckBox) findViewById(R.id.neckHalf);
        bustCheckBox = (CheckBox) findViewById(R.id.bustHalf);
        chestCheckBox = (CheckBox) findViewById(R.id.chestHalf);
        waistCheckBox = (CheckBox) findViewById(R.id.waistHalf);
        hipCheckBox = (CheckBox) findViewById(R.id.hipHalf);
        inseamCheckBox = (CheckBox) findViewById(R.id.inseamHalf);

        saveButton = (Button) findViewById(R.id.saveButton);
        cancelButton = (Button) findViewById(R.id.cancelButton);

        /**
         * Returns to the main activity when the cancel button is pressed.
         * Taken from http://stackoverflow.com/questions/7212816/intent-does-not-work-from-within-onclicklistener
         * 2017-02-04 16:38
         */
        cancelButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        /**
         * Calls createNewPerson() when the save button is pressed.
         * @see #createNewPerson()
         */
        saveButton.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View v) {
                setResult(RESULT_OK);
                createNewPerson();
            }
        });

        /**
         * Adds a DatePickerDialog to the date editText.  The focusable option is disabled
         * in the activity_create.xml, forbidding the user from typing or manually entering
         * a date.
         * Taken from http://stackoverflow.com/questions/17808373/popup-datepicker-for-edittext
         * 2017-02-05 18:25
         */
        dateView.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();

                DatePickerDialog mDatePicker=new DatePickerDialog(CreateActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        dateView.setText(String.valueOf(selectedyear) + "-" + String.valueOf(selectedmonth+1) + "-" + String.valueOf(selectedday));
                    }
                },mcurrentDate.get(Calendar.YEAR), mcurrentDate.get(Calendar.MONTH), mcurrentDate.get(Calendar.DAY_OF_MONTH));
                mDatePicker.setTitle("Select date");
                mDatePicker.show();  }
        });
    }

    /**
     * Called when the save button is pressed.  Creates a Person object with input from
     * the EditText fields of create_person.xml and adds the person to the PersonContainer
     * singleton instance by calling PersonContainer.getInstance().addPerson(newPerson);
     * Handles errors in input i.e. InvalidDimensionException thrown by the Person class.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void createNewPerson() {
        // Create an alert dialog box for displaying input exceptions
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        Person newPerson;
        String contents;
        try {
            // Create a new person with name in the name edit text field.
            newPerson = new Person(nameView.getText().toString());

            // Obtain date info
            //Taken from http://stackoverflow.com/questions/4216745/java-string-to-date-conversion
            //2017-02-04 19:13
            contents = dateView.getText().toString();
            if (!contents.equals("")) {
                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date date = format.parse(contents);
                newPerson.setDate(date);
            }

            // Obtain neck info
            contents = neckView.getText().toString();
            if (!contents.equals("")) {
                Integer dimension = Integer.parseInt(contents);
                newPerson.setNeck(dimension, neckCheckBox.isChecked());
            } else if (neckCheckBox.isChecked()) {
                throw new InvalidDimensionException("size must be >0.5");
            }

            // Obtain bust info
            contents = bustView.getText().toString();
            if (!contents.equals("")) {
                Integer dimension = Integer.parseInt(contents);
                newPerson.setBust(dimension, bustCheckBox.isChecked());
            } else if (bustCheckBox.isChecked()) {
                throw new InvalidDimensionException("size must be >0.5");
            }

            // Obtain chest info
            contents = chestView.getText().toString();
            if (!contents.equals("")) {
                Integer dimension = Integer.parseInt(contents);
                newPerson.setChest(dimension, chestCheckBox.isChecked());
            } else if (chestCheckBox.isChecked()) {
                throw new InvalidDimensionException("size must be >0.5");
            }

            // Obtain waist info
            contents = waistView.getText().toString();
            if (!contents.equals("")) {
                Integer dimension = Integer.parseInt(contents);
                newPerson.setWaist(dimension, waistCheckBox.isChecked());
            } else if (waistCheckBox.isChecked()) {
                throw new InvalidDimensionException("size must be >0.5");
            }

            // Obtain hip info
            contents = hipView.getText().toString();
            if (!contents.equals("")) {
                Integer dimension = Integer.parseInt(contents);
                newPerson.setHip(dimension, hipCheckBox.isChecked());
            } else if (hipCheckBox.isChecked()) {
                throw new InvalidDimensionException("size must be >0.5");
            }

            // Obtain inseam info
            contents = inseamView.getText().toString();
            if (!contents.equals("")) {
                Integer dimension = Integer.parseInt(contents);
                newPerson.setInseam(dimension, inseamCheckBox.isChecked());
            } else if (inseamCheckBox.isChecked()) {
                throw new InvalidDimensionException("size must be >0.5");
            }

            // Obtain comment info
            newPerson.setComment(commentView.getText().toString());

            // Add the person to PersonContainer singleton instance and return to main activity.
            PersonContainer.getInstance().addPerson(newPerson);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);

            // Handle Exceptions
        } catch (nameTooShortException e) {
            alert.setMessage(e.getMessage()).create();
            alert.show();
        } catch (ParseException e) {
            alert.setMessage("Please enter a date in the yyyy-mm-dd format").create();
            alert.show();
        } catch (NumberFormatException e) {
            alert.setMessage("Dimensions must be a number").create();
            alert.show();
        } catch (InvalidDimensionException e) {
            alert.setMessage(e.getMessage()).create();
            alert.show();
        }
    }
}
