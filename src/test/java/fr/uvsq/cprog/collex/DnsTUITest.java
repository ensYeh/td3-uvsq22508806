package fr.uvsq.cprog.collex;

import org.junit.*;
import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.IOException;

public class DnsTUITest {

    private Dns dns;

    @Before
    public void setup() throws IOException {
        dns = new Dns("dns_test");
    }

    private DnsTUI createTuiWithInput(String input) {
        InputStream in = new ByteArrayInputStream((input + "\n").getBytes());
        DnsTUI tui = new DnsTUI(dns);
        try {
            java.lang.reflect.Field field = DnsTUI.class.getDeclaredField("scanner");
            field.setAccessible(true);
            field.set(tui, new java.util.Scanner(in));
        } catch (Exception e) {
            fail("Impossible de remplacer le scanner : " + e.getMessage());
        }
        return tui;
    }

    @Test
    public void testQuitCommande() {
        DnsTUI tui = createTuiWithInput("quit");
        Commande cmd = tui.nextCommande();
        assertNotNull(cmd);
        assertTrue(cmd instanceof Quitter);
    }

    @Test
    public void testRechercheNomCommande() {
        DnsTUI tui = createTuiWithInput("192.168.0.105");
        Commande cmd = tui.nextCommande();
        assertNotNull(cmd);
        assertTrue(cmd instanceof RechercheNom);
    }

    @Test
    public void testRechercheIPCommande() {
        DnsTUI tui = createTuiWithInput("serveur105.uvsq.fr");
        Commande cmd = tui.nextCommande();
        assertNotNull(cmd);
        assertTrue(cmd instanceof RechercheIP);
    }

    @Test
    public void testListeDomaineSimple() {
        DnsTUI tui = createTuiWithInput("ls uvsq.fr");
        Commande cmd = tui.nextCommande();
        assertNotNull(cmd);
        assertTrue(cmd instanceof ListeDomaine);
    }

    @Test
    public void testListeDomaineAvecOption() {
        DnsTUI tui = createTuiWithInput("ls -a uvsq.fr");
        Commande cmd = tui.nextCommande();
        assertNotNull(cmd);
        assertTrue(cmd instanceof ListeDomaine);
    }

    @Test
    public void testAjouterItemCommande() {
        DnsTUI tui = createTuiWithInput("add 192.168.0.105 serveur105.uvsq.fr");
        Commande cmd = tui.nextCommande();
        assertNotNull(cmd);
        assertTrue(cmd instanceof AjouterItem);
    }

    @Test
    public void testCommandeInvalide() {
        DnsTUI tui = createTuiWithInput("commandeInvalide");
        Commande cmd = tui.nextCommande();
        assertNull(cmd);
    }

    @Test
    public void testLigneVide() {
        DnsTUI tui = createTuiWithInput("");
        Commande cmd = tui.nextCommande();
        assertNull(cmd);
    }
}