package fr.uvsq.cprog.collex;

import java.util.List;

public class ListeDomaine implements Commande {
    private Dns dns;
    private String domaine;
    private boolean ls_a;

    public ListeDomaine(Dns d, String dom, boolean a) {
        this.dns = d;
        this.domaine = dom;
        this.ls_a = a;
    }

    @Override
    public void execute() {
        List<DnsItem> items;

        if (ls_a) {
            items = dns.getItemsSortedByIp(domaine);
        } else {
            items = dns.getItems(domaine);
        }

        for (DnsItem item : items) {
            System.out.println(item.getIp() + " " + item.getNom());
        }
    }
}