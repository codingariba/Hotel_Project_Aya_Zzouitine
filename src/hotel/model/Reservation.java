package hotel.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private int id;
    private Client client;
    private Chambre chambre;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int nombrePersonnes;
    private double montantTotal;

    public Reservation(int id, Client client, Chambre chambre, LocalDate dateDebut, LocalDate dateFin, int nombrePersonnes) {
        if (dateFin.isBefore(dateDebut))
            throw new IllegalArgumentException("La date de fin doit être après la date de début");
        this.id = id;
        this.client = client;
        this.chambre = chambre;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.nombrePersonnes = nombrePersonnes;
        this.montantTotal = chambre.getPrixParNuit() * ChronoUnit.DAYS.between(dateDebut, dateFin);
    }

    // ✅ Getters
    public int getId() { return id; }
    public double getMontantTotal() { return montantTotal; }
    public Client getClient() { return client; }
    public Chambre getChambre() { return chambre; }
    public LocalDate getDateDebut() { return dateDebut; }
    public LocalDate getDateFin() { return dateFin; }
    public int getNombrePersonnes() { return nombrePersonnes; }

    // ✅ Setters si nécessaire
    public void setChambre(Chambre chambre) { this.chambre = chambre; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }
    public void setNombrePersonnes(int nombrePersonnes) { this.nombrePersonnes = nombrePersonnes; }
    public void setMontantTotal(double montantTotal) { this.montantTotal = montantTotal; }

    @Override
    public String toString() {
        return "Réservation " + id + " - " + client.getNom() + " " + client.getPrenom() +
                " - Chambre " + chambre.getNumero() +
                " du " + dateDebut + " au " + dateFin;
    }
}
