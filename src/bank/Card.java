package bank;

public class Card {

    private String cardNumber;
    private CardType cardType;
    private String expiryDate;
    private String cvv;
    private Account account;

    public Card(String cardNumber,
                CardType cardType,
                String expiryDate,
                String cvv,
                Account account) {

        setCardNumber(cardNumber);
        setCardType(cardType);
        setExpiryDate(expiryDate);
        setCvv(cvv);
        setAccount(account);
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public CardType getCardType() {
        return cardType;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public String getCvv() {
        return cvv;
    }

    public Account getAccount() {
        return account;
    }

    public void setCardNumber(String cardNumber) {

        if (cardNumber == null || !cardNumber.matches("\\d{16}")) {
            throw new IllegalArgumentException(
                    "Bank.Card number must contain exactly 16 digits."
            );
        }

        this.cardNumber = cardNumber;
    }

    public void setCardType(CardType cardType) {

        if (cardType == null) {
            throw new IllegalArgumentException(
                    "Bank.Card type cannot be null."
            );
        }

        this.cardType = cardType;
    }

    public void setExpiryDate(String expiryDate) {

        if (expiryDate == null || expiryDate.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Expiry date cannot be empty."
            );
        }

        this.expiryDate = expiryDate.trim();
    }


    public void setCvv(String cvv) {

        if (cvv == null || !cvv.matches("\\d{3}")) {
            throw new IllegalArgumentException(
                    "CVV must contain exactly 3 digits."
            );
        }

        this.cvv = cvv;
    }

    public void setAccount(Account account) {

        if (account == null) {
            throw new IllegalArgumentException(
                    "Bank.Account cannot be null."
            );
        }

        this.account = account;
    }

    @Override
    public String toString() {

        return "===============\n" +
                "\nBank.Card Number : " + cardNumber +
                "\nBank.Account : " + account +
                "\nBank.Card Type : " + cardType +
                "\nExpiry Date : " + expiryDate +
                "\nCVV : ****" +
                "\n===============";
    }
}
