package Pojo.request.CreateBooking;

import java.util.Objects;

public class Booking {
	 private String firstname;
	    private String lastname;
	    private Integer totalprice;
	    private Boolean depositpaid;
	    private Bookingdates bookingdates;
	    private String additionalneeds;
	    public String getFirstname() {
	        return firstname;
	    }
	    public void setFirstname(String firstname) {
	        this.firstname = firstname;
	    }
	    public String getLastname() {
	        return lastname;
	    }
	    public void setLastname(String lastname) {
	        this.lastname = lastname;
	    }
	    public Integer getTotalprice() {
	        return totalprice;
	    }
	    public void setTotalprice(Integer totalprice) {
	        this.totalprice = totalprice;
	    }
	    public Boolean getDepositpaid() {
	        return depositpaid;
	    }
	    public void setDepositpaid(Boolean depositpaid) {
	        this.depositpaid = depositpaid;
	    }
	    public Bookingdates getBookingdates() {
	        return bookingdates;
	    }
	    public void setBookingdates(Bookingdates bookingdates) {
	        this.bookingdates = bookingdates;
	    }
	    public String getAdditionalneeds() {
	        return additionalneeds;
	    }
	    public void setAdditionalneeds(String additionalneeds) {
	        this.additionalneeds = additionalneeds;
	    }
		@Override
		public int hashCode() {
			return Objects.hash(additionalneeds, bookingdates, depositpaid, firstname, lastname, totalprice);
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Booking other = (Booking) obj;
			return Objects.equals(additionalneeds, other.additionalneeds)
					&& Objects.equals(bookingdates, other.bookingdates)
					&& Objects.equals(depositpaid, other.depositpaid) && Objects.equals(firstname, other.firstname)
					&& Objects.equals(lastname, other.lastname) && Objects.equals(totalprice, other.totalprice);
		}
	    
	}