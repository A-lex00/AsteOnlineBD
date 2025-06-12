package it.uniroma2.dicii.bd.controller;
import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.ListaOggettiAcquistatiDAO;
import it.uniroma2.dicii.bd.model.dao.GenericProcedureDAO;
import it.uniroma2.dicii.bd.model.domain.ListaOggetti;
import it.uniroma2.dicii.bd.model.domain.Cliente;
import it.uniroma2.dicii.bd.model.domain.OggettoInAsta;
public class OggettiAcquistatiController {

        private String nomeUtente;
        private GenericProcedureDAO<ListaOggetti> listaOggettiAcquistatiDAO;

        public OggettiAcquistatiController(String username) {
            this.nomeUtente = username;
            // Inizializza la DAO utilizzando il metodo getInstance()
            this.listaOggettiAcquistatiDAO = ListaOggettiAcquistatiDAO.getInstance();
        }


        public void start() {
            System.out.println("Caricamento degli oggetti acquistati per l'utente: " + nomeUtente);
            try {
                Cliente cliente = new Cliente();
                cliente.setNomeUtente(nomeUtente);

                // Esegue la procedura DAO per ottenere la lista degli oggetti acquistati
                ListaOggetti oggettiAcquistati = listaOggettiAcquistatiDAO.execute(cliente);

                if (oggettiAcquistati != null && !oggettiAcquistati.getList().isEmpty()) {
                    System.out.println("\n--- Oggetti acquistati da " + nomeUtente + " ---");
                    for (OggettoInAsta oggetto : oggettiAcquistati.getList()) {
                        System.out.println("-----------------------------------");
                        System.out.println("Descrizione: " + oggetto.getDescrizione());
                        System.out.println("Prezzo di Acquisto: " + String.format("%.2f", oggetto.getImportoOffertaMassima()) + " €");
                        System.out.println("Dimensioni (HxLxP): " + oggetto.getAltezza() + "x" + oggetto.getLunghezza() + "x" + oggetto.getLarghezza() + " cm");
                        System.out.println("Stato Asta (finale): " + oggetto.getStatoAsta());
                        System.out.println("-----------------------------------");
                    }
                    System.out.println("\n--- Fine elenco oggetti acquistati ---");
                } else {
                    System.out.println("Nessun oggetto acquistato trovato per l'utente " + nomeUtente + ".");
                }

            } catch (DAOException e) {
                System.err.println("Errore durante il recupero degli oggetti acquistati: " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Si è verificato un errore inaspettato: " + e.getMessage());
            }
        }
    }
