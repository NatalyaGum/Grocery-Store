package by.edu.webstore.dao;

import by.edu.webstore.entity.Address;
import by.edu.webstore.exception.DaoException;

import java.util.List;
import java.util.Optional;


public interface AddressDao extends BaseDao<Address> {

    Optional<Address> findAddressById(long addressId) throws DaoException;

    List<Address> findUserAddresses(long userId) throws DaoException;

    long insertNewAddress(Address address, long user_id) throws DaoException;

}

