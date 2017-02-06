package com.example.jyuen1.jyuen1_sizebook;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;

/**
 * Created by Jennifer Yuen on 1/31/2017.
 */

/**
 * Copyright 2017 Jennifer Yuen
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0  Unless required by
 * applicable law or agreed to in writing, software distributed under
 * the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES
 * OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and
 * limitations under the License.
 *
 *
 * This class is the main view class of the project.  In this class, records
 * in the sizebook are displayed and file manipulation is performed.  This
 * class also serves as a doorway to access other activity classes.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The file that the PersonContainer singleton class is saved to.
     * The format of the file is JSON.
     * @see #loadFromFile()
     * @see #saveInFile()
     */
    private static final String FILENAME = "file.sav";
    /**
     * The key indicating the position of the clicked person in the personList Array
     * obtained from PersonContainer.getInstance().getPersonList();
     * The key is passed to ViewActivity.class to display the person information.
     */
    public final static String EXTRA_POSITION = "com.example.jyuen1.jyuen1_sizebook.MainActivity.POSITION";

    private TextView personCountText;
    private ListView personListView;
    private Button addButton;

    private PersonContainer instance;
    private ArrayAdapter<Person> adapter;

    /**
     * Called when the application and activity is first created.
     * Sets onClickListener's to addButton and personListView to display
     * CreateActivity.class and ViewActivity.class activities, respectively.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize views
        personCountText = (TextView) findViewById(R.id.countText);
        personListView = (ListView) findViewById(R.id.personListView);
        addButton = (Button) findViewById(R.id.addButton);

        addButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);
                Intent intent = new Intent(v.getContext(), CreateActivity.class);
                startActivity(intent);
            }
        });

        // Taken from http://stackoverflow.com/questions/17851687/how-to-handle-the-click-event-in-listview-in-android
        // 2017-02-04 17:02
        personListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                intent.putExtra(EXTRA_POSITION, position);
                startActivity(intent);
            }
        });
    }

    /**
     * Called after onCreate() or when the user navigates back to the activity
     * after it is no longer visible.  Restores the PersonContainer singleton
     * class state if it is the first time the application is loaded.
     * Handles proper displaying of the person list and count in the activity.
     * @see <a href="url">https://developer.android.com/reference/android/app/
     * Activity.html#ActivityLifecycle</a>
     */
    @Override
    protected void onStart() {
        super.onStart();

        if (PersonContainer.isLoaded() == null) {
            loadFromFile();
        }

        // Display updated person count
        personCountText.setText("Records: " + Integer.toString(PersonContainer.getInstance().getPersonCount()));

        /* Associate an adapter with the personListView list view to display records on screen.
         * Requires a working instance of PersonContainer, which is why this is not done
         * in onCreate()
         */
        adapter = new ArrayAdapter<Person>(this, R.layout.list_item, PersonContainer.getInstance().getPersonList());
        personListView.setAdapter(adapter);
    }

    /**
     * loads the PersonContainer singleton instance from the saved JSON file.
     * or instantiates a new singleton if the instance or the file is not found.
     */
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Taken from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            // 2017-01-24 18:19
            Type containerType = new TypeToken<PersonContainer>() {}.getType();
            instance = (PersonContainer) gson.fromJson(in, containerType);
            if (instance != null)
                instance.loadInstance(instance);
            else
                instance = PersonContainer.getInstance();
        } catch (FileNotFoundException e) {
            instance = PersonContainer.getInstance();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * preserves the state of the PersonContainer singleton by saving its state
     * information into a JSON file.
     */
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME, Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(PersonContainer.getInstance(), out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Calls saveInFile() when the activity stops i.e. it is no longer visible
     * @see #saveInFile()
     */
    @Override
    protected void onStop() {
        super.onStop();
        saveInFile();
    }
}
