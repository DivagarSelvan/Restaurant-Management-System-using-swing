package com.model;

import java.util.Date;

public class Sales {
	private String Customer_ID, Product_ID;
	private Date date;

	public Sales() {
		// TODO Auto-generated constructor stub
	}

	public String getCustomer_ID() {
		return Customer_ID;
	}

	public void setCustomer_ID(String customer_ID) {
		Customer_ID = customer_ID;
	}

	public String getProduct_ID() {
		return Product_ID;
	}

	public void setProduct_ID(String product_ID) {
		Product_ID = product_ID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void viewSaleDetails() {

	}

}
