package by.edu.webstore.dao;

/**
 * {@code ColumnName} class represent container for column name in database
 * The class does not contain functionality, only constants.
 */
public final class ColumnName {
    /* users table */
    public static final String USER_ID = "id_user";
    public static final String USER_NAME = "name";
    public static final String USER_SURNAME = "surname";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_PHONE_NUMBER = "phone";
    public static final String USER_ROLE = "role";
    public static final String USER_STATUS = "status";
    /* address table */
    public static final String ADDRESS_ID = "addresses.id_address";
    public static final String ADDRESS_STREET = "addresses.street";
    public static final String ADDRESS_BUILDING_NUMBER = "addresses.building";
    public static final String ADDRESS_APARTMENT = "addresses.apartment";
    public static final String ADDRESS_COMMENT = "addresses.comment";
    /* products table */
    public static final String PRODUCTS_ID = "id_product";
    public static final String PRODUCTS_TITLE = "title";
    public static final String PRODUCTS_MANUFACTURE = "manufacture";
    public static final String PRODUCTS_DESCRIPTION = "description";
    public static final String PRODUCTS_PRICE = "price";
    public static final String PRODUCTS_IMAGE = "picture";
    public static final String PRODUCTS_ACTIVE = "active";
    /* product_types table */
    public static final String ID_PRODUCT_TYPE = "product_type.id_product_type";
    public static final String PRODUCT_TYPE = "product_type.product_type";
    /* orders table */
    public static final String ORDER_ID = "orders.id_order";
    public static final String ORDER_COST = "orders.cost";
    public static final String ORDER_STATUS = "orders.order_status";
    public static final String ORDER_METHOD = "orders.payment_method";
    public static final String ORDER_DATE = "orders.date";
    public static final String ORDER_USER_ID = "orders.users_user_id";
    public static final String ORDER_ADDRESS_ID = "delivery_address_id";
    /* purchases table */
    public static final String PURCHASES_ID_PRODUCT = "products_id_product";
    public static final String PURCHASES_QUANTITY = "purchases.quantity";

}
