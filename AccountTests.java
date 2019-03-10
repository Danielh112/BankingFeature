package me.DanielHuntley.BankingApp;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class AccountTests {

	
	/* Test Add balance to the current account */
	
	@Test
	void currentAccountaddBalance() {
		CurrentAccount currentAccount = new CurrentAccount("1");
		currentAccount.addFunds(20.00);
		currentAccount.addFunds(1.00);
		Assert.assertEquals(Double.valueOf(21), currentAccount.getBalance());
	}
	
	/* Test subtract balance to the current account*/
	
	@Test
	void currentAccountsubtractBalance() {
		CurrentAccount currentAccount = new CurrentAccount("2");
		currentAccount.addFunds(20.00);
		currentAccount.withdrawFunds(3.00);
		Assert.assertEquals(Double.valueOf(17), currentAccount.getBalance());
	}
	
	/* Test Add balance to the savings account */
	
	@Test
	void savingsAccountaddBalance() {
		SavingsAccount savingsAccount = new SavingsAccount("1");
		savingsAccount.addFunds(20.00);
		savingsAccount.addFunds(1.00);
		Assert.assertEquals(Double.valueOf(21), savingsAccount.getBalance());
	}
	
	/* Test subtract balance to the savings account*/
	
	@Test
	void savingsAccountsubtractBalance() {
		SavingsAccount savingsAccount = new SavingsAccount("2");
		savingsAccount.addFunds(20.00);
		savingsAccount.withdrawFunds(3.00);
		Assert.assertEquals(Double.valueOf(17), savingsAccount.getBalance());
	}
	
	/* Validate that the two accounts do not contaminant one another */
	
	@Test
	void distinctAccountTest() {
		CurrentAccount currentAccount = new CurrentAccount("2");
		SavingsAccount savingsAccount = new SavingsAccount("2");
		currentAccount.addFunds(20.00);
		savingsAccount.addFunds(20.00);
		savingsAccount.withdrawFunds(3.00);
		Assert.assertEquals(Double.valueOf(17), savingsAccount.getBalance());
	}
}
