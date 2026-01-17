package hotel.model;

import hotel.enums.EtatChambre;
import hotel.enums.TypeChambre;
import hotel.enums.TypeService;
import hotel.observer.EnvoieNotification;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Hotel {
    private String nom;
    private TreeSet<Chambre> chambres = new TreeSet<>();
    private Map<String, Client> clients = new HashMap<>();
    private List<Reservation> reservations = new ArrayList<>();
    private List<Facture> factures = new ArrayList<>();
    private List<ServiceSupplementaire> services = new ArrayList<>();

    // Observer
    private EnvoieNotification notifications = new EnvoieNotification();

    public Hotel(String nom) {
        this.nom = nom;
        initialiserServices();
    }

    private void initialiserServices() {
        services.add(new ServiceSupplementaire("Petit-déjeuner", 10.0, TypeService.REPAS));
        services.add(new ServiceSupplementaire("Room service", 20.0, TypeService.ROOM_SERVICE));
        services.add(new ServiceSupplementaire("Spa", 50.0, TypeService.SPA));
        services.add(new ServiceSupplementaire("Parking", 15.0, TypeService.PARKING));
    }

    // Getters
    public String getNom() { return nom; }
    public List<Chambre> getChambres() { return new ArrayList<>(chambres); }
    public List<ServiceSupplementaire> getServicesSupplementaires() { return services; }
    public EnvoieNotification getNotifications() { return notifications; }

    // Ajouter chambre ou client
    public void ajouterChambre(Chambre c) { chambres.add(c); }
    public void ajouterClient(Client c) { clients.put(c.getCin(), c); }

    // Créer une réservation
    public Reservation creerReservation(Client client, TypeChambre type, LocalDate dateDebut, LocalDate dateFin, int nombrePersonnes) {
        // Vérifier disponibilité
        Chambre chambreDisponible = chambres.stream()
                .filter(c -> c.getType() == type && c.getEtat() == EtatChambre.DISPONIBLE && c.getCapacite() >= nombrePersonnes)
                .findFirst().orElse(null);

        if (chambreDisponible == null) {
            throw new IllegalArgumentException("Aucune chambre disponible pour ces critères.");
        }

        // Création de la réservation
        Reservation reservation = new Reservation(reservations.size() + 1, client, chambreDisponible, dateDebut, dateFin, nombrePersonnes);
        reservations.add(reservation);

        // Changer l'état de la chambre
        chambreDisponible.setEtat(EtatChambre.RESERVEE);

        // Ajouter la réservation à l'historique du client
        client.ajouterReservation(reservation);

        // Notifier les observateurs
        notifications.notifierObservateurs("Nouvelle réservation : Chambre " + chambreDisponible.getNumero() +
                " pour " + client.getNom() + " " + client.getPrenom());

        return reservation;
    }

    // Générer facture
    public Facture genererFacture(Reservation reservation) {
        Facture facture = new Facture(factures.size() + 1, reservation);
        factures.add(facture);
        return facture;
    }

    // Consulter disponibilités pour un type de chambre
    public List<Chambre> consulterDisponibilites(TypeChambre type) {
        return chambres.stream()
                .filter(c -> c.getType() == type && c.getEtat() == EtatChambre.DISPONIBLE)
                .collect(Collectors.toList());
    }

    // Annuler réservation
    public void annulerReservation(int idReservation) {
        Reservation reservation = reservations.stream()
                .filter(r -> r.getId() == idReservation)
                .findFirst().orElse(null);

        if (reservation != null) {
            reservation.getChambre().setEtat(EtatChambre.DISPONIBLE);
            reservations.remove(reservation);

            // Notification annulation
            notifications.notifierObservateurs("Réservation annulée : Chambre " + reservation.getChambre().getNumero() +
                    " pour " + reservation.getClient().getNom() + " " + reservation.getClient().getPrenom());
        }
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "nom='" + nom + '\'' +
                ", nombreChambres=" + chambres.size() +
                ", nombreClients=" + clients.size() +
                ", nombreReservations=" + reservations.size() +
                '}';
    }
}
