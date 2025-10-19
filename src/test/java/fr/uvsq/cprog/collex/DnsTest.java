package fr.uvsq.cprog.collex;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.Properties;

public class DnsTest {

    private static Path dataDir;
    private static Path fichierDns;

    @BeforeClass
    public static void setupClass() throws IOException {
        
        dataDir = Paths.get("target", "data");
        if (!Files.exists(dataDir)) {
            Files.createDirectories(dataDir);
        }

        fichierDns = dataDir.resolve("dns_test.txt"); 
        if (!Files.exists(fichierDns)) {
            Files.createFile(fichierDns);
        }

        Path config = Paths.get("target", "config.properties");
        Properties props = new Properties();
        props.setProperty("dns_test", "dns_test"); 
        try (var out = Files.newOutputStream(config)) {
            props.store(out, null);
        }
    }

    private Dns dns;

    @Before
    public void setup() throws IOException {
        Files.write(fichierDns, new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
        dns = new Dns("dns_test"); 
    }

    @Test 
    public void testAddAndGetItem() throws IOException {
        AdresseIP ip1 = new AdresseIP("192.168.0.101");
        NomMachine nom1 = new NomMachine("serveur101", "uvsq.fr");

        dns.addItem(ip1, nom1);

        DnsItem itemByIp = dns.getItem(ip1);
        assertNotNull(itemByIp);
        assertEquals(nom1, itemByIp.getNom());

        DnsItem itemByNom = dns.getItem(nom1);
        assertNotNull(itemByNom);
        assertEquals(ip1, itemByNom.getIp());
    }

    @Test
    public void testGetItemsSorted() throws IOException {
        dns.addItem(new AdresseIP("192.168.0.101"), new NomMachine("serveur101", "uvsq.fr"));
        dns.addItem(new AdresseIP("192.168.0.102"), new NomMachine("serveur102", "uvsq.fr"));
        dns.addItem(new AdresseIP("192.168.0.110"), new NomMachine("serveur110", "uvsq.fr"));

        List<DnsItem> itemsByName = dns.getItems("uvsq.fr");
        assertEquals("serveur101", itemsByName.get(0).getNom().getNom());
        assertEquals("serveur102", itemsByName.get(1).getNom().getNom());
        assertEquals("serveur110", itemsByName.get(2).getNom().getNom());

        List<DnsItem> itemsByIp = dns.getItemsSortedByIp("uvsq.fr");
        assertEquals("192.168.0.101", itemsByIp.get(0).getIp().getAdresseIPV4());
        assertEquals("192.168.0.102", itemsByIp.get(1).getIp().getAdresseIPV4());
        assertEquals("192.168.0.110", itemsByIp.get(2).getIp().getAdresseIPV4());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemIpExist() throws IOException {
        AdresseIP ip = new AdresseIP("192.168.0.101");
        NomMachine nom1 = new NomMachine("serveur101", "uvsq.fr");
        NomMachine nom2 = new NomMachine("serveur102", "uvsq.fr");

        dns.addItem(ip, nom1);
        dns.addItem(ip, nom2); 
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddItemNomExist() throws IOException {
        AdresseIP ip1 = new AdresseIP("192.168.0.101");
        AdresseIP ip2 = new AdresseIP("192.168.0.102");
        NomMachine nom = new NomMachine("serveur101", "uvsq.fr");

        dns.addItem(ip1, nom);
        dns.addItem(ip2, nom); 
    }

    @Test
    public void testGetItemNonExistant() {
        AdresseIP ip = new AdresseIP("10.0.0.101");
        NomMachine nom = new NomMachine("inconnu101", "uvsq.fr");

        assertNull(dns.getItem(ip));
        assertNull(dns.getItem(nom));
    }
}
