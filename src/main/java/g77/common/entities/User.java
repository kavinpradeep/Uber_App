package g77.common.entities;

import java.util.UUID;

public abstract class User {

		protected String name;
		protected String address; 
		protected String contactnumber;
		protected String email;
		protected String password;
		protected PaymentPreference paymentPreference;
		
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		protected UUID userId;
		
		
		public UUID getUserId() {
			return userId;
		}
		public void setUserId(UUID userId) {
			this.userId = userId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getContactnumber() {
			return contactnumber;
		}
		public void setContactnumber(String contactnumber) {
			this.contactnumber = contactnumber;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}

		public PaymentPreference getPaymentPreference() {
			return paymentPreference;
		}

		public void setPaymentPreference(PaymentPreference paymentPreference) {
			this.paymentPreference = paymentPreference;
		}
}
