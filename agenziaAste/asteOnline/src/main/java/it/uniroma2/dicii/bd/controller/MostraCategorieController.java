package it.uniroma2.dicii.bd.controller;



import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.CategoriaDAO;
import it.uniroma2.dicii.bd.model.domain.Categoria;
import it.uniroma2.dicii.bd.model.domain.ListaCategorie;
import it.uniroma2.dicii.bd.view.AmministratoreView;

public class MostraCategorieController implements Controller {

    private final CategoriaDAO categoriaDAO;

    public MostraCategorieController(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    @Override
    public void start() {
        AmministratoreView.displayMessage("--- Elenco Categorie Esistenti ---\n");

        try {
            ListaCategorie categorie = categoriaDAO.execute();

            if (categorie.isEmpty()) {
                AmministratoreView.messaggioErrore("Nessuna categoria trovata nel sistema.");
            } else {
                int i = 1;
                for (Categoria cat : categorie.getLista()) {
                    System.out.println(i++ + ") " + cat.toString());
                }
            }
            System.out.println("-------------------------------------\n");

        } catch (DAOException e) {
            AmministratoreView.messaggioErrore("Errore database durante il recupero delle categorie ");
        }
    }
}