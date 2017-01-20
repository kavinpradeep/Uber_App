package g77.registrationlogin;

import java.util.UUID;

import g77.common.entities.PaymentPreference;
import g77.common.entities.PaymentType;
import g77.common.entities.User;
import g77.payment.PaybyCard;
import g77.payment.PaymentMode;
import g77.payment.RegistrationPayment;


public abstract class Registration {
	
	private static final int RegistrationAmount = 100;
	
	//Template Method
	public void CreateProfile(User user){
		
		User dbUser = RegistrationLoginSubsystem.getRegistrationLoginSubsystem().CreateUserinDB(user);
		
		AddLoginCredentials(dbUser,user);
		AddPersonalDetails(dbUser, user);
		AddPaymentPreference(dbUser,user);
		
		if(this.isAnyAdditonalProfileCreation()){
			AdditonalProfileCreation(dbUser,user);
		}
		
		ProcessRegistrationPayment(dbUser,user);
		
		RegistrationLoginSubsystem.getRegistrationLoginSubsystem().StoreUserinDB(dbUser);
	}
	


	private void AddLoginCredentials(User dbUser, User user){
		
		dbUser.setUserId(UUID.randomUUID());
		dbUser.setPassword(user.getPassword());
	}
	
	private void AddPersonalDetails(User dbUser, User user){
		
		dbUser.setName(user.getName());
		dbUser.setAddress(user.getAddress());
		dbUser.setEmail(user.getEmail());
		dbUser.setContactnumber(user.getContactnumber());		
	}
	
	private void AddPaymentPreference(User dbUser, User user){
		
		dbUser.setPaymentPreference(user.getPaymentPreference());		
	
	}
	
	private void ProcessRegistrationPayment(User dbUser, User user) {

		PaymentPreference paymentPreference = user.getPaymentPreference();
		PaymentMode paymentMode = new PaybyCard(paymentPreference.getName(),paymentPreference.getCardNumber(),paymentPreference.getCVCNumber(),paymentPreference.getValidTill());
		
    	RegistrationPayment registrationPayment = new RegistrationPayment(paymentMode,RegistrationAmount);
    	
    	registrationPayment.makepayment();
		
	}
	
	public boolean isAnyAdditonalProfileCreation(){
		return false;
	}
		
	protected  abstract  void AdditonalProfileCreation(User dbUser, User user);

}
