package g77.payment;

public interface PaymentMode {

	public boolean hold(int amount);
	public boolean releasehold(int amount);
	public boolean Pay(int amount);
	
}
