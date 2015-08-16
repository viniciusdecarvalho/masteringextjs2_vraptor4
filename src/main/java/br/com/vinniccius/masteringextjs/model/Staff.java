package br.com.vinniccius.masteringextjs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the staff database table.
 * 
 */
@Entity
@Table(name="staff")
public class Staff implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="staff_id")
	private byte staffId;

	private byte active;

	private String email;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	@Column(name="last_update", updatable=false, insertable=false)
	private Timestamp lastUpdate;

	private String password;

    @Lob()
	private byte[] picture;

	private String username;

	//bi-directional many-to-one association to Payment
	@OneToMany(mappedBy="staff")
	private Set<Payment> payments;

	//bi-directional many-to-one association to Rental
	@OneToMany(mappedBy="staff")
	private Set<Rental> rentals;

	//bi-directional many-to-one association to Address
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="address_id")
	private Address address;

	//bi-directional many-to-one association to Store
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="store_id")
	private Store store;

	//bi-directional many-to-one association to Store
	@OneToMany(mappedBy="staff")
	private Set<Store> stores;

    public Staff() {
    }

	public byte getStaffId() {
		return this.staffId;
	}

	public void setStaffId(byte staffId) {
		this.staffId = staffId;
	}

	public byte getActive() {
		return this.active;
	}

	public void setActive(byte active) {
		this.active = active;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getPicture() {
		return this.picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}
	
	public Set<Rental> getRentals() {
		return this.rentals;
	}

	public void setRentals(Set<Rental> rentals) {
		this.rentals = rentals;
	}
	
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Store getStore() {
		return this.store;
	}

	public void setStore(Store store) {
		this.store = store;
	}
	
	public Set<Store> getStores() {
		return this.stores;
	}

	public void setStores(Set<Store> stores) {
		this.stores = stores;
	}
	
}