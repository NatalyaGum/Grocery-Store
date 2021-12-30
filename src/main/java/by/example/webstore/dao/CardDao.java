package by.example.webstore.dao;

import by.example.webstore.entity.Card;
import by.example.webstore.exception.DaoException;

import java.math.BigDecimal;
import java.util.Optional;

public interface CardDao extends BaseDao<Card> {
    Optional<Card> findByCardNumber(String cardNumber) throws DaoException ;

    boolean updateBankCardAmount(long id, BigDecimal decimal) throws DaoException;

    boolean addBankCard (Card card) throws DaoException ;
}
