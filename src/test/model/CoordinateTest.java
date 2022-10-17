package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CoordinateTest {

    @Test
    public void equalTest() {
        Coordinate c1 = new Coordinate(0, 0);
        Coordinate c2 = new Coordinate(0, 0);
        Coordinate c3 = new Coordinate(0, 1);

        assertTrue(c1.equals(c2));
        assertFalse(c1.equals(c3));
    }

}
