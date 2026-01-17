package hotel.model;

import hotel.enums.TypeService;

public class ServiceSupplementaire {
    private String nom;
    private double prix;
    private TypeService type;

    public ServiceSupplementaire(String nom, double prix, TypeService type) {
        this.nom = nom;
        this.prix = prix;
        this.type = type;
    }

    public String getNom() { return nom; }
    public double getPrix() { return prix; }
    public TypeService getType() { return type; }

    @Override
    public String toString() {
        return nom + " (" + type + ", " + prix + "€)";
    }
}
