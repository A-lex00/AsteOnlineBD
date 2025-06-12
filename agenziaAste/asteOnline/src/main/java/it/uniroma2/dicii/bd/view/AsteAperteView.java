
package it.uniroma2.dicii.bd.view;

import it.uniroma2.dicii.bd.model.domain.OggettoInAsta;

import java.util.List;


public class AsteAperteView {


        public static void displayAsteAperte(List<OggettoInAsta> oggetti) {
            if (oggetti == null || oggetti.isEmpty()) {
                System.out.println("\nNon ci sono aste aperte al momento.");
                return;
            }

            System.out.println("\n--- ASTE APERTE ---");
            System.out.printf("%-10s %-30s %-15s %-15s %-10s\n", "CODICE", "DESCRIZIONE", "PREZZO BASE", "OFF. MASSIMA", "CATEGORIA");
            System.out.println("--------------------------------------------------------------------------------------------------");

            for (OggettoInAsta oggetto : oggetti) {
                System.out.printf("%-10s %-30s %-15.2f %-15.2f %-10s\n",
                        oggetto.getCodice(),
                        oggetto.getDescrizione(),
                        oggetto.getPrezzoBase(),
                        oggetto.getOffertaMassima(),
                        oggetto.getCategoria());
            }
            System.out.println("--------------------------------------------------------------------------------------------------");
        }
        public static void displayError(String message) {
            System.err.println(message);
        }

    }