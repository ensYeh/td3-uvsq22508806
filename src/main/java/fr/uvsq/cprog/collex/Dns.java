package fr.uvsq.cprog.collex;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class Dns {
    private Map<AdresseIP, DnsItem> parIp = new HashMap<>();
    private Map<NomMachine, DnsItem> parNom = new HashMap<>();
    private Path fichierBase;

    public Dns(String dns) throws IOException {
        Properties props = new Properties();
        try (var input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new IOException("Fichier config.properties introuvable !");
            }
            props.load(input);
        }

        String chemin = props.getProperty(dns);
        if (chemin == null) {
            throw new IOException("Clé '" + dns + "' introuvable dans config.properties");
        }
        Path dataDir = Paths.get("target", "data"); // dossier modifiable
        if (!Files.exists(dataDir)) {
            Files.createDirectories(dataDir);
        }
        this.fichierBase = dataDir.resolve(chemin + ".txt"); // créer un fichier .txt avec le nom du dns
        if (!Files.exists(fichierBase)) {
            System.out.println("Fichier " + fichierBase + " introuvable, création d’un nouveau fichier vide.");
            Files.createFile(fichierBase);
        }
        chargerBase();
    }

    private void chargerBase() throws IOException {
        List<String> lignes = Files.readAllLines(fichierBase);
        for (String ligne : lignes) {
            ligne = ligne.trim();
            if (ligne.isEmpty())
                continue;

            String[] parts = ligne.split("\\s+");
            if (parts.length != 2)
                continue;

            String nomComplet = parts[0];
            String ip = parts[1];

            String[] n = nomComplet.split("\\.", 2);
            if (n.length != 2)
                continue;

            NomMachine nom = new NomMachine(n[0], n[1]);
            AdresseIP adresse = new AdresseIP(ip);
            DnsItem item = new DnsItem(adresse, nom);

            parIp.put(adresse, item);
            parNom.put(nom, item);
        }
    }

    public DnsItem getItem(AdresseIP ip) {
        return parIp.get(ip);
    }

    public DnsItem getItem(NomMachine nom) {
        return parNom.get(nom);
    }

    public List<DnsItem> getItems(String domaine) {
        return parNom.values().stream()
                .filter(i -> i.getNom().getDomaine().equals(domaine))
                .sorted(Comparator.comparing(i -> i.getNom().getNom()))
                .collect(Collectors.toList());
    }

    public List<DnsItem> getItemsSortedByIp(String domaine) {
        return parNom.values().stream()
                .filter(i -> i.getNom().getDomaine().equals(domaine))
                .sorted(Comparator.comparing(i -> i.getIp().getAdresseIPV4()))
                .collect(Collectors.toList());
    }

    public void addItem(AdresseIP ip, NomMachine nom) throws IOException {
        if (parIp.containsKey(ip))
            throw new IllegalArgumentException("ERREUR : L’adresse IP existe déjà !");
        if (parNom.containsKey(nom))
            throw new IllegalArgumentException("ERREUR : Le nom de machine existe déjà !");

        DnsItem item = new DnsItem(ip, nom);
        parIp.put(ip, item);
        parNom.put(nom, item);

        sauvegarder(item); 
    }

    private void sauvegarder(DnsItem item) throws IOException {
        String ligne = item.getNom().NomQualifie() + " " + item.getIp().getAdresseIPV4() + System.lineSeparator();
        Files.write(fichierBase, ligne.getBytes(), java.nio.file.StandardOpenOption.APPEND);
    }
}
