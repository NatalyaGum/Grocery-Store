package by.example.webstore.dao;

import by.example.webstore.entity.Address;
import by.example.webstore.exception.ConnectionPoolException;
import by.example.webstore.exception.DaoException;

import java.util.List;

public interface AddressDao extends BaseDao<Address>{
    List<Address> findByStreetName(String streetName) throws ConnectionPoolException, DaoException;

    boolean updateAddressDeliveryStreetName(long id, String streetName) throws DaoException, ConnectionPoolException;

    boolean updateAddressDeliveryHouseNumber(long id, String houseNumber) throws ConnectionPoolException, DaoException;

    boolean updateAddressDeliveryBuildingNumber(long id, int buildingNumber) throws ConnectionPoolException, DaoException;

    boolean updateAddressDeliveryFlatNumber(long id, int flatNumber) throws ConnectionPoolException, DaoException;
}

