package fr.uvsq.cprog.collex;

public class RechercheIP implements Commande {
    private Dns dns;
    private NomMachine nom;

    public RechercheIP(Dns d, NomMachine nom) {
        this.dns = d;
        this.nom = nom;
    }

    @Override
    public void execute() {
        DnsItem item = dns.getItem(nom);
        if (item != null) {
            System.out.println(item.getIp());
        } else {
            System.out.println("Nom de machine non trouvé !");
        }
    }
}