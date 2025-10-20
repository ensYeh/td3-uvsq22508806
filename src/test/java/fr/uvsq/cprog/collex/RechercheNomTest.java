package fr.uvsq.cprog.collex;

import org.junit.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.Properties;

import static org.junit.Assert.*;

public class RechercheNomTest {

    private static Path dataDir;
    private static Path fichierDns;
    private Dns dns;

    @BeforeClass
    public static void setupClass() throws IOException {
        dataDir = Paths.get("data");
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

    @Before
    public void setup() throws IOException {
        Files.write(fichierDns, new byte[0], StandardOpenOption.TRUNCATE_EXISTING); 
        dns = new Dns("dns_test");

        
        dns.addItem(new AdresseIP("192.168.1.190"), new NomMachine("serveur190", "uvsq.fr"));
    }

    @Test
    public void testExecuteIpExistante() {
        RechercheNom cmd = new RechercheNom(dns, new AdresseIP("192.168.1.190"));

        
        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        cmd.execute();

        String expected = "serveur190.uvsq.fr" + System.lineSeparator();
        assertEquals(expected, outContent.toString());
    }

    @Test
    public void testExecuteIpNonExistante() {
        RechercheNom cmd = new RechercheNom(dns, new AdresseIP("192.168.1.191"));

        java.io.ByteArrayOutputStream outContent = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(outContent));

        cmd.execute();

        String expected = "Adresse IP non trouvée !" + System.lineSeparator();
        assertEquals(expected, outContent.toString());
    }
}
