package me.DanielHuntley.BankingApp;

import java.time.LocalDateTime;

public class Customer {

	private CurrentAccount currentAccount;
	private SavingsAccount savingsAccount;

	public CurrentAccount getCurrentAccount() {
		return currentAccount;
	}

	public SavingsAccount getSavingsAccount() {
		return savingsAccount;
	}

	public void openCurrentAccount(String accountId) {
		currentAccount = new CurrentAccount(accountId);
	}

	public void openSavingsAccount(String accountId) {
		savingsAccount = new SavingsAccount(accountId);
	}

	/* 
	 * To prevent overdraft charges if the current account falls below 0.00, withdrawn available funds
	 * from the savings account. 
	 * Note: Once complete the new transactions will be added to the ledger
	*/
	
	public void smartBankingFeature(Ledger ledger, Double balance) {

		if (getSavingsAccount() != null && getSavingsAccount().getBalance() >= 0 && balance < 0 ) {
			
			Double availableAmount = getSavingsAccount().withdrawFunds(balance);
			if (availableAmount > 0) {
				getCurrentAccount().addFunds(availableAmount);

				LocalDateTime dateTime = LocalDateTime.now();

				availableAmount = (double) Math.round(availableAmount * 100) / 100;

				ledger.updateLedger(new LedgerTransaction(getSavingsAccount().getAccountId(), "SAVINGS", "SYSTEM",
						dateTime.toString(), Math.abs(availableAmount) * -1));
				ledger.updateLedger(new LedgerTransaction(getCurrentAccount().getAccountId(), "CURRENT", "SYSTEM",
						dateTime.toString(), Math.abs(availableAmount)));
			}
		}
	}

	/* Update the current account balance and if below 0 call the smart banking functionality */
	
	public void setCurrentAccountBalance(Ledger ledger, Double amount) {
		if (amount < 0) {
			getCurrentAccount().withdrawFunds(amount);
			smartBankingFeature(ledger, getCurrentAccount().getBalance());
		} else {
			getCurrentAccount().addFunds(amount);
		}
	}

	/* 
	 * Update the savings account balance
	 * Note: the balance cannot go below 0
	 */
	
	public void setSavingsAccountBalance(Double amount) {
		if (amount < 0) {
			getSavingsAccount().withdrawFunds(amount);
		} else {
			getSavingsAccount().addFunds(amount);
		}
	}
}
