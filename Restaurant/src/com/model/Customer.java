package com.model;

public class Customer {
	private String customer_ID, customerName, gender, email, address;
	private long mobile_Number;

	public Customer() {
// TODO Auto-generated constructor stub
	}

	/************* Getter and Setter Methods Here ******************/
	public String getCustomer_ID() {
		return customer_ID;
	}

	public void setCustomer_ID(String customer_ID) {
		this.customer_ID = customer_ID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getMobile_Number() {
		return mobile_Number;
	}

	public void setMobile_Number(long mobile_Number) {
		this.mobile_Number = mobile_Number;
	}
}