package g77.common.entities;

import g77.payment.PaymentMode;

public class Passenger extends User {

	@Override
	public String toString() {
		return "Passenger [name=" + name + ", userId=" + userId + " paymentPreference=" + paymentPreference + "]";
	}
	
	
	
}
 