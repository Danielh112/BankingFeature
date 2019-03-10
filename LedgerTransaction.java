package me.DanielHuntley.BankingApp;


public class LedgerTransaction {

	private String accountId;
	private String accountType;
	private String initiatorType;
	private String dateTime; /* Stored as String as we aren't doing any manipulation */
	private double transactionValue;

	public LedgerTransaction(String accountId, String accountType, String initiatorType, String dateTime,
			double transactionValue) {
		 this.accountId = accountId;
		 this.accountType = accountType;
		 this.initiatorType = initiatorType;
		 this.dateTime = dateTime;
		 this.transactionValue = transactionValue;
	}

	public String getAccountId() {
		return accountId;
	}
	
	public String getTransactionType() {
		return accountType;
	}
	
	public String getInitiatorType() {
		return initiatorType;
	}
	
	public String getDateTime() {
		return dateTime;
	}
	
	public Double getTransactionValue() {
		return transactionValue;
	}
}
