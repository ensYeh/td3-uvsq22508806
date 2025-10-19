package fr.uvsq.cprog.collex;

import org.junit.Test;
import static org.junit.Assert.*;

public class DnsItemTest {

    @Test
    public void testGetters() {
        AdresseIP ip = new AdresseIP("10.0.0.1");
        NomMachine nom = new NomMachine("serveur1", "example.com");
        DnsItem item = new DnsItem(ip, nom);

        assertEquals(ip, item.getIp());
        assertEquals(nom, item.getNom());
    }

    @Test
    public void testToString() {
        AdresseIP ip = new AdresseIP("10.0.0.1");
        NomMachine nom = new NomMachine("serveur1", "example.com");
        DnsItem item = new DnsItem(ip, nom);

        String expected = "10.0.0.1 serveur1.example.com";
        assertEquals(expected, item.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        AdresseIP ip1 = new AdresseIP("10.0.0.1");
        NomMachine nom1 = new NomMachine("serveur1", "example.com");
        DnsItem item1 = new DnsItem(ip1, nom1);

        AdresseIP ip2 = new AdresseIP("10.0.0.1");
        NomMachine nom2 = new NomMachine("serveur1", "example.com");
        DnsItem item2 = new DnsItem(ip2, nom2);

        AdresseIP ip3 = new AdresseIP("10.0.0.2");
        NomMachine nom3 = new NomMachine("serveur2", "example.com");
        DnsItem item3 = new DnsItem(ip3, nom3);

        assertTrue(item1.equals(item2));
        assertFalse(item1.equals(item3));
        Object o = "une chaîne";
        assertFalse(item1.equals(o)); 

        assertEquals(item1.hashCode(), item2.hashCode());
        assertNotEquals(item1.hashCode(), item3.hashCode());
    }
}
