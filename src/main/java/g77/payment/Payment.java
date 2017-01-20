package g77.payment;

public abstract class Payment {

	int amount;
	protected PaymentMode paymentMode;
	
	public abstract void makepayment();
	
	public Payment(PaymentMode paymentMode){
		
		this.paymentMode = paymentMode;
	}
	
	protected boolean Pay(int amount) {
		
		return paymentMode.Pay(amount);
		
	}
	
	protected boolean hold(int amount) {
		
		return paymentMode.hold(amount);
		
	}

	protected boolean releasehold(int amount) {
		
		return paymentMode.releasehold(amount);
	}
	
}
