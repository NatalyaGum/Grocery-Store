package by.edu.webstore.dao.impl;

import by.edu.webstore.entity.Address;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.edu.webstore.dao.ColumnName.*;

 class AddressCreator {
    private static AddressCreator instance;

    public static AddressCreator getInstance() {
        if (instance == null) {
            instance = new AddressCreator();
        }
        return instance;
    }

      Address create(ResultSet resultSet) throws SQLException {
        Address address = new Address();
        address.setAddressId(resultSet.getLong(ADDRESS_ID));
        address.setStreetName(resultSet.getString(ADDRESS_STREET));
        address.setHouseNumber(resultSet.getString(ADDRESS_BUILDING_NUMBER));
        address.setApartmentNumber(resultSet.getInt(ADDRESS_APARTMENT));
        address.setComment(resultSet.getString(ADDRESS_COMMENT));
        return address;
    }
}

