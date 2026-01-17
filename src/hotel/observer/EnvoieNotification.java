package hotel.observer;

import java.util.ArrayList;
import java.util.List;

public class EnvoieNotification {

    private List<RecoitNotification> observateurs = new ArrayList<>();

    public void ajouterObservateur(RecoitNotification obs) {
        observateurs.add(obs);
    }

    public void notifierObservateurs(String message) {
        for (RecoitNotification obs : observateurs) {
            obs.notifier(message);
        }
    }
}
