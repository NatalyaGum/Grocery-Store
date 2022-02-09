package by.edu.webstore.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Card extends AbstractEntity{
    private long cardId;
    private long userId;
    private String cardNumber;
    private LocalDate expirationDate;
    private BigDecimal balance;

    public long getCardId() {
        return cardId;
    }

    public void setCardId(long cardId) {
        this.cardId = cardId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardId == card.cardId &&
                userId == card.userId &&
                cardNumber.equals(card.cardNumber) &&
                expirationDate.equals(card.expirationDate) &&
                balance.equals(card.balance);
    }

    @Override
    public int hashCode() {
        int first = 31;
        int result = 1;
        result = result * first + (int) cardId;
        result = result * first + (int) userId;
        result = result * first + cardNumber.hashCode();
        result = result * first + expirationDate.hashCode();
        result = result * first +  balance.hashCode();
        return result;

    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Card{");
        sb.append("cardId=").append(cardId);
        sb.append(", userId=").append(userId);
        sb.append(", cardNumber='").append(cardNumber).append('\'');
        sb.append(", expirationDate=").append(expirationDate);
        sb.append(", balance=").append(balance);
        sb.append('}');
        return sb.toString();
    }
}
