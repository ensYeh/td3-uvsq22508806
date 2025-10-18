package fr.uvsq.cprog.collex;

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

}