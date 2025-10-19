package fr.uvsq.cprog.collex;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

public class AjouterItemTest {

    private Dns dns;

    @Before
    public void setUp() throws IOException {
        
        dns = new Dns("dns_test"); 
    }

    @Test
    public void testExecuteAjouteItemValide() {
        AdresseIP ip = new AdresseIP("192.168.0.1");
        NomMachine nom = new NomMachine("poste", "uvsq.fr");

        AjouterItem cmd = new AjouterItem(dns, ip, nom);
        cmd.execute();

        
        assertEquals(nom, dns.getItem(ip).getNom());
        assertEquals(ip, dns.getItem(nom).getIp());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExecuteAdresseIPExiste() throws IOException {
        AdresseIP ip = new AdresseIP("192.168.0.2");
        NomMachine nom1 = new NomMachine("pc1", "uvsq.fr");
        NomMachine nom2 = new NomMachine("pc2", "uvsq.fr");

        dns.addItem(ip, nom1);

        AjouterItem cmd = new AjouterItem(dns, ip, nom2);
       
        cmd.execute();

    
        assertEquals(nom1, dns.getItem(ip).getNom());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testExecuteNomExiste() throws IOException {
        AdresseIP ip1 = new AdresseIP("192.168.0.3");
        AdresseIP ip2 = new AdresseIP("192.168.0.4");
        NomMachine nom = new NomMachine("serveur", "uvsq.fr");

        dns.addItem(ip1, nom);

        AjouterItem cmd = new AjouterItem(dns, ip2, nom);
        cmd.execute();

        assertEquals(ip1, dns.getItem(nom).getIp());
    }
}