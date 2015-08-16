package br.com.vinniccius.masteringextjs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;


/**
 * The persistent class for the store database table.
 * 
 */
@Entity
@Table(name="store")
public class Store implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="store_id")
	private byte storeId;

	@Column(name="last_update", updatable=false, insertable=false)
	private Timestamp lastUpdate;

	//bi-directional many-to-one association to Customer
	@OneToMany(mappedBy="store")
	private Set<Customer> customers;

	//bi-directional many-to-one association to Inventory
	@OneToMany(mappedBy="store")
	private Set<Inventory> inventories;

	//bi-directional many-to-one association to Staff
	@OneToMany(mappedBy="store")
	private Set<Staff> staffs;

	//bi-directional many-to-one association to Address
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="address_id")
	private Address address;

	//bi-directional many-to-one association to Staff
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="manager_staff_id")
	private Staff staff;

    public Store() {
    }

	public byte getStoreId() {
		return this.storeId;
	}

	public void setStoreId(byte storeId) {
		this.storeId = storeId;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Set<Customer> getCustomers() {
		return this.customers;
	}

	public void setCustomers(Set<Customer> customers) {
		this.customers = customers;
	}
	
	public Set<Inventory> getInventories() {
		return this.inventories;
	}

	public void setInventories(Set<Inventory> inventories) {
		this.inventories = inventories;
	}
	
	public Set<Staff> getStaffs() {
		return this.staffs;
	}

	public void setStaffs(Set<Staff> staffs) {
		this.staffs = staffs;
	}
	
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
}