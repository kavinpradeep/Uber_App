package g77.payment;

public class RidePayment  extends Payment {

	int holdAmount=0;
	int fare=0;
	int tip=0;
	public RidePayment(PaymentMode paymentMode) {
		super(paymentMode);	
	}

	public void setRideFare(int fare, int tip){
		this.fare = fare;
		this.tip = tip;
	}
	
	@Override
	public void makepayment() {
		
		this.amount = this.fare+ this.tip;		
		Pay(this.amount);	
		
	}

	public void holdPayment(int amount){
		
		this.holdAmount=amount;
		this.hold(holdAmount);
	}
	
	public void releaseholdPayment(){
		if(holdAmount>0)
			this.releasehold(holdAmount);
	}
}
