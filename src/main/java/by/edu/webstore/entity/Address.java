package by.edu.webstore.entity;


public class Address extends AbstractEntity {
    private long addressId;
    private String streetName;
    private String houseNumber;
    private int apartmentNumber;
    private String comment;

    public long getAddressId() {
        return addressId;
    }

    public void setAddressId(long addressId) {
        this.addressId = addressId;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return addressId == address.addressId && apartmentNumber == address.apartmentNumber &&
                streetName.equals(address.streetName) && houseNumber.equals(address.houseNumber);
    }

    @Override
    public int hashCode() {
        int first = 31;
        int result = 1;
        result = result * first + (int) addressId;
        result = result * first + apartmentNumber;
        result = result * first + (streetName != null ? streetName.hashCode() : 0);
        result = result * first + (houseNumber != null ? houseNumber.hashCode() : 0);
        return result;
    }
}
