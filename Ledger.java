package me.DanielHuntley.BankingApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Ledger {

	private Customer customer;
	private List<LedgerTransaction> ledgerTransactions = new ArrayList<LedgerTransaction>();
	private String fileName;
	
	
	public Ledger() {
		customer = new Customer();
	}
	
	/* Read in ledger File */
	
	public void getLedger(String fileName) {
		this.fileName = fileName;
				
		BufferedReader reader = null;

		try {
			String line;
			File file = new File(fileName);
			reader = new BufferedReader(new FileReader(file));

			reader.readLine();
			while ((line = reader.readLine()) != null) {
				String[] transaction = line.split(",");
				/* Check for blank lines at end of file */
				if (line.length() > 0) {
					LedgerTransaction lt = new LedgerTransaction(transaction[0], transaction[1], transaction[2],
							transaction[3], Double.parseDouble(transaction[4]));
					updateLedger(lt);
					updateAccounts(lt);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
				generateNewLedger();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/* Update ledgers customer accounts */ 
	
	public void updateAccounts(LedgerTransaction lt) {
		if (lt.getTransactionType().equals("CURRENT")) {

			if (customer.getCurrentAccount() == null) {
				customer.openCurrentAccount(lt.getAccountId());
			}
			customer.setCurrentAccountBalance(this, lt.getTransactionValue());
		} else if (lt.getTransactionType().equals("SAVINGS")) {
			if (customer.getSavingsAccount() == null) {
				customer.openSavingsAccount(lt.getAccountId());
			}
			customer.setSavingsAccountBalance(lt.getTransactionValue());
		} else {
			new Error("Transaction type must be of type CURRENT or SAVINGS");
		}
	}

	/* Once new transactions have been added generate new ledger named output_{fileName} */
	
	public void generateNewLedger() {
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter("output_" + fileName));
			StringBuilder sb = new StringBuilder();

			sb.append("AccountID,");
			sb.append("AccountType,");
			sb.append("InitiatorType,");
			sb.append("DateTime,");
			sb.append("TransactionValue, \n");

			for (LedgerTransaction attribute : ledgerTransactions) {
				sb.append(attribute.getAccountId() + ",");
				sb.append(attribute.getTransactionType() + ",");
				sb.append(attribute.getInitiatorType() + ",");
				sb.append(attribute.getDateTime() + ",");
				sb.append(attribute.getTransactionValue() + "\n");
			}
			br.write(sb.toString());
			br.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void updateLedger(LedgerTransaction lt) {
		ledgerTransactions.add(lt);
	}

	public List<LedgerTransaction> getTransactions() {
		return ledgerTransactions;
	}

	public LedgerTransaction getTransaction(int i) {
		return ledgerTransactions.get(i);
	}
}
