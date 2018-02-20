package it.algoritmi.test;

public class PredatoryCreditCard extends CreditCard {
	private double rate;

	public PredatoryCreditCard(String customer, String bank, String account, 
								int limit, double initialBalance, double rate) {
		super(customer, bank, account, limit, initialBalance);
		this.rate = rate;
	}

	public void processMoth() {
		if(balance > 0) {
			double monthlyFactor = Math.pow(1 + rate, 1. / 12);
			balance *= monthlyFactor;
		}
	}

	@Override
	public boolean charge(double price) {
		boolean isSuccess = super.charge(price);
		if(!isSuccess) {
			balance += 5;
		}
		return isSuccess;
	}
	
}
