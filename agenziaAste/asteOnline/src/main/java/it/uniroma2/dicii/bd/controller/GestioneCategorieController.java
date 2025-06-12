package it.uniroma2.dicii.bd.controller;

import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.CategoriaDAO;
import it.uniroma2.dicii.bd.model.dao.CreaCategoriaDAO;

import it.uniroma2.dicii.bd.model.domain.Categoria;
import it.uniroma2.dicii.bd.model.domain.ListaCategorie;
import it.uniroma2.dicii.bd.view.CategoriaMenuView;


import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class GestioneCategorieController implements Controller {

    private final CreaCategoriaDAO creaCategoriaDAO;
    private final CategoriaDAO categoriaDAO;

    public GestioneCategorieController(CreaCategoriaDAO creaCategoriaDAO, CategoriaDAO categoriaDAO) {
        this.creaCategoriaDAO = creaCategoriaDAO;
        this.categoriaDAO = categoriaDAO;
    }

    @Override
    public void start() {
        CategoriaMenuView.displayHeader();

        try {
            ListaCategorie categorieEsistenti = categoriaDAO.execute();
            List<String> macrocategorieDisponibili = categorieEsistenti.getList().stream()
                    .map(Categoria::getNome)
                    .collect(Collectors.toList());

            CategoriaMenuView.DatiNuovaCategoriaInput datiInput = CategoriaMenuView.getDatiNuovaCategoria(macrocategorieDisponibili);

            Boolean successoCreazione;

            if (datiInput.getMacrocategoria().isEmpty()) {
                successoCreazione = creaCategoriaDAO.execute(datiInput.getNomeCategoria());
            } else {
                successoCreazione = creaCategoriaDAO.execute(datiInput.getNomeCategoria(), datiInput.getMacrocategoria());
            }

            if (successoCreazione) {
                CategoriaMenuView.displaySuccess("Categoria '" + datiInput.getNomeCategoria() + "' creata con successo!");
            } else {
                CategoriaMenuView.displayError("Creazione categoria fallita. La categoria potrebbe essere già esistente o la macrocategoria non valida.");
            }

        } catch (IOException e) {
            CategoriaMenuView.displayError("Errore di I/O durante la gestione delle categorie: " + e.getMessage());
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            CategoriaMenuView.displayError("Dati categoria non validi: " + e.getMessage());
            e.printStackTrace();
        } catch (DAOException e) {
            CategoriaMenuView.displayError("Errore database durante la gestione delle categorie: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            CategoriaMenuView.displayError("Si è verificato un errore inatteso: " + e.getMessage());
            e.printStackTrace();
        }
    }
}