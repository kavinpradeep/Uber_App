package g77.payment;

public class RegistrationPayment extends Payment {

	public RegistrationPayment(PaymentMode paymentMode, int amount) {
		super(paymentMode);
		this.amount = amount;
		
	}

	@Override
	public void makepayment() {
		Pay(this.amount);	
	}

}
