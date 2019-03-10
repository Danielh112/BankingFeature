package me.DanielHuntley.BankingApp;

public class CurrentAccount implements Account {

	private String accountId;
	private Double accountBalance = 0.00;

	
	public CurrentAccount(String accountId) {
		this.accountId = accountId;
	}
	
	public Double withdrawFunds(Double amount) {
		return accountBalance -= Math.abs(amount);
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
