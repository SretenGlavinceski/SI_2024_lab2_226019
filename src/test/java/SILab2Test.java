import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SILab2Test {

    @Test
    void checkEveryBranch () {

        RuntimeException ex;

        // null, 0 -> exception (allItems list can't be null!)

        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, 0));
        assertTrue(ex.getMessage().contains("allItems list can't be null!"));

        // list(new Item("name", null, any, any)), payment = any -> exception (No barcode!)

        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(List.of(new Item("name", null, 0, 0)), 0));
        assertTrue(ex.getMessage().contains("No barcode!"));

        // list(new Item("", "barcode", any, any)), payment = any -> exception (Invalid character in item barcode!)

        ex = assertThrows(RuntimeException.class, () -> SILab2.checkCart(List.of(new Item("", "barcode", 0, 0)), 0));
        assertTrue(ex.getMessage().contains("Invalid character in item barcode!"));

        // list(new Item("name1", "0123", 500, 10)), 4970 -> true

        assertEquals(true, SILab2.checkCart(List.of(new Item("name1", "0123", 500, 10)), 4970));


        // list(new Item("name1", "0123", 500, 0)), 499 -> false
        assertEquals(false, SILab2.checkCart(List.of(new Item("name1", "0123", 500, 0)), 499));
    }

    @Test
    void checkMultipleCondition() {
        // Item constructor = String name, String code, int price, float discount

        // [true:FXX]
        // list(new Item("", "123", 100, 0)), payment = 100
        assertEquals(true, SILab2.checkCart(List.of(new Item("", "123", 100, 0)), 100));

        // [true:TFX]
        // list(new Item("", "123", 301, 0)), payment = 301
        assertEquals(true, SILab2.checkCart(List.of(new Item("", "123", 301, 0)), 301));

        // [true:TTF]
        // list(new Item("", "123", 301, 10)), payment = 3010
        assertEquals(true, SILab2.checkCart(List.of(new Item("", "123", 301, 10)), 3010));

        // [false:TTT]
        // list(new Item("", "0123", 500, 10)), payment = 4969
        assertEquals(false, SILab2.checkCart(List.of(new Item("", "0123", 500, 10)), 4969));
    }
}









