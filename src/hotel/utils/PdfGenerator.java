package hotel.utils;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import hotel.model.Facture;

import java.io.File;

public class PdfGenerator {

    public static void genererFacturePDF(Facture facture) {
        try {
            // Nom du fichier PDF
            String fileName = "facture_" + facture.getReservation().getClient().getCin() + ".pdf";

            // Créer le PDF
            PdfWriter writer = new PdfWriter(new File(fileName));
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            // Ajouter le contenu
            document.add(new Paragraph("=== FACTURE ==="));
            document.add(new Paragraph("Client : " + facture.getReservation().getClient().getNom() + " " +
                    facture.getReservation().getClient().getPrenom()));
            document.add(new Paragraph("CIN : " + facture.getReservation().getClient().getCin()));
            document.add(new Paragraph("Chambre : " + facture.getReservation().getChambre().getNumero() +
                    " (" + facture.getReservation().getChambre().getType() + ")"));
            document.add(new Paragraph("Séjour : " + facture.getReservation().getDateDebut() +
                    " → " + facture.getReservation().getDateFin()));
            document.add(new Paragraph("Services supplémentaires : " +
                    (facture.getServices().isEmpty() ? "Aucun" : facture.getServices())));
            document.add(new Paragraph("Montant total : " + facture.getMontantTotal() + "€"));

            // Fermer le document
            document.close();

            System.out.println("PDF généré : " + fileName);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
