package by.example.webstore.dao;

public class ColumnName {
    /* users table */
    public static final String USER_ID = "id_user";
    public static final String USER_NAME = "name";
    public static final String USER_SURNAME = "surname";
    public static final String USER_EMAIL = "email";
    public static final String USER_PASSWORD = "password";
    public static final String USER_PHONE_NUMBER = "phone_number";
    public static final String USER_ROLE = "role";
    public static final String USER_STATUS = "status";
    public static final String USER_BLOCKED = "blocked";
    /* address table */
    private static final String ADDRESS_ID = "addresses.id_address";
    private static final String ADDRESS_STREET = "addresses.street";
    private static final String ADDRESS_BUILDING_NUMBER = "addresses.build_number";
    private static final String ADDRESS_APARTMENT = "addresses.apartment";
    private static final String ADDRESS_COMMENT = "addresses.comment";
    /* products table */
    public static final String PRODUCTS_ID = "products.id_product";
    public static final String PRODUCTS_TITLE = "products.title";
    public static final String PRODUCTS_COUNTRY = "products.country";
    public static final String PRODUCTS_manufacture = "products.manufacture";
    public static final String PRODUCTS_DESCRIPTION = "products.description";
    public static final String PRODUCTS_PRICE = "products.price";
    public static final String PRODUCTS_IMAGE = "products.picture_path";
    public static final String PRODUCTS_TYPE_ID = "products.product_type_id";
    public static final String PRODUCTS_QUANTITY = "products.quantity";
    public static final String PRODUCTS_RATING = "products.rating";
    /* product_types table */
    public static final String PRODUCT_ID = "product_type.id_product_type";
    public static final String PRODUCT_TYPE = "product_type.type";
    /* orders table */
    public static final String ORDER_ID = "orders.id_order";
    public static final String ORDER_COST = "orders.cost";
    public static final String ORDER_ = "orders.cost";
    public static final String ORDER_STATUS = "orders.order_status";
    public static final String ORDER_DATE = "orders.date";
    public static final String ORDER_USER_ID = "orders.users_user_id";
    public static final String ORDER_ADDRESS_ID ="orders.id_address";
    /* purchases table */
    public static final String PURCHASES_ID_ORDER = "purchases.id_order";
    public static final String PURCHASES_ID_PRODUCT = "purchases.id_product";
    public static final String PURCHASES_QUANTITY_PRODUCT = "purchases.quantity_of_product";
    /* payment cards table */
    public static final String CARD_ID = "payment_cards.id_card";
    public static final String CARD_USER_ID = "payment_cards.users_user_id";
    public static final String CARD_NUMBER = "payment_cards.card_number";
    public static final String CARD_EXPIRATION_DATE = "payment_cards.expiration_date";
    public static final String CARD_BALANCE = "payment_cards.balance";

}
