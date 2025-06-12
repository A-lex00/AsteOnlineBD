package it.uniroma2.dicii.bd.controller;
import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.VisuallizzaAsteInCorso;
import it.uniroma2.dicii.bd.model.dao.GenericProcedureDAO;
import it.uniroma2.dicii.bd.model.domain.ListaOggetti;
import it.uniroma2.dicii.bd.model.domain.Cliente;
import it.uniroma2.dicii.bd.model.domain.OggettoInAsta;
public class AsteInCorsoController {

        private String nomeUtente;
        private GenericProcedureDAO<ListaOggetti> visualizzaAsteInCorsoDAO;

        public AsteInCorsoController(String username) {
            this.nomeUtente = username;
            this.visualizzaAsteInCorsoDAO = VisuallizzaAsteInCorso.getInstance();
        }

        public void start() {
            System.out.println("Caricamento delle aste in corso per l'utente: " + nomeUtente);
            try {
                // Crea un oggetto Cliente con l'username per passarlo alla DAO
                Cliente cliente = new Cliente();
                cliente.setNomeUtente(nomeUtente);

                // Esegue la procedura DAO per ottenere la lista degli oggetti in asta
                ListaOggetti asteInCorso = visualizzaAsteInCorsoDAO.execute(cliente);

                if (asteInCorso != null && !asteInCorso.getList().isEmpty()) {
                    System.out.println("\n--- Le tue aste in corso ---");
                    for (OggettoInAsta oggetto : asteInCorso.getList()) {
                        System.out.println("-----------------------------------");
                        System.out.println("Codice: " + oggetto.getCodice());
                        System.out.println("Descrizione: " + oggetto.getDescrizione());
                        System.out.println("Prezzo Base: " + String.format("%.2f", oggetto.getPrezzoBase()) + " €");
                        System.out.println("Offerta Massima: " + String.format("%.2f", oggetto.getImportoOffertaMassima()) + " €");
                        System.out.println("Inizio Asta: " + oggetto.getInizioAsta());
                        System.out.println("Fine Asta: " + oggetto.getFineAsta());
                        System.out.println("Durata: " + oggetto.getDurata() + " giorni");
                        System.out.println("Stato: " + oggetto.getStato());
                        System.out.println("Categoria: " + oggetto.getCategoria());
                        System.out.println("Dimensioni (HxLxP): " + oggetto.getAltezza() + "x" + oggetto.getLunghezza() + "x" + oggetto.getLarghezza() + " cm");
                        System.out.println("-----------------------------------");
                    }
                    System.out.println("\n--- Fine elenco aste in corso ---");
                } else {
                    System.out.println("Non ci sono aste in corso per l'utente " + nomeUtente + " al momento.");
                }

            } catch (DAOException e) {
                System.err.println("Errore durante il recupero delle aste in corso: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Si è verificato un errore inaspettato: " + e.getMessage());
            }
        }
    }

