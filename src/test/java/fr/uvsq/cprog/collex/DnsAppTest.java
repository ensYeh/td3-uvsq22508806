package fr.uvsq.cprog.collex;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class DnsAppTest {

    private DnsApp app;

    @Before
    public void setUp() throws IOException {
        
        app = new DnsApp("dns_test");
    }

    @Test
    public void testConstructor() {
        assertNotNull("Dns doit être initialisé", app);
    }

    @Test
    public void testDnsTUIInitialization() throws IOException {

        try {
            DnsApp app2 = new DnsApp("dns_test");
            assertNotNull(app2);
        } catch (IOException e) {
            fail("Le constructeur ne devrait pas lever d'exception : " + e.getMessage());
        }
    }
}