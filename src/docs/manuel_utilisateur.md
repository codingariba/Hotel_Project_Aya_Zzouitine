# 📘 Manuel utilisateur – Système de réservation d’hôtel

## 1. Présentation générale

Le système de réservation d’hôtel est une application **console Java** permettant de :
- Gérer les chambres d’un hôtel
- Enregistrer des clients
- Créer des réservations
- Ajouter des services supplémentaires
- Générer une facture
- Notifier les utilisateurs via la console

L’application est destinée à un **agent de réception**.

---

## 2. Démarrage de l’application

### Prérequis
- Java JDK 8 ou supérieur
- IntelliJ IDEA
- Projet ouvert et compilé sans erreurs

### Lancement
1. Ouvrir le projet dans IntelliJ IDEA
2. Aller dans `hotel.main.Main`
3. Cliquer sur ▶ **Run**

📸 *Capture écran 1 : Lancement de l’application dans IntelliJ*

![Lancement de l’application](docs/images/lancement.png)

---

## 3. Création d’un client

Au démarrage, l’application demande les informations du client :

Nom du client :  
Prénom du client :  
CIN :

📸 *Capture écran 2 : Saisie des informations du client*

![Création du client](docs/images/saisie_client.png)

---

## 4. Saisie des dates de séjour

L’utilisateur saisit :
- Date de début
- Date de fin

Format obligatoire : `YYYY-MM-DD`

Exemple :  
Date début : 2026-04-08  
Date fin : 2026-04-28

📸 *Capture écran 3 : Saisie des dates de séjour*

![Saisie des dates](docs/images/dates.png)

⚠️ Si la date de fin est antérieure à la date de début, une **exception est levée**.

---

## 5. Consultation des chambres disponibles

L’application affiche la liste des chambres disponibles :

| Numéro | Type   | Prix/nuit |
|--------|--------|-----------|
| 101    | SIMPLE | 50.0€     |
| 102    | DOUBLE | 80.0€     |
| 201    | SUITE  | 150.0€    |

📸 *Capture écran 4 : Affichage des chambres disponibles*

![Chambres disponibles](docs/images/chambres.png)

---

## 6. Réservation d’une chambre

L’utilisateur saisit le numéro de la chambre souhaitée :

Numéro de chambre : 101

Une notification apparaît automatiquement :

[Réception] Notification : Nouvelle réservation : Chambre 101 pour betahhi yaline

📸 *Capture écran 5 : Notification console (Observer Pattern)*

![Notification réservation](docs/images/notification.png)

---

## 7. Choix des services supplémentaires

Les services disponibles sont affichés :

| # | Service            | Prix |
|---|------------------|------|
| 1 | Petit-déjeuner    | 10€  |
| 2 | Room service      | 20€  |
| 3 | Spa               | 50€  |
| 4 | Parking           | 15€  |

L’utilisateur peut :
- Entrer plusieurs numéros (ex : `1,4`)
- Ou appuyer sur Entrée pour aucun service

📸 *Capture écran 6 : Sélection des services supplémentaires*

![Services supplémentaires](docs/images/services.png)

---

## 8. Récapitulatif final

L’application affiche un résumé complet :

=== RÉCAPITULATIF ===
Client : betahhi yaline
Chambre : 101
Séjour : 2026-04-08 → 2026-04-28
Services supplémentaires : Petit-déjeuner, Parking
Montant total : 1025.0€
Points fidélité : 10

📸 *Capture écran 7 : Récapitulatif final*

![Récapitulatif](docs/images/recap.png)

---

## 9. Génération de la facture PDF

À la fin du processus, une facture PDF est générée automatiquement :

facture_48005.pdf

📸 *Capture écran 8 : Fichier PDF généré dans le dossier du projet*

![Facture PDF](docs/images/capture8_facture.png)

---

## 10. Gestion des erreurs

L’application gère les erreurs suivantes :
- Dates invalides
- Chambre indisponible
- Capacité dépassée
- Saisie incorrecte

Les messages d’erreur sont affichés directement dans la console.

---

## 11. Conclusion

Le système permet une utilisation simple et intuitive en console.  
Il assure :
- Une gestion complète des réservations
- Un suivi client efficace
- Une facturation fiable
- Des notifications en temps réel

L’application est adaptée à un usage pédagogique et professionnel.
