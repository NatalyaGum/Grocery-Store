package by.edu.webstore.dao.impl;

import by.edu.webstore.connection.ConnectionPool;
import by.edu.webstore.entity.Address;
import by.edu.webstore.exception.ConnectionPoolException;
import by.edu.webstore.exception.DaoException;
import by.edu.webstore.dao.AddressDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class AddressDaoImpl implements AddressDao {
    static Logger logger = LogManager.getLogger();
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String FIND_ADDRESS_BY_ID = """
            SELECT id_address, street, building, apartment, comment  
            FROM addresses
            WHERE id_address=?""";

    private static final String FIND_ADDRESS_BY_USER_ID = """
            SELECT id_address, street, building, apartment, comment FROM addresses          
            WHERE users_user_id=?
            ORDER BY id_address DESC """;

    private static final String INSERT_NEW_ADDRESS = "INSERT INTO addresses (street, building, apartment, comment) VALUES(?, ?, ?, ?)";

    @Override
    public Optional<Address> findAddressById(long addressId) throws DaoException {
        Optional<Address> addressOptional = Optional.empty();
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ADDRESS_BY_ID)) {
            statement.setLong(1, addressId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Address address = AddressCreator.getInstance().create(resultSet);
                addressOptional = Optional.of(address);
            }
            logger.debug( "findAddressById method was completed successfully." +
                    (addressOptional.isPresent() ? " Address with id " + addressId + " was found"
                            : " Address with id " + addressId + " doesn't exist"));
            return addressOptional;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error( "Impossible to find address by user id from database. Database access error:", e);
            throw new DaoException("Impossible to find address by user id from database. Database access error:", e);
        }
    }

    @Override
    public List<Address> findUserAddresses(long userId) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ADDRESS_BY_USER_ID)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<Address> addresses = new ArrayList<>();
            while (resultSet.next()) {
                Address address = AddressCreator.getInstance().create(resultSet);
                addresses.add(address);
            }
            logger.debug( "findUserAddresses method was completed successfully. " +
                    (!addresses.isEmpty() ? addresses.size() + " addresses were found for user with id " + userId
                            : "User with id " + userId + " doesn't have any addresses"));
            return addresses;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error( "Impossible to find addresses by user id from database. Database access error:", e);
            throw new DaoException("Impossible to find addresses by user id from database. Database access error:", e);
        }
    }

   /* @Override
    public boolean insertUserAddress(long userId, Address address) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NEW_ADDRESS_FOR_USER)) {
            statement.setString(1, address.getStreetName());
            statement.setString(2, address.getHouseNumber());
            statement.setInt(3, address.getApartmentNumber());
            statement.setString(4, address.getComment());
            statement.setLong(5, userId);
            boolean result = statement.executeUpdate() == 1;
            logger.info( "insertUserAddress method was completed successfully."
                    + (result ? "New address was inserted for user with id " + userId : "New address wasn't inserted for user with id " + userId));
            return result;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error( "Impossible to insert new address for user with id " + userId + " into database. Database access error:", e);
            throw new DaoException("Impossible to insert new address for user with id " + userId + " into database. Database access error:", e);
        }
    }*/

    @Override
    public long insertNewAddress(Address address) throws DaoException {
        try (Connection connection = connectionPool.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_NEW_ADDRESS, RETURN_GENERATED_KEYS)) {
            statement.setString(1, address.getStreetName());
            statement.setString(2, address.getBuildingNumber());
            statement.setInt(3, address.getApartmentNumber());
            statement.setString(4, address.getComment());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            long addressId = 0;
            if (resultSet.next()) {
                addressId = resultSet.getLong(1);
            }
            logger.info( addressId != 0 ? "insertAddress method was completed successfully. Address with id " + addressId + " was inserted"
                    : "Address wasn't inserted into database");
            return addressId;
        } catch (SQLException | ConnectionPoolException e) {
            logger.error("Impossible to insert new address into database. Database access error:", e);
            throw new DaoException("Impossible to insert new address into database. Database access error:", e);
        }
    }



    @Override
    public List<Address> findAllEntities() throws DaoException, ConnectionPoolException {
        return null;
    }


}
