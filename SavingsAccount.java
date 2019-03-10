package me.DanielHuntley.BankingApp;

public class SavingsAccount implements Account {

	private String accountId;
	private Double accountBalance = 0.00;
	
	public SavingsAccount(String accountId) {
		this.accountId = accountId;
	}
	
	/* Attempt to withdraw the maximum available amount */
	
	public Double withdrawFunds(Double amount) {
		if (getBalance() - Math.abs(amount) >= 0) {
			accountBalance -= Math.abs(amount);
			return Math.abs(amount);
		} else {
			System.out.println("Only able to withdraw " + getBalance() + " of " + Math.abs(amount));
			Double currentBalance = accountBalance;
			accountBalance = 0.00;
			return currentBalance;
		}
	}
	
	public void addFunds(Double amount) {
		accountBalance += Math.abs(amount);
	}

	public Double getBalance() {
		return accountBalance;
	}
	
	public String getAccountId() {
		return accountId;
	}
}
