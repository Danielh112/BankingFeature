package me.DanielHuntley.BankingApp;

public interface Account {

	public Double withdrawFunds(Double amount);
	
	public void addFunds(Double amount);

	public Double getBalance();
	
	public String getAccountId();
	
}
