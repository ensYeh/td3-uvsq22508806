package fr.uvsq.cprog.collex;

public class DnsItem {

    private final AdresseIP adresseIPV4;
    private final NomMachine nom;

    public DnsItem(AdresseIP ip, NomMachine n) {
        this.adresseIPV4 = ip;
        this.nom = n;
    }

    public AdresseIP getIp() {
        return adresseIPV4;
    }

    public NomMachine getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return adresseIPV4 + " " + nom;
    }
}
