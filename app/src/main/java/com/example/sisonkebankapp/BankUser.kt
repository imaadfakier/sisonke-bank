package com.example.sisonkebankapp

class BankUser {
    var FIRST_NAME: String? = null;
    var LAST_NAME: String? = null;
    var EMAIL_ADDRESS: String? = null;
    var PASSWORD: String? = null;
    var MOBILE_NUMBER: String? = null
    var GENDER: String? = null
    var ACCOUNT_BALANCE: String? = null
    var SAVINGS_ACCOUNT_BALANCE: String? = null

    constructor(){};

    constructor(FIRST_NAME: String,
                LAST_NAME: String,
                EMAIL_ADDRESS: String,
                PASSWORD: String,
                MOBILE_NUMBER: String,
                GENDER: String,
                ACCOUNT_BALANCE: String,
                SAVINGS_ACCOUNT_BALANCE: String){
        this.FIRST_NAME = FIRST_NAME;
        this.LAST_NAME = LAST_NAME;
        this.EMAIL_ADDRESS = EMAIL_ADDRESS;
        this.PASSWORD = PASSWORD;
        this.MOBILE_NUMBER = MOBILE_NUMBER;
        this.GENDER = GENDER;
        this.ACCOUNT_BALANCE = ACCOUNT_BALANCE;
        this.SAVINGS_ACCOUNT_BALANCE = SAVINGS_ACCOUNT_BALANCE;
    }

    //Getters:
    fun getAccountHolderFirstName():String?{
        return this.FIRST_NAME;
    }

    fun getAccountHolderLastName():String?{
        return this.LAST_NAME;
    }

    fun getAccountHolderEmailAddress():String?{
        return this.EMAIL_ADDRESS;
    }

    fun getAccountHolderPassword():String?{
        return this.PASSWORD;
    }

    fun getAccountHolderMobileNumber():String?{
        return this.MOBILE_NUMBER;
    }

    fun getAccountHolderGender():String?{
        return this.GENDER;
    }

    fun getAccountHolderCurrentAccountBalance():String?{
        return this.ACCOUNT_BALANCE;
    }

    fun getAccountHolderCurrentSavingsAccountBalance():String?{
        return this.SAVINGS_ACCOUNT_BALANCE;
    }

    //Setters:
    fun setAccountHolderFirstName(account_holder_first_name: String){
        this.FIRST_NAME = account_holder_first_name;
    }

    fun setAccountHolderLastName(account_holder_last_name: String){
        this.LAST_NAME = account_holder_last_name;
    }

    fun setAccountHolderEmailAddress(account_holder_email_address: String){
        this.EMAIL_ADDRESS = account_holder_email_address;
    }

    fun setAccountHolderPassword(account_holder_password: String){
        this.PASSWORD = account_holder_password;
    }

    fun setAccountHolderMobileNumber(account_holder_mobile_number: String){
        this.MOBILE_NUMBER = account_holder_mobile_number;
    }

    fun setAccountHolderGender(account_holder_gender: String){
        this.GENDER = account_holder_gender;
    }

    fun setAccountHolderCurrentAccountBalance(account_holder_current_account_balance: String){
        this.ACCOUNT_BALANCE = account_holder_current_account_balance;
    }

    fun setAccountHolderCurrentSavingsAccountBalance(account_holder_current_savings_account_balance: String){
        this.SAVINGS_ACCOUNT_BALANCE = account_holder_current_savings_account_balance;
    }
}