package com.capgemini.Service;
import com.capgemini.Exceptions.InsufficientBalanceException;
import com.capgemini.Exceptions.InsufficientOpeningBalanceException;
import com.capgemini.Exceptions.InvalidAccountNumberException;

public interface Bank {
	String createAccount(int accountNumber, int amount)throws InsufficientOpeningBalanceException;
	int withdrawAmount(int accountNumber, int amount)throws InvalidAccountNumberException,InsufficientBalanceException;
	int DepositeAmount(int accountNumber, int amount)throws InvalidAccountNumberException;
	int[] fundTransfer(int accountNumberOne, int accountNumberTwo,int amount)throws InvalidAccountNumberException,InsufficientBalanceException;
	

}
