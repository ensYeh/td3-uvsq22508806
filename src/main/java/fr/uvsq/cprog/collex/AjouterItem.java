package fr.uvsq.cprog.collex;

import java.io.IOException;

public class AjouterItem implements Commande {
    private Dns dns;
    private AdresseIP AdresseIpv4;
    private NomMachine nom;

    public AjouterItem(Dns d, AdresseIP ip, NomMachine n) {
        this.dns = d;
        this.AdresseIpv4 = ip;
        this.nom = n;
    }

    @Override
    public void execute() {
        try {
            dns.addItem(AdresseIpv4, nom);
        } catch (IOException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}