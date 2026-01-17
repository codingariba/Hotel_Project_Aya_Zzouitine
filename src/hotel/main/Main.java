package hotel.main;

import hotel.enums.TypeChambre;
import hotel.model.*;
import hotel.observer.NotificationConsole;
import hotel.utils.PdfGenerator;


import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Création de l'hôtel
        Hotel hotel = new Hotel("Hôtel du Maroc");

        hotel.ajouterChambre(new Chambre(
                101, 1, TypeChambre.SIMPLE, 50.0, 1,
                List.of("TV", "WiFi")
        ));

        hotel.ajouterChambre(new Chambre(
                102, 1, TypeChambre.DOUBLE, 80.0, 2,
                List.of("TV", "WiFi", "Mini-bar")
        ));

        hotel.ajouterChambre(new Chambre(
                201, 2, TypeChambre.SUITE, 150.0, 4,
                List.of("TV", "WiFi", "Mini-bar", "Balcon")
        ));

        // Observer (notifications console)
        hotel.getNotifications()
                .ajouterObservateur(new NotificationConsole("Réception"));

        Scanner sc = new Scanner(System.in);

        // Création du client
        System.out.print("Nom du client : ");
        String nom = sc.nextLine();

        System.out.print("Prénom du client : ");
        String prenom = sc.nextLine();

        System.out.print("CIN : ");
        String cin = sc.nextLine();

        Client client = new Client(nom, prenom, cin, "", "");
        hotel.ajouterClient(client);

        // Dates
        System.out.print("Date début (YYYY-MM-DD) : ");
        LocalDate debut = LocalDate.parse(sc.nextLine());

        System.out.print("Date fin (YYYY-MM-DD) : ");
        LocalDate fin = LocalDate.parse(sc.nextLine());

        // Affichage chambres disponibles
        System.out.println("\nChambres disponibles :");
        for (Chambre c : hotel.getChambres()) {
            if (c.getEtat().name().equals("DISPONIBLE")) {
                System.out.println(
                        "Numéro " + c.getNumero() +
                                " | " + c.getType() +
                                " | " + c.getPrixParNuit() + "€/nuit"
                );
            }
        }

        // Choix chambre
        System.out.print("\nNuméro de chambre : ");
        int numChambre = Integer.parseInt(sc.nextLine());

        Chambre chambreChoisie = hotel.getChambres().stream()
                .filter(c -> c.getNumero() == numChambre)
                .findFirst()
                .orElse(null);

        if (chambreChoisie == null || chambreChoisie.getEtat().name().equals("RESERVEE")) {
            System.out.println("Chambre non disponible !");
            return;
        }

        // Réservation
        Reservation reservation = hotel.creerReservation(
                client,
                chambreChoisie.getType(),
                debut,
                fin,
                chambreChoisie.getCapacite()
        );

        System.out.println("\nRéservation créée avec succès.");

        // Facture + services
        Facture facture = hotel.genererFacture(reservation);

        System.out.println("\nServices disponibles :");
        int i = 1;
        for (ServiceSupplementaire s : hotel.getServicesSupplementaires()) {
            System.out.println(i + ". " + s.getNom() + " (" + s.getPrix() + "€)");
            i++;
        }

        System.out.print("\nChoisissez les services (ex: 1,3) ou Entrée : ");
        String choix = sc.nextLine();

        if (!choix.isEmpty()) {
            for (String n : choix.split(",")) {
                int index = Integer.parseInt(n.trim()) - 1;
                if (index >= 0 && index < hotel.getServicesSupplementaires().size()) {
                    facture.ajouterService(hotel.getServicesSupplementaires().get(index));
                }
            }
        }
        System.out.println("\n=== RÉCAPITULATIF ===");
        System.out.println("Client : " + client.getNom() + " " + client.getPrenom());
        System.out.println("CIN : " + client.getCin());
        System.out.println("Chambre : " + chambreChoisie.getNumero() + " (" + chambreChoisie.getType() + "), Prix : " + chambreChoisie.getPrixParNuit() + "€/nuit");
        System.out.println("Séjour : " + debut + " → " + fin);
        System.out.println("Services supplémentaires choisis : " + (facture.getServices().isEmpty() ? "Aucun" : facture.getServices()));
        System.out.println("Montant total : " + facture.getMontantTotal() + "€");
        System.out.println("Points fidélité du client : " + client.getPointsFidelite() + " pts");
        PdfGenerator.genererFacturePDF(facture);
        sc.close();
    }
}
