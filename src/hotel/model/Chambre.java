package hotel.model;

import hotel.enums.EtatChambre;
import hotel.enums.TypeChambre;

import java.util.List;

public class Chambre implements Comparable<Chambre> {
    private int numero;
    private int etage;
    private TypeChambre type;
    private double prixParNuit;
    private int capacite;
    private EtatChambre etat;
    private List<String> equipements;

    public Chambre(int numero, int etage, TypeChambre type, double prixParNuit, int capacite, List<String> equipements) {
        this.numero = numero;
        this.etage = etage;
        this.type = type;
        this.prixParNuit = prixParNuit;
        this.capacite = capacite;
        this.equipements = equipements;
        this.etat = EtatChambre.DISPONIBLE;
    }

    public int getNumero() { return numero; }
    public TypeChambre getType() { return type; }
    public EtatChambre getEtat() { return etat; }
    public void setEtat(EtatChambre etat) { this.etat = etat; }
    public int getCapacite() { return capacite; }
    public double getPrixParNuit() { return prixParNuit; }

    @Override
    public int compareTo(Chambre o) {
        return Integer.compare(this.numero, o.numero);
    }

    @Override
    public String toString() {
        return "Chambre " + numero + " (" + type + ", " + etat + ")";
    }
}
