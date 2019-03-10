package me.DanielHuntley.BankingApp;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class LedgerTests {

	/* Test that the valid system transactions are added to the ledger */
	
	@Test
	void testSmartBanking() {
		Ledger ledger = new Ledger();
		
		ledger.updateLedger(new LedgerTransaction("1", "SAVINGS", "ACCOUNT-HOLDER", "2018-12-30T09:10:00Z", 5.00));
		ledger.updateLedger(new LedgerTransaction("4", "CURRENT", "ACCOUNT-HOLDER", "2018-12-30T09:10:00Z", -5.00));
		ledger.updateLedger(new LedgerTransaction("1", "SAVINGS", "ACCOUNT-HOLDER", "2018-12-30T09:13:00Z", 5.00));
		ledger.updateLedger(new LedgerTransaction("4", "CURRENT", "ACCOUNT-HOLDER", "2018-12-30T09:13:00Z", 5.00));
		
		for (int i = 0; i < ledger.getTransactions().size(); i++) {
			ledger.updateAccounts(ledger.getTransaction(i));
		}
		
		/* Current account value is negative so 5.00 should be withdrawn from the current account */
		Assert.assertEquals(ledger.getTransaction(4).getAccountId(), "1");
		Assert.assertEquals(ledger.getTransaction(4).getTransactionType(), "SAVINGS");
		Assert.assertEquals(ledger.getTransaction(4).getInitiatorType(), "SYSTEM");
		Assert.assertEquals(ledger.getTransaction(4).getTransactionValue(), Double.valueOf(-5));

		/* Current account should be credited 5.00 */
		Assert.assertEquals(ledger.getTransaction(5).getAccountId(), "4");
		Assert.assertEquals(ledger.getTransaction(5).getTransactionType(), "CURRENT");
		Assert.assertEquals(ledger.getTransaction(5).getInitiatorType(), "SYSTEM");
		Assert.assertEquals(ledger.getTransaction(5).getTransactionValue(), Double.valueOf(5));
		
	}
	
	/* Only partial amount should be within drawn from savings */
	
	@Test
	void testInvalidSmartBanking() {
		Ledger ledger = new Ledger();
		
		ledger.updateLedger(new LedgerTransaction("1", "SAVINGS", "ACCOUNT-HOLDER", "2018-12-30T09:10:00Z", 3.00));
		ledger.updateLedger(new LedgerTransaction("4", "CURRENT", "ACCOUNT-HOLDER", "2018-12-30T09:10:00Z", -5.00));
		
		for (int i = 0; i < ledger.getTransactions().size(); i++) {
			ledger.updateAccounts(ledger.getTransaction(i));
		}
		
		/* Assert that only a partial amount has been withdrawn */
		Assert.assertEquals(ledger.getTransaction(2).getAccountId(), "1");
		Assert.assertEquals(ledger.getTransaction(2).getTransactionType(), "SAVINGS");
		Assert.assertEquals(ledger.getTransaction(2).getInitiatorType(), "SYSTEM");
		Assert.assertEquals(ledger.getTransaction(2).getTransactionValue(), Double.valueOf(-3));
		
	}
	
	/* Validate that with no savings, no money should be withdrawn */
	
	@Test
	void testEmptySavings() {
		Ledger ledger = new Ledger();
		
		ledger.updateLedger(new LedgerTransaction("4", "CURRENT", "ACCOUNT-HOLDER", "2018-12-30T09:10:00Z", -5.00));
		
		for (int i = 0; i < ledger.getTransactions().size(); i++) {
			ledger.updateAccounts(ledger.getTransaction(i));
		}
		
		/* Assert that no new transactions have been added as there is no money which can be withdrawn from savings */
		Assert.assertEquals(ledger.getTransactions().size(), 1);
		
	}
}
