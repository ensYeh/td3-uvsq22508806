package fr.uvsq.cprog.collex;

import static org.junit.Assert.*;
import org.junit.Test;

public class AdresseIPTest {

    @Test
    public void testConstructeurValide() {
        AdresseIP ip = new AdresseIP("192.168.1.1");
        assertEquals("192.168.1.1", ip.getAdresseIPV4());
        assertEquals("192.168.1.1", ip.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructeurInvalide() {
        new AdresseIP("256.256.256.256");
    }

    @Test
    public void testEqualsEtHashCode() {
        AdresseIP ip1 = new AdresseIP("10.0.0.1");
        AdresseIP ip2 = new AdresseIP("10.0.0.1");
        AdresseIP ip3 = new AdresseIP("10.0.0.2");

        assertTrue(ip1.equals(ip1));

        assertTrue(ip1.equals(ip2));

        assertEquals(ip1.hashCode(), ip2.hashCode());

        assertFalse(ip1.equals(ip3));

         Object o = "10.0.0.1";

        assertFalse(ip1.equals(o));

        assertFalse(ip1.equals(null));
    }
}