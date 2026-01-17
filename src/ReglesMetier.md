# Documentation des règles métier : Hôtel

## 1. Gestion des chambres

### 1.1 Types de chambres
- **SIMPLE** : capacité 1 personne, prix faible.
- **DOUBLE** : capacité 2 personnes, prix moyen.
- **SUITE** : capacité 4 personnes ou plus, prix élevé, équipements premium (balcon, mini-bar…).

### 1.2 Attributs d’une chambre
- `numero` : numéro unique pour identifier chaque chambre.
- `etage` : étage de l’hôtel où se trouve la chambre.
- `prixParNuit` : coût pour une nuit.
- `capacite` : nombre maximum de personnes.
- `equipements` : liste des équipements disponibles (TV, WiFi, mini-bar, balcon…).
- `type` : type de la chambre (`SIMPLE`, `DOUBLE`, `SUITE`).
- `etat` : état actuel de la chambre (`DISPONIBLE`, `RESERVEE`, `OCCUPEE`, `MAINTENANCE`).

### 1.3 Règles métier
- Une chambre **ne peut être réservée** que si son état est `DISPONIBLE`.
- Lors de la réservation, l’état de la chambre passe à `RESERVEE`.
- Après le check-in, l’état passe à `OCCUPEE`.
- Une chambre peut être mise en `MAINTENANCE` pour des réparations et n’est pas disponible pour réservation.
- Vérification de la capacité lors de la réservation : le nombre de personnes ne peut pas dépasser la capacité de la chambre.

---

## 2. Gestion des clients

### 2.1 Attributs
- `nom` et `prenom`
- `cin` : identifiant unique du client
- `telephone` et `email`
- `historiqueReservations` : liste des réservations passées et en cours
- `pointsFidelite` : points cumulés pour réduire le montant de futures factures

### 2.2 Règles métier
- Chaque client doit avoir un `cin` unique.
- Les points de fidélité sont **attribués à chaque réservation** (exemple : 10 points par réservation).
- Les clients peuvent bénéficier de **réductions en fonction des points** (bonus à implémenter).
- Le client peut consulter son historique de réservations et le détail des factures associées.

---

## 3. Gestion des réservations

### 3.1 Attributs
- `id` : identifiant unique de la réservation
- `client` : client associé
- `chambre` : chambre réservée
- `dateDebut` et `dateFin` : période de séjour
- `nombrePersonnes` : nombre de personnes dans la réservation
- `montantTotal` : montant calculé automatiquement selon `prixParNuit` × nombre de nuits

### 3.2 Règles métier
- La **date de fin doit être postérieure à la date de début**.
- Une réservation ne peut être créée que si **au moins une chambre disponible correspond aux critères** (type et capacité).
- Annulation de réservation : la chambre redevient `DISPONIBLE`.
- Gestion des conflits : aucune double réservation possible pour la même chambre sur la même période.
- Notifications sont envoyées aux observateurs lors de chaque réservation.
- Prolongation de séjour possible si la chambre est disponible pour les nouvelles dates.

---

## 4. Gestion des factures

### 4.1 Attributs
- `id` : identifiant unique
- `reservation` : réservation associée
- `services` : liste de services supplémentaires choisis
- `montantTotal` : calculé automatiquement
- `payee` : statut de paiement (`true` ou `false`)

### 4.2 Règles métier
- Montant total = `(prixParNuit × nombre de nuits) + somme des services`.
- Application de réductions possibles : points fidélité, promotions, séjour long.
- Taxes et frais supplémentaires peuvent être ajoutés (bonus).
- Une facture est **liée à une seule réservation**.
- Une facture peut être mise à jour si des services supplémentaires sont ajoutés après la réservation initiale.

---

## 5. Services supplémentaires

### 5.1 Types de services
- `PETIT_DEJEUNER` : 10€
- `ROOM_SERVICE` : 20€
- `SPA` : 50€
- `PARKING` : 15€

### 5.2 Règles métier
- Les services sont **optionnels** et peuvent être ajoutés à la facture après réservation.
- Chaque service ajouté augmente le montant total de la facture.
- Les services sont liés à une facture, pas directement à la réservation.
- Le client peut choisir plusieurs services à la fois.

---

## 6. Notifications – Pattern Observer

- `EnvoieNotification` : gère les observateurs (ex. Réception, Manager).
- `RecoitNotification` : interface implémentée par les observateurs.
- `NotificationConsole` : exemple d’observateur affichant les notifications dans la console.

### Règles métier
- Chaque action importante (nouvelle réservation, ajout de service, annulation) **notifie tous les observateurs**.
- Permet la **gestion en temps réel des opérations de l’hôtel**.
- Les observateurs peuvent être ajoutés ou retirés dynamiquement.

---

## 7. Règles générales et validations

- Toutes les collections (`clients`, `chambres`, `reservations`) utilisent des structures adaptées :
    - `TreeSet<Chambre>` pour trier par numéro
    - `HashMap<String, Client>` pour accéder rapidement aux clients par CIN
    - `List<Reservation>` pour l’historique des réservations

- Validation des entrées utilisateur :
    - Nombre de personnes ≤ capacité
    - Dates valides (dateFin > dateDebut)
    - Vérification de la disponibilité avant création de réservation

- Gestion des états des objets :
    - Chambre : `DISPONIBLE`, `RESERVEE`, `OCCUPEE`, `MAINTENANCE`
    - Facture : `payee` (true/false)

- Toutes les opérations respectent les règles métier pour assurer **cohérence et fiabilité** du système.

---

