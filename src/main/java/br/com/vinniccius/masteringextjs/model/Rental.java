package br.com.vinniccius.masteringextjs.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the rental database table.
 * 
 */
@Entity
@Table(name="rental")
public class Rental implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="rental_id")
	private int rentalId;

	@Column(name="last_update", updatable=false, insertable=false)
	private Timestamp lastUpdate;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="rental_date")
	private Date rentalDate;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="return_date")
	private Date returnDate;

	//bi-directional many-to-one association to Payment
	@OneToMany(mappedBy="rental")
	private Set<Payment> payments;

	//bi-directional many-to-one association to Customer
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="customer_id")
	private Customer customer;

	//bi-directional many-to-one association to Inventory
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="inventory_id")
	private Inventory inventory;

	//bi-directional many-to-one association to Staff
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="staff_id")
	private Staff staff;

    public Rental() {
    }

	public int getRentalId() {
		return this.rentalId;
	}

	public void setRentalId(int rentalId) {
		this.rentalId = rentalId;
	}

	public Timestamp getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Date getRentalDate() {
		return this.rentalDate;
	}

	public void setRentalDate(Date rentalDate) {
		this.rentalDate = rentalDate;
	}

	public Date getReturnDate() {
		return this.returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public Set<Payment> getPayments() {
		return this.payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public Inventory getInventory() {
		return this.inventory;
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public Staff getStaff() {
		return this.staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}
	
}