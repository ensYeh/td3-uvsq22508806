package fr.uvsq.cprog.collex;

import java.util.Objects;

public class AdresseIP {
    private String adresseIPV4;

    public AdresseIP(String a) {
        if (a.matches("^((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)$")) {
            this.adresseIPV4 = a;
        } else {
            throw new IllegalArgumentException("Adresse IPv4 invalide : " + a);
        }
    }

    public String getAdresseIPV4() {
        return this.adresseIPV4;
    }

    @Override
    public String toString() {
        return adresseIPV4;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof AdresseIP))
            return false;
        AdresseIP other = (AdresseIP) o;
        return adresseIPV4.equals(other.adresseIPV4);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adresseIPV4);
    }

}