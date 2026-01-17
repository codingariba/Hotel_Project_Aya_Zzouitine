# 📊 Rapport de tests : Système de réservation d’hôtel

## 1. Objectif du rapport

Ce document présente les tests effectués sur le système de réservation d’hôtel afin de vérifier :

- La robustesse du code
- La gestion correcte des cas normaux et des cas limites
- Le respect des règles métier définies

## 2. Environnement de test

- Langage : Java
- IDE : IntelliJ IDEA
- OS : Windows
- Mode d’exécution : Application console
- Librairies externes : iText (export PDF)

## 3. Jeux de données utilisés

### Chambres

| Numéro | Type   | Capacité | Prix | État initial |
|--------|--------|----------|------|--------------|
| 101    | SIMPLE | 1        | 50€  | DISPONIBLE   |
| 102    | DOUBLE | 2        | 80€  | DISPONIBLE   |
| 201    | SUITE  | 4        | 150€ | DISPONIBLE   |

### Services supplémentaires

- Petit-déjeuner (10€)
- Room service (20€)
- Spa (50€)
- Parking (15€)

## 4. Cas de tests fonctionnels

### ✅ Test 1 : Création d’une réservation valide

**Entrées**
- Client : betahhi yaline
- Chambre : 101
- Dates : 2026-04-08 → 2026-04-28

**Résultat attendu**
- Réservation créée
- Chambre passe à l’état `RESERVEE`
- Notification affichée en console
- Points fidélité +10

**Résultat obtenu**  
✔ Conforme

### ❌ Test 2 : Date de fin antérieure à la date de début (cas limite)

**Entrées**
- Date début : 2026-05-10
- Date fin : 2026-05-05

**Résultat attendu**
- Exception levée
- Réservation refusée

**Résultat obtenu**  
✔ Exception `IllegalArgumentException`

### ❌ Test 3 : Réservation d’une chambre déjà réservée

**Entrées**
- Chambre 101 déjà réservée
- Nouvelle demande pour la même période

**Résultat attendu**
- Réservation refusée
- Message d’erreur

**Résultat obtenu**  
✔ Conforme

### ❌ Test 4 : Aucun service sélectionné

**Entrées**
- Champ services vide

**Résultat attendu**
- Facture sans services
- Montant basé uniquement sur les nuits

**Résultat obtenu**  
✔ Conforme

### ❌ Test 5 : Nombre de personnes supérieur à la capacité (cas limite)

**Entrées**
- Chambre SIMPLE (1 personne)
- Nombre de personnes = 2

**Résultat attendu**
- Chambre non proposée
- Réservation impossible

**Résultat obtenu**  
✔ Conforme

## 5. Tests sur la facturation

### ✅ Test 6 : Calcul du montant total

**Entrées**
- 20 nuits × 50€
- Services : Petit-déjeuner + Parking

**Résultat attendu**
- (20 × 50) + 10 + 15 = **1025€**

**Résultat obtenu**  
✔ 1025€

### ✅ Test 7 : Génération de la facture PDF

**Résultat attendu**
- Fichier `facture_<CIN>.pdf` généré
- Contenu conforme à la réservation

**Résultat obtenu**  
✔ PDF généré avec succès

## 6. Tests Observer (Notifications)

### ✅ Test 8 : Notification lors d’une réservation

**Résultat attendu**
[Réception] Notification : Nouvelle réservation : Chambre 101 pour betahhi yaline

**Résultat obtenu**  
✔ Conforme

## 7. Tests de fidélité

### ✅ Test 9 : Points fidélité

**Règle**
- +10 points par réservation

**Résultat**
- Points affichés après réservation

✔ Conforme

## 8. Conclusion

Le système :

- Gère correctement les règles métier
- Traite les cas limites sans erreur critique
- Affiche les notifications en temps réel
- Produit des factures correctes et exportables en PDF

Le projet est **fonctionnel, robuste et conforme aux exigences pédagogiques**.
