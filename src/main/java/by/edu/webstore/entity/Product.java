package by.edu.webstore.entity;

import java.math.BigDecimal;

/**
 * {@code Product} class represent a room
 *
 * @see AbstractEntity
 */
public class Product extends AbstractEntity {
    private long productId;
    private String title;
    private String manufacture;
    private String description;
    private BigDecimal price;
    private String picture;
    private ProductType productType;
    private boolean active;

    public Product() {
    }

    public Product(String title, String description, BigDecimal price,
                   String manufacture, ProductType productType) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.manufacture = manufacture;
        this.productType = productType;
    }

    public Product(String title, String description, BigDecimal price,
                   String picture, String manufacture, ProductType productType) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.picture = picture;
        this.manufacture = manufacture;
        this.productType = productType;
    }

    public Product(long productId, String title, String manufacture, String description,
                   BigDecimal price, String picture, ProductType productType, boolean active) {
        this.productId = productId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.picture = picture;
        this.manufacture = manufacture;
        this.productType = productType;
        this.active = active;
    }

    public Product(long productId, String title, String manufacture, String description,
                   BigDecimal price, ProductType productType, boolean active) {
        this.productId = productId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.manufacture = manufacture;
        this.productType = productType;
        this.active = active;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product otherProduct = (Product) o;
        return (productId == otherProduct.productId && productType == otherProduct.productType) &&
                (title != null ? title.equals(otherProduct.title) : otherProduct.title == null) &&
                (manufacture != null ? manufacture.equals(otherProduct.manufacture) : otherProduct.manufacture == null) &&
                (description != null ? description.equals(otherProduct.description) : otherProduct.description == null) &&
                (productType != null ? productType.equals(otherProduct.productType) : otherProduct.productType == null) &&
                (price != null ? price.equals(otherProduct.price) : otherProduct.price == null) &&
                (active == otherProduct.active) &&
                (picture != null ? picture.equals(otherProduct.picture) : otherProduct.picture == null);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Product{ ");
        builder.append("productId=").append(productId);
        builder.append(", title=").append(title);
        builder.append(", manufacture=").append(manufacture);
        builder.append(", price=").append(price);
        builder.append(", productType=").append(productType);
        builder.append(", picturePath=").append(picture);
        builder.append(", description=").append(description);
        builder.append(", active=").append(active);
        builder.append("}");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        int first = 31;
        int result = 1;
        result = result * first + (productId != 0 ? (int) productId : 0);
        result = result * first + (title != null ? title.hashCode() : 0);
        result = result * first + (manufacture != null ? manufacture.hashCode() : 0);
        result = result * first + (description != null ? description.hashCode() : 0);
        result = result * first + (price != null ? price.hashCode() : 0);
        result = result * first + (picture != null ? picture.hashCode() : 0);
        result = result * first + (productType.hashCode() != 0 ? productType.hashCode() : 0);
        result = result * first + Boolean.hashCode(active);
        return result;

    }

}
