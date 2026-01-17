package hotel.model;

import java.util.ArrayList;
import java.util.List;

public class Facture {
    private int id;
    private Reservation reservation;
    private List<ServiceSupplementaire> services = new ArrayList<>();
    private double montantTotal;
    private boolean payee = false;

    public Facture(int id, Reservation reservation) {
        this.id = id;
        this.reservation = reservation;
        this.montantTotal = reservation.getMontantTotal();
    }

    // ✅ Méthode ajoutée
    public Reservation getReservation() {
        return reservation;
    }

    public void ajouterService(ServiceSupplementaire service) {
        services.add(service);
        montantTotal += service.getPrix();
    }

    public double getMontantTotal() { return montantTotal; }
    public List<ServiceSupplementaire> getServices() { return services; }
    public void setPayee(boolean payee) { this.payee = payee; }

    @Override
    public String toString() {
        return "Facture " + id + " - Total: " + montantTotal + "€ - Payée: " + payee;
    }
}
