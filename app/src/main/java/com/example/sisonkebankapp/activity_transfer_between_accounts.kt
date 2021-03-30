package com.example.sisonkebankapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_transfer_between_accounts.*

class activity_transfer_between_accounts : AppCompatActivity() {
    internal var dbHelper = DatabaseHelper(this);

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transfer_between_accounts)

        val intent = intent
        val logged_in_user: String? = intent.getStringExtra("logged_in_user");

        if (logged_in_user != null) {
            val user_account_details: String = dbHelper.getUserDetails(logged_in_user, false)
            val user_account_details_segments = user_account_details.split(":".toRegex()).toTypedArray()
            val current_account_balance = user_account_details_segments[3];
            val current_savings_account_balance = user_account_details_segments[4];
            textView20.setText(current_account_balance);
            textView22.setText(current_savings_account_balance);
        }

        val spinner: Spinner = findViewById(R.id.spinner)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.transfer_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

        button6.setOnClickListener{
            if (editTextTextPersonName3.text.toString().isEmpty()){
                Toast.makeText(
                    applicationContext,
                    "Please enter an amount to transfer",
                    Toast.LENGTH_SHORT
                ).show();
            } else {
                val transfer_option: String = spinner.getSelectedItem().toString()
                if (transfer_option == "Current to Savings") {
                    if (Integer.parseInt(editTextTextPersonName3.text.toString()) <= Integer.parseInt(textView20.text.toString())) {
                        dbHelper.updateBalance(
                            logged_in_user.toString(),
                            transfer_option,
                            editTextTextPersonName3.text.toString()
                        );
                        val updated_user_account_details: String =
                            dbHelper.getUserDetails(logged_in_user.toString(), false)
                        val updated_user_account_details_segments =
                            updated_user_account_details.split(":".toRegex()).toTypedArray()
                        val new_current_account_balance = updated_user_account_details_segments[3];
                        val new_current_savings_account_balance =
                            updated_user_account_details_segments[4];
                        textView20.setText(new_current_account_balance);
                        textView22.setText(new_current_savings_account_balance);
                        Toast.makeText(
                            applicationContext,
                            "Transfer completed successfully",
                            Toast.LENGTH_SHORT
                        ).show();
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Error! Transfer unsuccessful!",
                            Toast.LENGTH_SHORT
                        ).show();
                    }
                } else if (transfer_option == "Savings to Current") {
                    if (Integer.parseInt(editTextTextPersonName3.text.toString()) <= Integer.parseInt(textView22.text.toString())) {
                        dbHelper.updateBalance(
                            logged_in_user.toString(),
                            transfer_option,
                            editTextTextPersonName3.text.toString()
                        );
                        val updated_user_account_details: String =
                            dbHelper.getUserDetails(logged_in_user.toString(), false)
                        val updated_user_account_details_segments =
                            updated_user_account_details.split(":".toRegex()).toTypedArray()
                        val new_current_account_balance = updated_user_account_details_segments[3];
                        val new_current_savings_account_balance =
                            updated_user_account_details_segments[4];
                        textView20.setText(new_current_account_balance);
                        textView22.setText(new_current_savings_account_balance);
                        Toast.makeText(
                            applicationContext,
                            "Transfer completed successfully",
                            Toast.LENGTH_SHORT
                        ).show();
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Error! Transfer unsuccessful!",
                            Toast.LENGTH_SHORT
                        ).show();
                    }
                }
            }
        }
    }
}