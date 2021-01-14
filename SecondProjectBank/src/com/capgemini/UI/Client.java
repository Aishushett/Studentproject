package com.capgemini.UI;
import com.capgemini.Exceptions.InsufficientBalanceException;
import com.capgemini.Exceptions.InsufficientOpeningBalanceException;
import com.capgemini.Exceptions.InvalidAccountNumberException;
import com.capgemini.Service.SBIBank;


public class Client {

	public static void main(String[] args)  {
		SBIBank bank=new SBIBank();
		try {
			System.out.println("Create Account=");
			System.out.println(bank.createAccount(101, 2000));
			System.out.println(bank.createAccount(102, 5000));
		}catch(InsufficientOpeningBalanceException iob)
		{
			System.out.println("Insufficient Opening Balance..Minimum Balance 500Rs Required");
		}
		try
		{
			System.out.println("Withdraw Amount=");
			System.out.println("Balance="+bank.withdrawAmount(101, 1000));
		}catch(InsufficientBalanceException e) {
			System.out.println("Insufficient Balance Exception");
			}
		catch(InvalidAccountNumberException e) {
			System.out.println("Invalid Account Number");
		}try
		{
			System.out.println("Depositing Amount=");
			System.out.println("Balance="+bank.DepositeAmount(101, 500));
		}catch(InvalidAccountNumberException e)
		{
			System.out.println("Invalid Account Number");
		}try {
			System.out.println("Fund Transferring=");
			int[] a=bank.fundTransfer(101, 102, 200);
			System.out.println("Balance of Account Number 101="+a[0]);
			System.out.println("Balance of Account Number 102="+a[1]);
		}catch(InvalidAccountNumberException e)
		{
			System.out.println("Invalid Account Number");
		
		}catch(InsufficientBalanceException e)
		{
			System.out.println("Insufficient Balance Exception");
		}
		
	}
}


