package com.capgemini.service;

import java.util.LinkedList;

import com.capgemini.beans.Account;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsuffiecientOpeningBalanceException ;
import com.capgemini.exceptions.InvalidAccountNumberException;

public class SBIBank implements Bank{
	private LinkedList<Account>accounts=new LinkedList<>();
	@Override
	public String createAccount(int accountNumber, int amount) throws InsuffiecientOpeningBalanceException  {
		if(amount>=500) 
		{
			Account account=new Account(accountNumber,amount);
			accounts.add(account);
			return "Account created Successfully";
		}
		throw new InsuffiecientOpeningBalanceException ();
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
	
	public int withdrawAmount(int accountNumber,int amount) throws InvalidAccountNumberException, InsufficientBalanceException {
		Account account=searchAccount(accountNumber);
		synchronized(account){
			if((account.getAmount()-amount)>=500)
			{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				account.setAmount(account.getAmount()-amount);
				return account.getAmount();
			}

			throw new InsufficientBalanceException();
		}
	}
	@Override
	public int depositeAmount(int accountNumber, int amount)throws InvalidAccountNumberException {
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
			accountOne.setAmount(accountOne.getAmount()-amount);
			accountTwo.setAmount(accountTwo.getAmount()+amount);
			int[] bal= {accountOne.getAmount(),accountTwo.getAmount()};
			return bal;
		}
		else throw new InsufficientBalanceException();
	}
}


