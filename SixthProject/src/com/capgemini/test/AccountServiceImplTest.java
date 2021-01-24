

package com.capgemini.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.beans.Account;
import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsuffiecientOpeningBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.repo.AccountRepo;
import com.capgemini.service.AccountService;
import com.capgemini.service.AccountServiceImpl;

public class AccountServiceImplTest {
	AccountService accountService;

	public static Account account = new Account();
	@Mock
	AccountRepo accountRepo;
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);

		accountService = new AccountServiceImpl(accountRepo);
	}


	@Test(expected=InsuffiecientOpeningBalanceException.class)
	public void whenTheAmountIsLessThanFiveHundredSystemShouldThrowException() throws InsuffiecientOpeningBalanceException
	{
		accountService.createAccount(101, 400);
	}

	@Test
	public void whenTheValidInfoIsPassedAccountShouldBeCreatedSuccessfully() throws InsuffiecientOpeningBalanceException
	{
		 account =new Account(101,2000);
		when(accountRepo.save(account)).thenReturn(true);
		assertEquals(account, accountService.createAccount(101, 5000));
	}

	@Test(expected=InvalidAccountNumberException.class)
	public void whenTheInvalidAccountNumberIsPassedDuringDepositSystemShouldThrowException() throws InvalidAccountNumberException,InsuffiecientOpeningBalanceException
	{
		accountService.depositeAmount(101, 2000);
	}
	@Test
	public void whenTheValidInfoIsPassedAmountShouldBeDeposited() throws InvalidAccountNumberException, InsufficientBalanceException,InsuffiecientOpeningBalanceException
	{	
		 account = new Account(101,600);
		when(accountRepo.searchAccount(101)).thenReturn(account);
		assertEquals(account, accountService.depositeAmount(101, 5000));
	}
	@Test(expected=InvalidAccountNumberException.class)
	public void whenTheInvalidAccountNumberIsPassedDuringWithdrawSystemShouldThrowException() throws InvalidAccountNumberException, InsufficientBalanceException, InsuffiecientOpeningBalanceException
	{
		accountService.withdrawAmount(100, 2000);
	}
	@Test(expected=InsufficientBalanceException.class)
	public void whenTheWithdrawnAmountIsGreaterThanCurrentBalanceSystemShouldThrowException() throws InvalidAccountNumberException, InsufficientBalanceException, InsuffiecientOpeningBalanceException
	{
		account = new Account(101,700);
		when(accountRepo.searchAccount(101)).thenReturn(account);
		accountService.withdrawAmount(101, 800);
	}
	@Test
	public void whenTheValidInfoIsPassedAmountShouldBeWithdrawn() throws InvalidAccountNumberException, InsufficientBalanceException,InsuffiecientOpeningBalanceException
	{
		account = new Account(101, 600);
		when(accountRepo.searchAccount(101)).thenReturn(account);
		assertEquals(account, accountService.withdrawAmount(101, 300));
	}
}