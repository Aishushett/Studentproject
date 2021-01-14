package com.capgemini.Service;
import java.util.LinkedList;

import com.capgemini.Exceptions.InsufficientBalanceException;
import com.capgemini.Exceptions.InsufficientOpeningBalanceException;
import com.capgemini.Exceptions.InvalidAccountNumberException;
import com.capgemini.beans.Account;
public class SBIBank implements Bank {
LinkedList<Account>accounts=new LinkedList<>();
@Override
	public String createAccount(int accountNumber, int amount) throws InsufficientOpeningBalanceException {
		if(amount>=500) 
		{
			Account account=new Account(accountNumber,amount);
			accounts.add(account);
			return "Account created Successfully";
		}
		else throw new InsufficientOpeningBalanceException();
	}
private Account searchAccount(int accountNumber)throws InvalidAccountNumberException
{
	for(Account account:accounts)
	{
		if(account.getAccountNumber()==accountNumber)
		{
			return account;
		}
	}throw new InvalidAccountNumberException();
}
@Override
public int withdrawAmount(int accountNumber,int amount) throws InvalidAccountNumberException,InsufficientBalanceException {
		
		Account account=searchAccount(accountNumber);
		if (account.getAmount()-amount>=0)
{
	account.setAmount(account.getAmount()-amount);
	return account.getAmount();
}
		throw new InsufficientBalanceException();
	}
@Override
	public int DepositeAmount(int accountNumber, int amount)throws InvalidAccountNumberException {
		Account account=searchAccount(accountNumber);
		account.setAmount(account.getAmount()+amount);
		return account.getAmount();
	}
@Override
	public int[] fundTransfer(int accountNumberOne, int accountNumberTwo, int amount)throws InvalidAccountNumberException,InsufficientBalanceException {
		Account accountOne=searchAccount(accountNumberOne);
		Account accountTwo=searchAccount(accountNumberTwo);
		if(accountOne.getAmount()-amount>=0)
{
	int[] a= {accountOne.getAccountNumber()-amount,accountTwo.getAmount()+amount};
	accountOne.setAmount(accountOne.getAmount()-amount);
	accountTwo.setAmount(accountTwo.getAmount()+amount);
	return a;
	}
		else throw new InsufficientBalanceException();
	}
}
