package com.example.naya;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Table_layout extends AppCompatActivity
        implements RadioGroup.OnCheckedChangeListener,View.OnClickListener{
    EditText editText1,editText2,editText3,editText4;
    RadioGroup radioGroup;
    Button btnsubmit;
    Spinner spinner;
    String name,gender,dob,country,phone,email;
    String[] countries ={"Nepal","India","Srilanka","Bhutan","Maldives","Myanmar","Pakistan","Afganistan"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_layout);
        editText1 = findViewById(R.id.edt1);
        editText2 = findViewById(R.id.edt2);
        editText3 = findViewById(R.id.edt3);
        editText4 = findViewById(R.id.edt4);
        radioGroup = findViewById(R.id.rbgender);
        spinner = findViewById(R.id.spcountry);
        btnsubmit = findViewById(R.id.btnsubmit);

        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.spinners_values,countries);
        spinner.setAdapter(adapter);

        radioGroup.setOnCheckedChangeListener(this);
        setSpinnerValue();
        btnsubmit.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int i) {
        if(i == R.id.rbmale){
            //Toast.makeText(this,"male",Toast.LENGTH_SHORT).show();
            gender = "Male";
        }
        if(i == R.id.rbfemale){
            //Toast.makeText(this,"male",Toast.LENGTH_SHORT).show();
            gender = "Female";
        }
        if(i == R.id.rbother){
            //Toast.makeText(this,"male",Toast.LENGTH_SHORT).show();
            gender = "Other";
        }

    }

    private void setSpinnerValue(){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                country = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Table_layout.this, "please select country", Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onClick(View v) {
        name = editText1.getText().toString();
        dob = editText4.getText().toString();
        phone = editText3.getText().toString();
        email = editText2.getText().toString();

        if(v.getId() == R.id.btnsubmit) {
            if (validate()) {
                Toast.makeText(this, "All Ok", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private boolean validate(){
        if(TextUtils.isEmpty(name)){
            editText1.setError("Enter name");
            editText1.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(email)){
            editText2.setError("Enter Email");
            editText2.requestFocus();
            return false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editText2.setError("Invalid Email");
            editText2.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(phone)){
            editText3.setError("Enter phone number");
            editText3.requestFocus();
            return false;
        }

        if(!TextUtils.isDigitsOnly(phone)){
            editText3.setError("Invalid phone number");
            editText3.requestFocus();
            return false;
        }

        if(TextUtils.isEmpty(gender)){
            Toast.makeText(this, "please select gender", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(country)){
            Toast.makeText(this, "please select country", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(TextUtils.isEmpty(dob)){
            editText4.setError("Enter name");
            editText4.requestFocus();
            return false;
        }
        return true;
    }
}
