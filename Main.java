package me.DanielHuntley.BankingApp;

public class Main {

	public static void main(String[] args) {
		if (args[0] == null) {
			new Error("Please provide a filename");
		} else {
			String fileName = args[0];
			Ledger ledger = new Ledger();
			ledger.getLedger(fileName);
		}
	}

}
