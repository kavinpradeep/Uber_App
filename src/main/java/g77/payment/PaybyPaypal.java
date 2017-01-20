package g77.payment;

public class PaybyPaypal implements PaymentMode{

	String loginName;
	String password;
	
	public PaybyPaypal(String loginName, String password){
		this.loginName = loginName;
		this.password = password;
	}
	
	public boolean Pay(int amount) {
		
		System.out.println("PaybyPaypal - Payment Succcess: "+ amount + " dollars paid Successfully by "+ loginName );
		return true;
	}

	public boolean hold(int amount) {
		
		System.out.println("PaybyPaypal - Payment Hold Succcess: "+ amount + " dollars holded  Successfully from "+ loginName );
		return true;
	}

	public boolean releasehold(int amount) {
		
		System.out.println("PaybyPaypal - Payment Hold Succcess: "+ amount + " dollars hold released  Successfully from "+ loginName );
		return true;
	}
}
