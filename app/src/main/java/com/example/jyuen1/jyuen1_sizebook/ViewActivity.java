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
 * Created by Jennifer Yuen on 2/4/2017.
 */

/**
 * This activity allows the user to view and delete single records
 * in the sizebook.
 */
public class ViewActivity extends AppCompatActivity {
    private EditText nameET;
    private EditText neckSizeET;
    private EditText bustSizeET;
    private EditText chestSizeET;
    private EditText waistSizeET;
    private EditText hipSizeET;
    private EditText inseamSizeET;
    private EditText dateET;
    private EditText commentET;

    private CheckBox neckCB;
    private CheckBox bustCB;
    private CheckBox chestCB;
    private CheckBox waistCB;
    private CheckBox hipCB;
    private CheckBox inseamCB;

    private Button saveButton;
    private Button deleteButton;

    private Person person;

    /**
     * Called when the activity is first created.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        // initialize views
        nameET = (EditText) findViewById(R.id.nameText);
        neckSizeET = (EditText) findViewById(R.id.neckSize);
        bustSizeET = (EditText) findViewById(R.id.bustSize);
        chestSizeET = (EditText) findViewById(R.id.chestSize);
        waistSizeET = (EditText) findViewById(R.id.waistSize);
        hipSizeET = (EditText) findViewById(R.id.hipSize);
        inseamSizeET = (EditText) findViewById(R.id.inseamSize);
        dateET = (EditText) findViewById(R.id.date);
        commentET = (EditText) findViewById(R.id.comment);

        neckCB = (CheckBox) findViewById(R.id.neckHalfInch);
        bustCB = (CheckBox) findViewById(R.id.bustHalfInch);
        chestCB = (CheckBox) findViewById(R.id.chestHalfInch);
        waistCB = (CheckBox) findViewById(R.id.waistHalfInch);
        hipCB = (CheckBox) findViewById(R.id.hipHalfInch);
        inseamCB = (CheckBox) findViewById(R.id.inseamHalfInch);

        saveButton = (Button) findViewById(R.id.saveButton2);
        deleteButton = (Button) findViewById(R.id.removeButton);


        /**
         * Obtain the position of the record in the person list
         * from the intent extras passed from main activity.
         */
        Bundle extras = getIntent().getExtras();
        int position = extras.getInt(MainActivity.EXTRA_POSITION);
        person = PersonContainer.getInstance().getPersonList().get(position);

        /**
         * If the user presses the delete button, remove the record
         * from PersonContainer singleton's person list and return to
         * main activity.
         */
        deleteButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                try {
                    PersonContainer.getInstance().removePerson(person);
                    Intent intent = new Intent(v.getContext(), MainActivity.class);
                    startActivity(intent);
                } catch (EmptyContainerException e) {
                    e.printStackTrace();
                }
            }
        });

        /**
         * Calls setAttributes() when the save button is pressed.
         * @see #setAttributes()
         */
        saveButton.setOnClickListener(new View.OnClickListener() {

            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View v) {
                setResult(RESULT_OK);
                setAttributes();
            }
        });

        /**
         * Adds a DatePickerDialog to the date editText.  The focusable option is disabled
         * in the activity_view.xml, forbidding the user from typing or manually entering
         * a date.
         * Taken from http://stackoverflow.com/questions/17808373/popup-datepicker-for-edittext
         * 2017-02-05 18:25
         */
        dateET.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //To show current date in the datepicker
                Calendar mcurrentDate = Calendar.getInstance();

                DatePickerDialog mDatePicker=new DatePickerDialog(ViewActivity.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        dateET.setText(String.valueOf(selectedyear) + "-" + String.valueOf(selectedmonth+1) + "-" + String.valueOf(selectedday));
                    }
                },mcurrentDate.get(Calendar.YEAR), mcurrentDate.get(Calendar.MONTH), mcurrentDate.get(Calendar.DAY_OF_MONTH));
                mDatePicker.setTitle("Select date");
                mDatePicker.show();  }
        });
    }

    /**
     * Called when the activity is starting, after OnCreate()
     * Retrieves information about the person from PersonContainer singleton instance
     * and displays in in the appropriate edit text fields.
     * @see <a href="url">https://developer.android.com/reference/
     * android/app/Activity.html#ActivityLifecycle</a>
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onStart() {
        super.onStart();

        // set the name edit text field
        if (person.getName() != null)
            nameET.setText(person.getName());

        // set the date edit text field in the yyyy-mm-dd format
        if (person.getDate() != null) {
            SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
            dateET.setText(dateformat.format(person.getDate()));
        }

        // set the comment edit text field
        if (person.getComment() != null)
            commentET.setText(person.getComment());

        // set the neck edit text and check box fields
        if (person.getNeck() != null) {
            neckSizeET.setText(Integer.toString(person.getNeck().getNum()));
            neckCB.setChecked(person.getNeck().getHalf());
        }

        // set the bust edit text and check box fields
        if (person.getBust() != null) {
            bustSizeET.setText(Integer.toString(person.getBust().getNum()));
            bustCB.setChecked(person.getBust().getHalf());
        }

        // set the chest edit text and check box fields
        if (person.getChest() != null) {
            chestSizeET.setText(Integer.toString(person.getChest().getNum()));
            chestCB.setChecked(person.getChest().getHalf());
        }

        // set the waist edit text and check box fields
        if (person.getWaist() != null) {
            waistSizeET.setText(Integer.toString(person.getWaist().getNum()));
            waistCB.setChecked(person.getWaist().getHalf());
        }

        // set the hip edit text and check box fields
        if (person.getHip() != null) {
            hipSizeET.setText(Integer.toString(person.getHip().getNum()));
            hipCB.setChecked(person.getHip().getHalf());
        }

        // set the inseam edit text and check box fields
        if (person.getInseam() != null) {
            inseamSizeET.setText(Integer.toString(person.getInseam().getNum()));
            inseamCB.setChecked(person.getInseam().getHalf());
        }
    }

    /**
     * If the user edits any of the edit text fields, this function updates
     * the information in the person object.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void setAttributes() {
        // Create an alert dialog box for displaying input exceptions
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        Integer dimension;
        String contents;
        try {
            // Set the name
            person.setName(nameET.getText().toString());

            // Set the date
            contents = dateET.getText().toString();
            if (!contents.equals("")) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                Date date = df.parse(contents);
                person.setDate(date);
            } else {
                person.setDate(null);
            }

            // set the neck circumference
            contents = neckSizeET.getText().toString();
            if (!contents.equals("")) {
                dimension = Integer.parseInt(contents);
                person.setNeck(dimension, neckCB.isChecked());
            } else if (neckCB.isChecked()) {
                throw new InvalidDimensionException("size must be >0.5");
            } else {
                person.setNeck(null);
            }

            // set the bust circumference
            contents = bustSizeET.getText().toString();
            if (!contents.equals("")) {
                dimension = Integer.parseInt(contents);
                person.setBust(dimension, bustCB.isChecked());
            } else if (bustCB.isChecked()) {
                throw new InvalidDimensionException("size must be >0.5");
            } else {
                person.setBust(null);
            }

            // set the chest circumference
            contents = chestSizeET.getText().toString();
            if (!contents.equals("")) {
                dimension = Integer.parseInt(contents);
                person.setChest(dimension, chestCB.isChecked());
            } else if (chestCB.isChecked()) {
                throw new InvalidDimensionException("size must be >0.5");
            } else {
                person.setChest(null);
            }

            // set the waist circumference
            contents = waistSizeET.getText().toString();
            if (!contents.equals("")) {
                dimension = Integer.parseInt(contents);
                person.setWaist(dimension, waistCB.isChecked());
            } else if (waistCB.isChecked()) {
                throw new InvalidDimensionException("size must be >0.5");
            } else {
                person.setWaist(null);
            }

            // set the hip circumference
            contents = hipSizeET.getText().toString();
            if (!contents.equals("")) {
                dimension = Integer.parseInt(contents);
                person.setHip(dimension, hipCB.isChecked());
            } else if (hipCB.isChecked()) {
                throw new InvalidDimensionException("size must be >0.5");
            } else {
                person.setHip(null);
            }

            // set the inseam length
            contents = inseamSizeET.getText().toString();
            if (!contents.equals("")) {
                dimension = Integer.parseInt(contents);
                person.setInseam(dimension, inseamCB.isChecked());
            } else if (inseamCB.isChecked()) {
                throw new InvalidDimensionException("size must be >0.5");
            } else {
                person.setInseam(null);
            }

            // set the comment
            person.setComment(commentET.getText().toString());

            // return to main activity
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
            alert.setMessage("Dimensions must be a number!").create();
            alert.show();
        } catch (InvalidDimensionException e) {
            alert.setMessage(e.getMessage()).create();
            alert.show();
        }
    }
}
