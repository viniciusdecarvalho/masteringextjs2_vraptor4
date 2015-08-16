package br.com.vinniccius.masteringextjs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@Table(name="address")
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="address_id")
	private int addressId;

	private String address;

	private String address2;

	private String district;

	@Column(name="last_update", updatable=false, insertable=false)
	private Timestamp lastUpdate;

	private String phone;

	@Column(name="postal_code")
	private String postalCode;

	//bi-directional many-to-one association to City
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="city_id")	
	private City city;

	//bi-directional many-to-one association to Customer
	@OneToMany(mappedBy="address")
	private Set<Customer> customers;

	//bi-directional many-to-one association to Staff
	@OneToMany(mappedBy="address")
	private Set<Staff> staffs;

	//bi-directional many-to-one association to Store
	@OneToMany(mappedBy="address")
	private Set<Store> stores;

    public Address() {
    }

	public int getAddressId() {
		return this.addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress2() {
		return this.address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getDistrict() {
		return this.district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostalCode() {
		return this.postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public City getCity() {
		return this.city;
	}

	public void setCity(City city) {
		this.city = city;
	}
	
	public Set<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}
	
	public Set<Staff> getStaffs() {
		return this.staffs;
	}

	public void setStaffs(Set<Staff> staffs) {
		this.staffs = staffs;
	}
	
	public Set<Store> getStores() {
		return this.stores;
	}

	public void setStores(Set<Store> stores) {
		this.stores = stores;
	}
	
}