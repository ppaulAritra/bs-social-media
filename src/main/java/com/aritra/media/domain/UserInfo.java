package com.aritra.media.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
public class UserInfo extends BaseEntity {

    private String address;

    private String phone;

    private String email;

    private String postCode;

    private String country;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

  
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

	@Override
	public String toString() {
		return "UserInfo [address=" + address + ", phone=" + phone + ", email=" + email + ", postCode=" + postCode
				+ ", country=" + country + "]";
	}

   
}
