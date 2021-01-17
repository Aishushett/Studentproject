package com.capgemini.service;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsuffiecientOpeningBalanceException ;
import com.capgemini.exceptions.InvalidAccountNumberException;

public interface Bank {
	String createAccount(int accountNumber, int amount)throws InsuffiecientOpeningBalanceException ;
	int withdrawAmount(int accountNumber, int amount)throws InvalidAccountNumberException,InsufficientBalanceException;
	int depositeAmount(int accountNumber, int amount)throws InvalidAccountNumberException;
	int[] fundTransfer(int accountNumberOne, int accountNumberTwo,int amount)throws InvalidAccountNumberException,InsufficientBalanceException;

}


