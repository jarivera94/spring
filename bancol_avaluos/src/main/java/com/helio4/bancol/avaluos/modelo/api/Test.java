package com.helio4.bancol.avaluos.modelo.api;


import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.validator.constraints.NotBlank;


@XmlRootElement(name = "test")
@XmlAccessorType(XmlAccessType.PROPERTY)
@XmlType(propOrder = { "line1", "line2", "line3", "city", 
		"state", "country", "postalCode", "phone"})
public class Test {

	/**
	 * Address line 1
	 */

	private String line1;

	/**
	 * Address line 2
	 */
	private String line2;

	/**
	 * Address line 3
	 */
	private String line3;

	/**
	 * The city	name
	 */
	private String city;

	/**
	 * The state or province name
	 */
	private String state;

	/**
	 * ISO Country code
	 */
	private String country;

	/**
	 * The postal code
	 */
	private String postalCode;

	/**
	 * The phone number
	 */
	private String phone;

	// ----------------------------------------------
	// CONSTRUCTORS
	// ----------------------------------------------

	/**
	 * Default Constructor 
	 */
	public Test() {
	}

	/**
	 * Constructor from Address Object 
	 */
	public Test(Test address) {

	}


	// ----------------------------------------------
	// GETTERS AND SETTERS
	// ----------------------------------------------


	/**
	 * Returns the address line 1
	 * @return the line1
	 */
	@NotBlank(message = "prueba de no tener line 1")
	public String getLine1() {
		return line1;
	}

	/**
	 * Returns the address line 2
	 * @return the line2
	 */

	public String getLine2() {
		return line2;
	}

	/**
	 * Returns the address line 3
	 * @return the line3
	 */

	public String getLine3() {
		return line3;
	}

	/**
	 * Returns the city name 
	 * @return the city
	 */

	public String getCity() {
		return city;
	}

	/**
	 * Returns the state name
	 * @return the state
	 */

	public String getState() {
		return state;
	}

	/**
	 * Returns the ISO country code
	 * @return the country
	 */

	public String getCountry() {
		return country;
	}

	/**
	 * Returns the postal code
	 * @return the postalCode
	 */

	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Returns the phone number
	 * @return the phone
	 */
	@Size(max = 255)
	@XmlElement(nillable=false)
	public String getPhone() {
		return phone;
	}

	/**
	 * Set the address line 1
	 * @param line1 the line1 to set
	 */
	public void setLine1(String line1) {
		this.line1 = line1;
	}

	/**
	 * Set the address line 2
	 * @param line2 the line2 to set
	 */
	public void setLine2(String line2) {
		this.line2 = line2;
	}

	/**
	 * Set the address line 3
	 * @param line3 the line3 to set
	 */
	public void setLine3(String line3) {
		this.line3 = line3;
	}

	/**
	 * Set the city name 
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Set the state name
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Set the ISO country name
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Set the postal code
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * Set the phone number
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
