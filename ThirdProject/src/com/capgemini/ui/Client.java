package com.capgemini.ui;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsuffiecientOpeningBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.service.BankRunner;
import com.capgemini.service.SBIBank;

public class Client {

	public static void main(String[] args) {
		SBIBank bank=new SBIBank();
		BankRunner bankrunner=new BankRunner(bank);
		try {
		System.out.println("Account creation");
		System.out.println(bank.createAccount(101,5000));
		System.out.println(bank.createAccount(102,8500));
		Thread firstThread=new Thread(bankrunner,"first");
		firstThread.start();
		Thread secondThread=new Thread(bankrunner,"second");
		secondThread.start();
		System.out.println("After depositing amount in account no 101 is : "+bank.depositeAmount(101,3000));
		int []bal=bank.fundTransfer(101, 102, 3000);
		System.out.println("After fund transfer new balance of accountOne: "+bal[0]+"  New balance of accountTwo: "+bal[1]);
		}catch(InsuffiecientOpeningBalanceException e) {
			System.out.println("Insufficient amount for account opening....minimum balance must be 500");
		}	catch(InsufficientBalanceException e) {
			System.out.println("insufficient balance");
		}catch(InvalidAccountNumberException e) {
			System.out.println("invalid account number");
		}
	}
}



