package com.avdsoft.a403sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.avdsoft.a403sqlite.database.helpers.DatabaseHelper;
import com.avdsoft.a403sqlite.models.UserModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText name;
    private EditText lastName;
    private EditText age;
    private EditText descr;
    private EditText address;

    private Button save;
    private Button read;
    private Button remove;
    private Button update;

    private UserModel mUser;
    private DatabaseHelper dbHepler;
    private ContentValues mCV;
    private SQLiteDatabase mDB;
    private final String DB_NAME = "db_403sqlite";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = (EditText) findViewById(R.id.name_EditText);
        lastName = (EditText) findViewById(R.id.lastName_EditText);
        age = (EditText) findViewById(R.id.age_EditText);
        descr = (EditText) findViewById(R.id.descr_EditText);
        address = (EditText) findViewById(R.id.address_EditText);

        save = (Button) findViewById(R.id.create_Btn);
        read = (Button) findViewById(R.id.read_Btn);
        remove = (Button) findViewById(R.id.remove_Btn);
        update = (Button) findViewById(R.id.update_Btn);

        save.setOnClickListener(this);
        read.setOnClickListener(this);
        remove.setOnClickListener(this);
        update.setOnClickListener(this);

        dbHepler = new DatabaseHelper(this, DB_NAME, 1);
        mCV = new ContentValues();
        mDB = dbHepler.getWritableDatabase();
    }

    @Override
    public void onClick(View v) {
        String nameField = name.getText().toString();
        String lastNameField = lastName.getText().toString();
        String ageField = age.getText().toString();
        String descrField = descr.getText().toString();
        String addressField = address.getText().toString();

        int btnId = v.getId();
        if (R.id.create_Btn == btnId) {
            dataAdd(nameField, lastNameField, ageField, descrField, addressField);
        } else if (R.id.read_Btn == btnId) {
            dataRead();
        } else if (R.id.remove_Btn == btnId) {
            dataRemove();
        } else if (R.id.update_Btn == btnId) {
            dataUpdate(nameField, lastNameField, ageField, descrField, addressField);
        }

    }

    private void dataAdd(String nameField, String lastNameField, String ageField, String descrField,
                         String addressField) {
        mCV.put("name", nameField);
        mCV.put("last_name", lastNameField);
        mCV.put("age", ageField);
        mCV.put("description", descrField);
        mCV.put("address", addressField);
        mDB.insert(DB_NAME, null, mCV);
        mUser = new UserModel(nameField, lastNameField, ageField, descrField, addressField);
        setDataToWidgets("");
    }

    private void dataRead() {
        Cursor c = mDB.query(DB_NAME, null, null, null, null, null, null);
        if (c.moveToFirst()) {
            int idColIndex = c.getColumnIndex("id");
            int nameColIndex = c.getColumnIndex("name");
            int lnameColIndex = c.getColumnIndex("last_name");
            int ageColIndex = c.getColumnIndex("age");
            int descColIndex = c.getColumnIndex("description");
            int adrColIndex = c.getColumnIndex("address");
            do {
                mUser = new UserModel(c.getString(nameColIndex),
                        c.getString(lnameColIndex),
                        c.getString(ageColIndex),
                        c.getString(descColIndex),
                        c.getString(adrColIndex));
                mUser.setUserId(c.getInt(idColIndex));
            } while (c.moveToNext());
            c.close();
            setDataToWidgets();
        }
    }

    private void setDataToWidgets() {
        name.setText(mUser.getUserName());
        lastName.setText(mUser.getUserLastName());
        age.setText(mUser.getUserAge());
        descr.setText(mUser.getUserDescr());
        address.setText(mUser.getUserAddress());
    }

    private void setDataToWidgets(String empty) {
        name.setText(empty);
        lastName.setText(empty);
        age.setText(empty);
        descr.setText(empty);
        address.setText(empty);
    }

    private void dataRemove() {
        mDB.delete(DB_NAME, "id=?", new String[]{"" + mUser.getUserId()});
        setDataToWidgets("");
    }

    private void dataUpdate(String nameField, String lastNameField, String ageField, String descrField,
                            String addressField) {
        mCV.put("name", nameField);
        mCV.put("last_name", lastNameField);
        mCV.put("age", ageField);
        mCV.put("description", descrField);
        mCV.put("address", addressField);
        mDB.update(DB_NAME, mCV, "id=?", new String[]{"" + mUser.getUserId()});
        mUser = new UserModel(nameField, lastNameField, ageField, descrField, addressField);
        setDataToWidgets("");
    }
}
