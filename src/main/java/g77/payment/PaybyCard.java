package g77.payment;

public class PaybyCard implements PaymentMode{

	String Name;
	String CardNumber;
	String CVCNumber;
	String ValidTill;
	
	public PaybyCard(String Name, String CardNumber, String CVCNumber, String ValidTill){
		this.Name = Name;
		this.CardNumber = CardNumber;
		this.CVCNumber = CVCNumber;
		this.ValidTill = ValidTill;
	}
	
	public boolean Pay(int amount) {
		
		System.out.println("PaybyCard - Payment Succcess: "+ amount + " dollars paid Successfully by "+ Name );
		return true;
	}

	public boolean hold(int amount) {
		
		System.out.println("PaybyCard - Payment Hold Succcess: "+ amount + " dollars holded  Successfully from "+ Name );
		return true;
	}

	public boolean releasehold(int amount) {
		
		System.out.println("PaybyCard - Payment Release Succcess: "+ amount + " dollars hold released  Successfully from "+ Name );
		return true;
	}
}
