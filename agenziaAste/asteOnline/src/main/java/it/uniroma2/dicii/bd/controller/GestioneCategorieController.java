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
        CategoriaMenuView.mostra();

        try {
            ListaCategorie categorieEsistenti = categoriaDAO.execute();
            List<String> macrocategorieDisponibili = categorieEsistenti.getLista().stream()
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
                CategoriaMenuView.displaySuccess("Categoria  creata con successo!");
            } else {
                CategoriaMenuView.displayError("Creazione categoria fallita.");
            }

        } catch (IOException e) {
            CategoriaMenuView.displayError("Errore di Input durante la gestione delle categorie");
        } catch (IllegalArgumentException e) {
            CategoriaMenuView.displayError("Dati categoria non validi GestioneCategorie");
        } catch (DAOException e) {
            CategoriaMenuView.displayError("Errore database durante la gestione delle categorie");
        }
    }
}