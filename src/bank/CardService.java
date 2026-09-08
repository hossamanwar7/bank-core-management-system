package bank;

public class CardService {

    private Bank bank;

    // =========================
    // Constructor
    // =========================

    public CardService(Bank bank) {

        if (bank == null) {
            throw new IllegalArgumentException(
                    "Bank.Bank cannot be null."
            );
        }

        this.bank = bank;
    }

    // =========================
    // Add Bank.Card
    // =========================

    public void addCard(Card card) {

        if (card == null) {
            throw new IllegalArgumentException(
                    "Bank.Card cannot be null."
            );
        }

        // Bank.Card number must be unique
        if (searchCardByNumber(card.getCardNumber()) != null) {
            throw new IllegalArgumentException(
                    "Bank.Card with this number already exists."
            );
        }

        bank.addCard(card);

        System.out.println(
                "Bank.Card added successfully."
        );
    }

    // =========================
    // Search Bank.Card By Number
    // =========================

    public Card searchCardByNumber(String cardNumber) {

        if (cardNumber == null ||
                cardNumber.trim().isEmpty()) {

            return null;
        }

        for (Card card : bank.getCards()) {

            if (card.getCardNumber()
                    .equals(cardNumber.trim())) {

                return card;
            }
        }

        return null;
    }

    // =========================
    // Find Bank.Card By Number
    // =========================

    public Card findCardByNumber(String cardNumber) {

        Card card = searchCardByNumber(cardNumber);

        if (card == null) {

            System.out.println(
                    "Bank.Card not found."
            );

            return null;
        }

        System.out.println(
                "Bank.Card found."
        );

        return card;
    }

    // =========================
    // Remove Bank.Card
    // =========================

    public void removeCard(String cardNumber) {

        Card card = searchCardByNumber(cardNumber);

        if (card == null) {
            throw new IllegalArgumentException(
                    "Bank.Card not found."
            );
        }

        bank.getCards().remove(card);

        System.out.println(
                "Bank.Card removed successfully."
        );
    }

    // =========================
    // Update Bank.Card
    // =========================

    public void updateCard(
            String cardNumber,
            CardType cardType,
            String expiryDate,
            String cvv) {

        Card card = searchCardByNumber(cardNumber);

        if (card == null) {
            throw new IllegalArgumentException(
                    "Bank.Card not found."
            );
        }

        card.setCardType(cardType);
        card.setExpiryDate(expiryDate);
        card.setCvv(cvv);

        System.out.println(
                "Bank.Card updated successfully."
        );
    }

    // =========================
    // View All Cards
    // =========================

    public void viewCards() {

        if (bank.getCards().isEmpty()) {

            System.out.println(
                    "No cards available."
            );

            return;
        }

        for (Card card : bank.getCards()) {
            System.out.println(card);
        }
    }

    // =========================
    // View Cards By Bank.Account
    // =========================

    public void viewCardsByAccount(
            String accountNumber) {

        if (accountNumber == null ||
                accountNumber.trim().isEmpty()) {

            throw new IllegalArgumentException(
                    "Bank.Account number cannot be empty."
            );
        }

        boolean found = false;

        for (Card card : bank.getCards()) {

            if (card.getAccount()
                    .getAccountNumber()
                    .equals(accountNumber.trim())) {

                System.out.println(card);

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No cards found for this account."
            );
        }
    }

    // =========================
    // Find Bank.Card By Bank.Account
    // =========================

    public Card findCardByAccount(
            String accountNumber) {

        if (accountNumber == null ||
                accountNumber.trim().isEmpty()) {

            return null;
        }

        for (Card card : bank.getCards()) {

            if (card.getAccount()
                    .getAccountNumber()
                    .equals(accountNumber.trim())) {

                return card;
            }
        }

        return null;
    }

    // =========================
    // Get Cards Count
    // =========================

    public int getCardsCount() {

        return bank.getCards().size();
    }
}

