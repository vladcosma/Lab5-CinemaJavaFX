package Domain;

import Domain.Exceptions.CardValidatorException;

import java.util.Calendar;

public class CustomerCardValidator implements IValidator<CustomerCard> {
    /**
     * validates a card
     * @param card to validate
     * @throws CardValidatorException if there are validation errors
     */
    public void validate(CustomerCard card) {
        String errors = "";

        if (card.getCNP().length() != 13) {
            errors += "The CNP must have 13 characters! \n";
        }

        if (card.getDateOfBirth().getYear() < 1900 || card.getDateOfBirth().getYear() > Calendar.getInstance().get(Calendar.YEAR)) {
            errors += "The year of birth must be less than " + Calendar.getInstance().get(Calendar.YEAR) +
                    " and greater than 1900\n";
        }

        if (card.getRegistrationDate().getYear() < 1900 || card.getRegistrationDate().getYear() > Calendar.getInstance().get(Calendar.YEAR)) {
            errors += "The year of registration must be less than " + Calendar.getInstance().get(Calendar.YEAR) +
                    " and greater than 1900\n";
        }

        if (card.getBonusPoints() < 0) {
            errors += "The bonus points must be >= 0 \n";
        }
        boolean palindromeId = PalindromeIdValidator.isPalindrome(card.getId());
        if (!palindromeId) {
            errors += "The id " + card.getId() + " isn't palindrome";
        }

        if (!errors.isEmpty()) {
            throw new CardValidatorException("\n" + errors);
        }
    }
}