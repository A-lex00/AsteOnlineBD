package it.uniroma2.dicii.bd.controller;
import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.ListaOggettiAcquistatiDAO;
import it.uniroma2.dicii.bd.model.dao.GenericProcedureDAO;
import it.uniroma2.dicii.bd.model.domain.ListaOggetti;
import it.uniroma2.dicii.bd.model.domain.Cliente;
import it.uniroma2.dicii.bd.model.domain.OggettoInAsta;

import java.sql.SQLException;

public class OggettiAcquistatiController {

        private String nomeUtente;
        private GenericProcedureDAO<ListaOggetti> listaOggettiAcquistatiDAO;

        public OggettiAcquistatiController(String username) {
            this.nomeUtente = username;
            this.listaOggettiAcquistatiDAO = ListaOggettiAcquistatiDAO.getListaOggettiAcquistatiDAO();
        }


        public void start() {
            try {
                Cliente cliente = new Cliente();
                cliente.setUsername(nomeUtente);

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
                System.err.println("Errore durante il recupero degli oggetti acquistati ");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
