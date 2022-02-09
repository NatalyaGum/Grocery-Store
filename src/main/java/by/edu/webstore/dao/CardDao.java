package by.edu.webstore.dao;

import by.edu.webstore.entity.Card;
import by.edu.webstore.exception.DaoException;

import java.math.BigDecimal;
import java.util.Optional;

public interface CardDao extends BaseDao<Card> {
    Optional<Card> findByCardNumber(String cardNumber) throws DaoException;

    boolean updateBankCardAmount(long id, BigDecimal decimal) throws DaoException;

    boolean addBankCard (Card card) throws DaoException ;
}
