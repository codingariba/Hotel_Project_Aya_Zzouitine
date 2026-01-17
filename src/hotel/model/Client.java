package hotel.model;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String nom;
    private String prenom;
    private String cin;
    private String telephone;
    private String email;
    private List<Reservation> historiqueReservations = new ArrayList<>();
    private int pointsFidelite = 0;

    public Client(String nom, String prenom, String cin, String telephone, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.telephone = telephone;
        this.email = email;
    }

    public void ajouterReservation(Reservation reservation) {
        historiqueReservations.add(reservation);
        pointsFidelite += 10; // 10 points par réservation
    }

    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getCin() { return cin; }
    public List<Reservation> getHistoriqueReservations() { return historiqueReservations; }
    public int getPointsFidelite() { return pointsFidelite; }

    @Override
    public String toString() {
        return nom + " " + prenom + " (" + cin + ")";
    }
}
