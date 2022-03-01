package com.example.exercise6;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Author {
	
	private int id;
	@NotBlank
	@Size(min=2)
	public String name;
	public String country;
	public int dob;
	public int qtyBooks;
	public Boolean alive;
	
	public Author() {
	}

	public Author(int id, String name, String country, int dob, int qtyBooks, Boolean alive) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.dob = dob;
		this.qtyBooks = qtyBooks;
		this.alive = alive;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getDob() {
		return dob;
	}

	public void setDob(int dob) {
		this.dob = dob;
	}

	public int getQtyBooks() {
		return qtyBooks;
	}

	public void setQtyBooks(int qtyBooks) {
		this.qtyBooks = qtyBooks;
	}

	public Boolean getAlive() {
		return alive;
	}

	public void setAlive(Boolean alive) {
		this.alive = alive;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", country=" + country + ", dob=" + dob + ", qtyBooks="
				+ qtyBooks + ", alive=" + alive + "]";
	}
	
}
	
