package fr.uvsq.cprog.collex;

import java.io.IOException;

public class DnsApp {

    private Dns dns;
    private DnsTUI tui;

    public DnsApp(String nomDns) throws IOException {
        this.dns = new Dns(nomDns);
        this.tui = new DnsTUI(dns);
    }

    public void run() {
        while (true) {
            Commande cmd = tui.nextCommande(); 
            if (cmd != null) {
                cmd.execute();
            }
        }
    }

    public static void main(String[] args) {
        try {
            DnsApp app = new DnsApp("dns1"); 
            app.run();
        } catch (IOException e) {
            System.out.println("Erreur lors du démarrage de l'application : " + e.getMessage());
        }
    }
}