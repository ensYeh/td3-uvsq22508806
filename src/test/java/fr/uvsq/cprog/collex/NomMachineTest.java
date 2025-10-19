package fr.uvsq.cprog.collex;

import org.junit.Test;
import static org.junit.Assert.*;

public class NomMachineTest {

    @Test
    public void testGettersAndNomQualifie() {
        NomMachine nom = new NomMachine("serveur45", "uvsq.fr");
        assertEquals("serveur45", nom.getNom());
        assertEquals("uvsq.fr", nom.getDomaine());
        assertEquals("serveur45.uvsq.fr", nom.NomQualifie());
    }

    @Test
    public void testToString() {
        NomMachine nom = new NomMachine("serveur2", "uvsq.fr");
        assertEquals("serveur2.uvsq.fr", nom.toString());
    }

    @Test
    public void testEqualsAndHashCode() {
        NomMachine n1 = new NomMachine("serveur45", "uvsq.fr");
        NomMachine n3 = new NomMachine("serveur47", "uvsq.fr");

        assertTrue(n1.equals(n1));
        assertFalse(n1.equals(n3));
        Object o ="une chaîne";
        assertFalse(n1.equals(o));

        assertEquals(n1.hashCode(), n1.hashCode());
        assertNotEquals(n1.hashCode(), n3.hashCode());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNomAvecPoint() {
        new NomMachine("serveur.45", "uvsq.fr");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDomaineInvalide() {
        new NomMachine("serveur45", "-uvsq.fr");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDomaineVide() {
        new NomMachine("serveur45", "uvsq"); 
    }
}