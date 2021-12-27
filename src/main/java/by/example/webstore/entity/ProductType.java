package by.example.webstore.entity;



public class ProductType extends AbstractEntity{
    private long productTypeId;
    private String productTypeName;

    public long getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(long productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductType that = (ProductType) o;
        return productTypeId == that.productTypeId &&
                (productTypeName != null ? productTypeName.equals(that.productTypeName) : that.productTypeName == null);
    }

    @Override
    public int hashCode() {
        int first = 31;
        int result = 1;
        result = result * first + (productTypeId != 0 ? (int) productTypeId : 0);
        result = result * first + (productTypeName != null ? productTypeName.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ProductType{ ");
        builder.append("productTypeId=").append(productTypeId);
        builder.append(", productTypeName=").append(productTypeName);
        return builder.toString();
    }
}
