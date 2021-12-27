package by.example.webstore.entity;

import java.math.BigDecimal;

public class Product extends AbstractEntity {
    private long productId;
    private String name;
    private String country;
    private String manufacture;
    private String description;
    private BigDecimal price;
    private String picturePath;
    private long productType;
    private int raiting;

    public Product() {
    }
    public Product(long productId, String name, String country, String manufacture, String description,
                   BigDecimal price, String picturePath, long productType, int raiting) {
        this.productId = productId;
        this.name = name;
        this.country = country;
        this.manufacture = manufacture;
        this.description = description;
        this.price = price;
        this.picturePath = picturePath;
        this.productType = productType;
        this.raiting = raiting;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
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

    public String getManufacture() {
        return manufacture;
    }

    public void setManufacture(String manufacture) {
        this.manufacture = manufacture;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public long getProductType() {
        return productType;
    }

    public void setProductType(long productType) {
        this.productType = productType;
    }

    public int getRaiting() {
        return raiting;
    }

    public void setRaiting(int raiting) {
        this.raiting = raiting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return productId == product.productId && productType == product.productType &&
                (name != null ? name.equals(product.name) : product.name == null) &&
                (country != null ? country.equals(product.country) : product.country == null) &&
                (manufacture != null ? manufacture.equals(product.manufacture) : product.manufacture == null) &&
                (description != null ? description.equals(product.description) : product.description == null) &&
                (price != null ? price.equals(product.price) : product.price == null) &&
                (picturePath != null ? picturePath.equals(product.picturePath) : product.picturePath == null);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Product{ ");
        builder.append("productId=").append(productId);
        builder.append(", name=").append(name);
        builder.append(", country='").append(country);
        builder.append(", manufacture=").append(manufacture);
        builder.append(", price=").append(price);
        builder.append(", productType=").append(productType);
        builder.append(", picturePath=").append(picturePath);
        builder.append(", raiting=").append(raiting);
        builder.append(", description=").append(description);
        builder.append("}");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        int first = 31;
        int result = 1;
        result = result * first + (productId != 0 ? (int) productId : 0);
        result = result * first + (name != null ? name.hashCode() : 0);
        result = result * first + (country != null ? country.hashCode() : 0);
        result = result * first + (manufacture != null ? manufacture.hashCode() : 0);
        result = result * first + (description != null ? description.hashCode() : 0);
        result = result * first + (price != null ? price.hashCode() : 0);
        result = result * first + (picturePath != null ? picturePath.hashCode() : 0);
        result = result * first + (productType != 0 ? (int) productType : 0);
        return result;

    }

}
