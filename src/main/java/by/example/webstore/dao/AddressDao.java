package by.example.webstore.dao;

import by.example.webstore.entity.Address;
import by.example.webstore.exception.DaoException;

import java.util.List;
import java.util.Optional;


public interface AddressDao extends BaseDao<Address> {

    Optional<Address> findEntityById(long addressId) throws DaoException;

    List<Address> findUserAddresses(long userId) throws DaoException;

    long insertNewEntity(Address address) throws DaoException;

}

