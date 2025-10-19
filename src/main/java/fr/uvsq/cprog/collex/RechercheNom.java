package fr.uvsq.cprog.collex;

public class RechercheNom implements Commande {
    private Dns dns;
    private AdresseIP adresseIPV4;

    public RechercheNom(Dns dns, AdresseIP ip) {
        this.dns = dns;
        this.adresseIPV4 = ip;
    }

    @Override
    public void execute() {
        DnsItem item = dns.getItem(adresseIPV4);
        if (item != null) {
            System.out.println(item.getNom());
        } else {
            System.out.println("Adresse IP non trouvée !");
        }
    }
}