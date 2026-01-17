# Jeux de données de test – Hôtel

## 1. Chambres de test

| Numéro | Étage | Type   | Capacité | Prix/Nuit | Équipements |
|------|------|--------|----------|-----------|-------------|
| 101  | 1    | SIMPLE | 1        | 50€       | TV, WiFi |
| 102  | 1    | DOUBLE | 2        | 80€       | TV, WiFi, Mini-bar |
| 103  | 1    | DOUBLE | 2        | 85€       | TV, WiFi |
| 201  | 2    | SUITE  | 4        | 150€      | TV, WiFi, Mini-bar, Balcon |
| 202  | 2    | SUITE  | 3        | 130€      | TV, WiFi |

États initiaux :
- Toutes les chambres sont à l’état **DISPONIBLE**

---

## 2. Clients de test

| Nom     | Prénom | CIN     | Téléphone     | Email |
|--------|--------|---------|---------------|-------|
| El Amrani | Yaline | AA45007 | 0612345678 | yaline@email.com |
| Benali | Ghita | BB78123 | 0623456789 | ghita@email.com |
| Idrissi | Karim | CC99112 | 0634567890 | karim@email.com |

Chaque client commence avec :
- 0 point de fidélité
- Historique de réservation vide

---

## 3. Réservations de test

| ID | Client | Chambre | Dates | Personnes | Montant |
|----|--------|--------|-------|-----------|---------|
| 1  | Yaline El Amrani | 101 | 2026-03-08 → 2026-03-10 | 1 | 100€ |
| 2  | Ghita Benali | 102 | 2026-04-01 → 2026-04-05 | 2 | 320€ |

Effets :
- Chambres concernées passent à l’état **RESERVEE**
- Les clients gagnent des points de fidélité

---

## 4. Services supplémentaires de test

| Service | Type | Prix |
|-------|------|------|
| Petit-déjeuner | PETIT_DEJEUNER | 10€ |
| Room Service | ROOM_SERVICE | 20€ |
| Spa | SPA | 50€ |
| Parking | PARKING | 15€ |

---

## 5. Factures de test

### Facture 1 – Réservation 101
- 2 nuits × 50€ = 100€
- Services : Petit-déjeuner
- Total : **110€**

### Facture 2 – Réservation 102
- 4 nuits × 80€ = 320€
- Services : Spa, Parking
- Total : **385€**

---

## 6. Notifications attendues (Observer)

- "Nouvelle réservation : Chambre 101 pour Yaline El Amrani"
- "Nouvelle réservation : Chambre 102 pour Ghita Benali"
- "Service ajouté : Spa à la facture"

---

## 7. Cas limites testés

- Date de fin antérieure à la date de début → ❌ Exception
- Réservation d’une chambre déjà réservée → ❌ Refus
- Nombre de personnes > capacité → ❌ Refus
- Ajout de service après facturation → ✅ Montant mis à jour
