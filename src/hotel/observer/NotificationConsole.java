package hotel.observer;

public class NotificationConsole implements RecoitNotification {

    private String nom; // par exemple "Réception"

    public NotificationConsole(String nom) {
        this.nom = nom;
    }

    @Override
    public void notifier(String message) {
        System.out.println("[" + nom + "] Notification : " + message);
    }
}
