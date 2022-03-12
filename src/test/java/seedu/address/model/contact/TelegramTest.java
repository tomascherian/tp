package seedu.address.model.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Telegram(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "#";
        assertThrows(IllegalArgumentException.class, () -> new Telegram(invalidPhone));
    }

    @Test
    public void isValidTelegram() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Telegram.isValidId(null));

        // invalid phone numbers
        assertFalse(Telegram.isValidId("%")); // empty string
        assertFalse(Telegram.isValidId("*")); // empty string
        assertFalse(Telegram.isValidId("#")); // Invalid arguments
        assertFalse(Telegram.isValidId("@#")); // Invalid arguments
        assertFalse(Telegram.isValidId("@#$*")); // Invalid arguments

        // Valid telegram ids
        assertTrue(Telegram.isValidId("rA1_"));
        assertTrue(Telegram.isValidId("kP1_"));
        assertTrue(Telegram.isValidId("kD2_"));
    }
}
