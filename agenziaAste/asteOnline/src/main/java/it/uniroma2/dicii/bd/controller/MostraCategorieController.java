package it.uniroma2.dicii.bd.controller;



import it.uniroma2.dicii.bd.exception.DAOException;
import it.uniroma2.dicii.bd.model.dao.CategoriaDAO;
import it.uniroma2.dicii.bd.model.domain.Categoria;
import it.uniroma2.dicii.bd.model.domain.ListaCategorie;
import it.uniroma2.dicii.bd.view.AmministratoreView; // O una CategoriaView specifica per la visualizzazione

public class MostraCategorieController implements Controller {

    private final CategoriaDAO categoriaDAO;

    public MostraCategorieController(CategoriaDAO categoriaDAO) {
        this.categoriaDAO = categoriaDAO;
    }

    @Override
    public void start() {
        AmministratoreView.displayGenericError("--- Elenco Categorie Esistenti ---"); // Puoi usare una view più specifica se ce l'hai

        try {
            ListaCategorie categorie = categoriaDAO.execute(); // Recupera tutte le categorie

            if (categorie.isEmpty()) {
                AmministratoreView.displayGenericError("Nessuna categoria trovata nel sistema.");
            } else {
                int i = 1;
                for (Categoria cat : categorie.getList()) {
                    System.out.println(i++ + ") " + cat.toString()); // Utilizza il toString() della Categoria
                }
            }
            System.out.println("-------------------------------------\n");

        } catch (DAOException e) {
            AmministratoreView.displayGenericError("Errore database durante il recupero delle categorie: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) { // Catch all per errori imprevisti
            AmministratoreView.displayGenericError("Si è verificato un errore inatteso durante la visualizzazione delle categorie: " + e.getMessage());
            e.printStackTrace();
        }
    }
}