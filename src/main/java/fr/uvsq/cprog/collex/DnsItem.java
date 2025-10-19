package fr.uvsq.cprog.collex;

import java.util.Objects;

public class DnsItem {

    private  AdresseIP adresseIPV4;
    private  NomMachine nom;

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
     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DnsItem)) return false;
        DnsItem other = (DnsItem) o;
        return adresseIPV4.equals(other.adresseIPV4) && nom.equals(other.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adresseIPV4, nom);
    }
}
