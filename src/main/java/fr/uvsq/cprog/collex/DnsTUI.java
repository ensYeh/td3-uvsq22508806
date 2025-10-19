package fr.uvsq.cprog.collex;

import java.util.Scanner;

public class DnsTUI {
    private Dns dns;
    private Scanner scanner;

    public DnsTUI(Dns d) {
        this.dns = d;
        this.scanner = new Scanner(System.in);
    }

    public Commande nextCommande() {
        System.out.print("> ");
        String ligne = scanner.nextLine().trim();

        if (ligne.isEmpty())
            return null;

        String[] parts = ligne.split("\\s+");

        try {
            if (parts.length == 1) {
                if (parts[0].matches("^([0-9]{1,3}\\.){3}[0-9]{1,3}$")) { // Ce regex accepte les adresses de type IPv4
                    AdresseIP ip = new AdresseIP(parts[0]);// même si certains octets dépassent 255. La vérification stricte (0 à 255) est effectuée dans le constructeur de AdresseIP,
                    return new RechercheNom(dns, ip); // qui lancera une IllegalArgumentException si la valeur est invalide.
                                                                    
                }

                else if (parts[0].contains(".")) {
                    String[] nomParts = parts[0].split("\\.", 2);
                    NomMachine nom = new NomMachine(nomParts[0], nomParts[1]);
                    return new RechercheIP(dns, nom);
                }
                else if (parts[0].equals("quit")) {
                return new Quitter();
            }

            } else if (parts[0].equals("ls")) {
                if (parts.length == 2) {
                    return new ListeDomaine(dns, parts[1], false);
                } else if (parts.length == 3 && parts[1].equals("-a")) {
                    return new ListeDomaine(dns, parts[2], true);
                }

            } else if (parts[0].equals("add") && parts.length == 3) {
                AdresseIP ip = new AdresseIP(parts[1]);
                String[] nomParts = parts[2].split("\\.", 2);
                NomMachine nom = new NomMachine(nomParts[0], nomParts[1]);
                return new AjouterItem(dns, ip, nom);
            } 

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }

        System.out.println("Commande invalide !");
        return null;
    }

    public void affiche(String message) {
        System.out.println(message);
    }


}