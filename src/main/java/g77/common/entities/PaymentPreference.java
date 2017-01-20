package g77.common.entities;

public class PaymentPreference {
	PaymentType paymentType;
	String Name;
	String CardNumber;
	String CVCNumber;
	String ValidTill;
	String paypalLoginName;
	String paypalpassword;
	
	
	public PaymentType getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getCardNumber() {
		return CardNumber;
	}
	public void setCardNumber(String cardNumber) {
		CardNumber = cardNumber;
	}
	public String getCVCNumber() {
		return CVCNumber;
	}
	public void setCVCNumber(String cVCNumber) {
		CVCNumber = cVCNumber;
	}
	public String getValidTill() {
		return ValidTill;
	}
	public void setValidTill(String validTill) {
		ValidTill = validTill;
	}
	public String getPaypalLoginName() {
		return paypalLoginName;
	}
	public void setPaypalLoginName(String paypalLoginName) {
		this.paypalLoginName = paypalLoginName;
	}
	public String getPaypalpassword() {
		return paypalpassword;
	}
	public void setPaypalpassword(String paypalpassword) {
		this.paypalpassword = paypalpassword;
	}
	@Override
	public String toString() {
		return "PaymentPreference [paymentType=" + paymentType + "]";
	}
	
	
	
	
}
