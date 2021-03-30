package com.example.sisonkebankapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.view.ViewDebug
import kotlin.random.Random

class DatabaseHelper(context: Context):SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
    companion object {
        private val DATABASE_VER = 1;
        private val DATABASE_NAME = "Clients.db";
        private val TABLE_NAME = "Users";
        private val ID = "ID";
        private val FIRST_NAME = "First_Name";
        private val LAST_NAME = "Last_Name";
        private val EMAIL_ADDRESS = "Email_Address";
        private val PASSWORD = "Password";
        private val MOBILE_NUMBER = "Mobile_Number";
        private val GENDER = "Gender"
        private val ACCOUNT_BALANCE = "Account_Balance"
        private val SAVINGS_ACCOUNT_BALANCE = "Savings_Account_Balance"
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY = ("CREATE TABLE $TABLE_NAME($ID INTEGER PRIMARY KEY, " +
                "$FIRST_NAME TEXT, $LAST_NAME TEXT, $EMAIL_ADDRESS TEXT, $PASSWORD TEXT, " +
                "$MOBILE_NUMBER TEXT, $GENDER TEXT, " +
                "$ACCOUNT_BALANCE TEXT, $SAVINGS_ACCOUNT_BALANCE TEXT)");
        p0!!.execSQL(CREATE_TABLE_QUERY);
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME");
        onCreate(p0);
    }

    val getUserDetails:Cursor
        get(){
            val selectQuery = "SELECT * FROM $TABLE_NAME";
            val db = this.writableDatabase;
            val cursor = db.rawQuery(selectQuery, null);
            return cursor;
        }

    fun getUserDetails(account_holder: String, only_receive_first_name: Boolean): String {
        val db = this.readableDatabase;
        val query = "SELECT $FIRST_NAME, $LAST_NAME, $EMAIL_ADDRESS, $ACCOUNT_BALANCE, $SAVINGS_ACCOUNT_BALANCE from $TABLE_NAME";
        val cursor: Cursor = db.rawQuery(query, null)
        var first_name: String
        var last_name: String
        var email_address: String
        var current_account_balance: String
        var savings_account_balance: String
        if (cursor.moveToFirst()) {
            do {
                first_name = cursor.getString(0)
                last_name = cursor.getString(1)
                email_address = cursor.getString(2)
                current_account_balance = cursor.getString(3)
                savings_account_balance = cursor.getString(4)
                if (only_receive_first_name) {
                    if (account_holder == email_address) {
                        return first_name;
                        //Log.d("First Name:", first_name);
                    }
                } else {
                    if (account_holder == email_address) {
                        return first_name + ":" + last_name + ":" + email_address + ":" + current_account_balance + ":" + savings_account_balance;
                    }
                }
                //Log.d("Email Address: ", email_address);
                //Log.d("First Name: ", first_name);
            } while (cursor.moveToNext())
        }
        return "ERROR! USER DOES NOT EXIST!";
    }

//    fun randomAccountBalances(start: Int, end: Int): Int {
//        require(!(start > end || end - start + 1 > Int.MAX_VALUE)) { "Illegal Argument" };
//        return Random(System.nanoTime()).nextInt(end - start + 1) + start;
//    }
//
//    fun randomizeIt():Int {
//        val start = 10000;
//        val end = 90000;
//
//        return randomAccountBalances(start, end);
//    }

    /*fun addUser(first_name: String, last_name: String, email_address: String, password: String, phone: String, gender: String){
        val db = this.writableDatabase;
        val values = ContentValues();
        values.put(FIRST_NAME, first_name);
        values.put(LAST_NAME, last_name);
        values.put(EMAIL_ADDRESS, email_address);
        values.put(PASSWORD, password);
        values.put(MOBILE_NUMBER, phone);
        values.put(GENDER, gender);
        values.put(ACCOUNT_BALANCE, Integer.toString(randomizeIt()));
        values.put(SAVINGS_ACCOUNT_BALANCE, Integer.toString(randomizeIt()));

        db.insert(TABLE_NAME, null, values);

        //val current_account_balance = randomizeIt();
        //Log.d("Account Balance", Integer.toString(current_account_balance));
        //val current_savings_account_balance = randomizeIt();
        //Log.d("Savings Account Balance", Integer.toString(current_savings_account_balance));

        db.close();
    }*/

    fun addUser(bank_user: BankUser){
        val db = this.writableDatabase;
        val values = ContentValues();
        values.put(FIRST_NAME, bank_user.FIRST_NAME);
        values.put(LAST_NAME, bank_user.LAST_NAME);
        values.put(EMAIL_ADDRESS, bank_user.EMAIL_ADDRESS);
        values.put(PASSWORD, bank_user.PASSWORD);
        values.put(MOBILE_NUMBER, bank_user.MOBILE_NUMBER);
        values.put(GENDER, bank_user.GENDER);
        //values.put(ACCOUNT_BALANCE, Integer.toString(randomizeIt()));
        values.put(ACCOUNT_BALANCE, bank_user.ACCOUNT_BALANCE);
        //values.put(SAVINGS_ACCOUNT_BALANCE, Integer.toString(randomizeIt()));
        values.put(SAVINGS_ACCOUNT_BALANCE, bank_user.SAVINGS_ACCOUNT_BALANCE);

        db.insert(TABLE_NAME, null, values);

        //val current_account_balance = randomizeIt();
        //Log.d("Account Balance", Integer.toString(current_account_balance));
        //val current_savings_account_balance = randomizeIt();
        //Log.d("Savings Account Balance", Integer.toString(current_savings_account_balance));

        db.close();
    }

    fun updateUserDetails(user_ID: String, first_name: String, last_name: String, email_address: String, password: String, phone: String, gender: String):Boolean{
        val db = this.writableDatabase;
        val values = ContentValues();
        values.put(ID, user_ID);
        values.put(FIRST_NAME, first_name);
        values.put(LAST_NAME, last_name);
        values.put(EMAIL_ADDRESS, email_address);
        values.put(PASSWORD, password);
        values.put(MOBILE_NUMBER, phone);
        values.put(GENDER, gender);

        db.update(TABLE_NAME, values,"$ID = ?", arrayOf(user_ID));
        return true;
    }

    fun updateBalance(logged_in_user: String, transfer_type: String, amount_to_transfer: String): String {
        val db = this.readableDatabase;
        val query = "SELECT $ID, $EMAIL_ADDRESS, $ACCOUNT_BALANCE, $SAVINGS_ACCOUNT_BALANCE from $TABLE_NAME";
        val cursor: Cursor = db.rawQuery(query, null)
        var account_holder_ID: Int
        var account_holder: String
        var existing_main_account_balance: String
        var existing_savings_account_balance: String
        var new_main_account_balance_amount: Int
        var new_savings_account_balance_amount: Int
        val values = ContentValues();
        if (cursor.moveToFirst()) {
            do {
                account_holder_ID = cursor.getInt(0)
                account_holder = cursor.getString(1)
                existing_main_account_balance = cursor.getString(2)
                existing_savings_account_balance = cursor.getString(3)
                if (logged_in_user == account_holder) {
                    if (transfer_type == "Current to Savings") {
                        new_main_account_balance_amount = Integer.parseInt(existing_main_account_balance) - Integer.parseInt(amount_to_transfer)
                        new_savings_account_balance_amount = Integer.parseInt(existing_savings_account_balance) + Integer.parseInt(amount_to_transfer)
                        values.put(ACCOUNT_BALANCE, new_main_account_balance_amount);
                        values.put(SAVINGS_ACCOUNT_BALANCE, new_savings_account_balance_amount)
                        db.update(TABLE_NAME, values,"$ID = ?", arrayOf(Integer.toString(account_holder_ID)));
                    } else if (transfer_type == "Savings to Current") {
                        new_savings_account_balance_amount = Integer.parseInt(existing_savings_account_balance) - Integer.parseInt(amount_to_transfer)
                        new_main_account_balance_amount = Integer.parseInt(existing_main_account_balance) + Integer.parseInt(amount_to_transfer)
                        values.put(ACCOUNT_BALANCE, new_main_account_balance_amount);
                        values.put(SAVINGS_ACCOUNT_BALANCE, new_savings_account_balance_amount)
                        db.update(TABLE_NAME, values,"$ID = ?", arrayOf(Integer.toString(account_holder_ID)));
                    }
                }
            } while (cursor.moveToNext())
        }
        return "ERROR! TRANSFER UNSUCCESSFUL!";
    }

    fun deleteClient(user_ID: String):Int{
        val db = this.writableDatabase;
        return db.delete(TABLE_NAME,"$ID = ?", arrayOf(user_ID));
    }

    fun doesEmailAlreadyExist(email_entered: String): Boolean {
        val db = this.readableDatabase;
        val query = "SELECT $EMAIL_ADDRESS from $TABLE_NAME";
        val cursor: Cursor = db.rawQuery(query, null)
        var already_existing_email: String
        if (cursor.moveToFirst()) {
            do {
                already_existing_email = cursor.getString(0)
                if (email_entered == already_existing_email) {
                    return true;
                }
            } while (cursor.moveToNext())
        }
        return false;
    }

    fun doesUsernameExist(username_attempt: String): Boolean {
        val db = this.readableDatabase;
        val query = "SELECT $EMAIL_ADDRESS from $TABLE_NAME";
        val cursor: Cursor = db.rawQuery(query, null)
        var username: String
        if (cursor.moveToFirst()) {
            do {
                username = cursor.getString(0)
                if (username_attempt == username) {
                    return true;
                }
            } while (cursor.moveToNext())
        }
        return false;
    }

    fun doesPasswordExist(password_attempt: String): Boolean {
        val db = this.readableDatabase;
        val query = "SELECT $PASSWORD from $TABLE_NAME";
        val cursor: Cursor = db.rawQuery(query, null)
        var password: String
        if (cursor.moveToFirst()) {
            do {
                password = cursor.getString(0)
                if (password_attempt == password) {
                    return true;
                }
            } while (cursor.moveToNext())
        }
        return false;
    }

    fun deleteDB(): String{
        return "Clients.db";
    }
}