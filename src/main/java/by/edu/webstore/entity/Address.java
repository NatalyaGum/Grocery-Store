package by.edu.webstore.entity;


public class Address extends AbstractEntity {
    private long addressId;
    private String streetName;
    private String buildingNumber;
    private int apartmentNumber;
    private String comment;

    public Address() {
    }
    public Address(String streetName, String buildingNumber, int apartmentNumber, String comment) {
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
        this.comment = comment;
    }

    public Address(long addressId, String streetName, String buildingNumber, int apartmentNumber, String comment) {
        this.addressId = addressId;
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
        this.comment = comment;
    }

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

    public String getBuildingNumber() {
        return buildingNumber;
    }

    public void setBuildingNumber(String houseNumber) {
        this.buildingNumber = houseNumber;
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
        return addressId == address.addressId &&
                apartmentNumber == address.apartmentNumber &&
                streetName.equals(address.streetName) &&
                buildingNumber.equals(address.buildingNumber);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Г.Минск");
        sb.append(", ул. ").append(streetName);
        sb.append(", д. ").append(buildingNumber);
        sb.append(", кв. ").append(apartmentNumber);
        sb.append(". Примечание: ").append(comment);
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int first = 31;
        int result = 1;
        result = result * first + (int) addressId;
        result = result * first + apartmentNumber;
        result = result * first + (streetName != null ? streetName.hashCode() : 0);
        result = result * first + (buildingNumber != null ? buildingNumber.hashCode() : 0);
        return result;
    }
}
