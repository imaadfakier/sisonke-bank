package com.example.sisonkebankapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_registration.*
import kotlin.random.Random


class activity_registration : AppCompatActivity() {

    internal var bank_user: List<BankUser> = ArrayList<BankUser>()
    internal var dbHelper = DatabaseHelper(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        fun randomAccountBalances(start: Int, end: Int): Int {
            require(!(start > end || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" };
            return Random(System.nanoTime()).nextInt(end - start + 1) + start;
        }

        fun randomizeIt():Int {
            val start = 10000;
            val end = 90000;

            return randomAccountBalances(start, end);
        }

        editTextTextPersonName.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })

        editTextTextPersonName2.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })

        editTextTextEmailAddress.addTextChangedListener(object:TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                /*val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex();
                if (editTextTextEmailAddress.text.toString().isEmpty()){
                    editTextTextEmailAddress.setError("Please enter your email address");
                }*/

            }

        })

        editTextTextPassword.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })

        editTextPhone.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })

        val first_radio_listener: View.OnClickListener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (!radioButton3.isChecked){
                    radioButton.setChecked(true);
                } else {
                    radioButton.setChecked(false);
                }
            }
        }

        val rb3 = radioButton3;
        rb3.setOnClickListener(first_radio_listener)

        val second_radio_listener: View.OnClickListener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                if (!radioButton.isChecked){
                    radioButton3.setChecked(true);
                } else {
                    radioButton3.setChecked(false);
                }
            }
        }

        val rb = radioButton;
        rb.setOnClickListener(second_radio_listener)

        button.setOnClickListener {
            var correct_fields = 0;

            if (editTextTextPersonName.text.toString()
                    .isEmpty() && editTextTextPersonName2.text.toString()
                    .isEmpty() && editTextTextEmailAddress.text.toString()
                    .isEmpty() && editTextTextPassword.text.toString()
                    .isEmpty() && editTextPhone.text.toString().isEmpty()
                    && !radioButton3.isChecked && !radioButton.isChecked
            ) {
                Toast.makeText(
                    applicationContext,
                    "All fields are missing information",
                    Toast.LENGTH_SHORT
                ).show();
                correct_fields = 0;
            } else {
                if (editTextTextPersonName.text.toString().isEmpty()) {
                    Toast.makeText(
                        applicationContext,
                        "Please enter your first name",
                        Toast.LENGTH_SHORT
                    ).show();
                } else {
                    correct_fields++;
                }

                if (editTextTextPersonName2.text.toString().isEmpty()) {
                    Toast.makeText(
                        applicationContext,
                        "Please enter your last name",
                        Toast.LENGTH_SHORT
                    ).show();
                } else {
                    correct_fields++;
                }

                if (editTextTextEmailAddress.text.toString().isEmpty()) {
                    Toast.makeText(
                        applicationContext,
                        "Please enter an email address",
                        Toast.LENGTH_SHORT
                    ).show();
                }
                else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(editTextTextEmailAddress.text.toString()).matches()) {
                        Toast.makeText(
                            applicationContext,
                            "Please provide a valid email address",
                            Toast.LENGTH_SHORT
                        ).show();
                }
                else if (dbHelper.doesEmailAlreadyExist(editTextTextEmailAddress.text.toString())) {
                    Toast.makeText(
                        applicationContext,
                        "Unfortunately, that email is already in use",
                        Toast.LENGTH_SHORT
                    ).show();
                }
                else {
                    correct_fields++;
                }

                if (editTextTextPassword.text.toString().isEmpty()) {
                    Toast.makeText(
                        applicationContext,
                        "Please enter a password",
                        Toast.LENGTH_SHORT
                    ).show();
                } else if (editTextTextPassword.text.toString().length < 5) {
                    Toast.makeText(
                        applicationContext,
                        "Minimum password length is 5",
                        Toast.LENGTH_SHORT
                    ).show();
                } else {
                    correct_fields++;
                }

                if (editTextPhone.text.toString().isEmpty()) {
                    Toast.makeText(
                        applicationContext,
                        "Please enter your mobile number",
                        Toast.LENGTH_SHORT
                    ).show();
                } else {
                    correct_fields++;
                }

                if (!radioButton3.isChecked && !radioButton.isChecked){
                    Toast.makeText(
                        applicationContext,
                        "Please choose a gender",
                        Toast.LENGTH_SHORT
                    ).show();
                } /*else if (radioButton3.isChecked && radioButton.isChecked){
                    Toast.makeText(
                        applicationContext,
                        "Please choose one gender",
                        Toast.LENGTH_SHORT
                    ).show();
                }*/
                else {
                    correct_fields++;
                }
            }

            if (correct_fields == 6){
                try {
                    if (radioButton3.isChecked) {
                        val new_sisonke_bank_client = BankUser(
                            editTextTextPersonName.text.toString(),
                            editTextTextPersonName2.text.toString(),
                            editTextTextEmailAddress.text.toString(),
                            editTextTextPassword.text.toString(),
                            editTextPhone.text.toString(),
                            radioButton3.text.toString(),
                            randomizeIt().toString(),
                            randomizeIt().toString()
                        )
                        /*dbHelper.addUser(
                            editTextTextPersonName.text.toString(),
                            editTextTextPersonName2.text.toString(),
                            editTextTextEmailAddress.text.toString(),
                            editTextTextPassword.text.toString(),
                            editTextPhone.text.toString(),
                            radioButton3.text.toString(),
                            Integer.toString(randomizeIt()),
                            Integer.toString(randomizeIt())
                        )*/
                        dbHelper.addUser(
                            new_sisonke_bank_client
                        )
                    } else {
                        val new_sisonke_bank_client = BankUser(
                            editTextTextPersonName.text.toString(),
                            editTextTextPersonName2.text.toString(),
                            editTextTextEmailAddress.text.toString(),
                            editTextTextPassword.text.toString(),
                            editTextPhone.text.toString(),
                            radioButton.text.toString(),
                            randomizeIt().toString(),
                            randomizeIt().toString()
                        )
                        /*dbHelper.addUser(
                            editTextTextPersonName.text.toString(),
                            editTextTextPersonName2.text.toString(),
                            editTextTextEmailAddress.text.toString(),
                            editTextTextPassword.text.toString(),
                            editTextPhone.text.toString(),
                            radioButton.text.toString(),
                            Integer.toString(randomizeIt()),
                            Integer.toString(randomizeIt())
                        )*/
                        dbHelper.addUser(
                            new_sisonke_bank_client
                        )
                    }

                    editTextTextPersonName.setText("");
                    editTextTextPersonName2.setText("");
                    editTextTextEmailAddress.setText("");
                    editTextTextPassword.setText("");
                    editTextPhone.setText("");

                    val intent = Intent(this@activity_registration, activity_login::class.java)
                    startActivity(intent);

                    val toast = Toast.makeText(
                        applicationContext,
                        "Success! Thank you for banking with us",
                        Toast.LENGTH_SHORT
                    ).show();

                    finish();


                    /*val res = dbHelper.getUserDetails;

                    if (res.count == 0){
                        Toast.makeText(
                            applicationContext,
                            "Database contains no records!",
                            Toast.LENGTH_SHORT
                        ).show();
                    } else {
                        val buffer = StringBuffer();

                        while (res.moveToNext()){
                            buffer.append("ID: ", res.getString(0) + "\n");
                            buffer.append("FIRST NAME: ", res.getString(1) + "\n");
                            buffer.append("LAST NAME: ", res.getString(2) + "\n");
                            buffer.append("EMAIL ADDRESS: ", res.getString(3) + "\n");
                            buffer.append("PASSWORD: ", res.getString(4) + "\n");
                            buffer.append("MOBILE NUMBER: ", res.getString(5) + "\n");
                            buffer.append("GENDER: ", res.getString(6) + "\n");
                        }

                        Toast.makeText(
                            applicationContext,
                            "Database records are as follows:\n$buffer",
                            Toast.LENGTH_SHORT
                        ).show();

                        //Log.d("Database records:", "\n$buffer");
                    }*/
                }
                catch (e: Exception) {
                    // handler
                    Toast.makeText(
                        applicationContext,
                        "" + e,
                        Toast.LENGTH_SHORT
                    ).show();
                }
                finally {
                    // optional finally block
                }
            }
        }

        textView5.setOnClickListener{
            val intent = Intent(this@activity_registration, activity_login::class.java)
            startActivity(intent);
        }
    }
}