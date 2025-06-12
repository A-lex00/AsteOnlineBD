package it.uniroma2.dicii.bd.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class CategoriaMenuView {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        public static void mostra() {
            System.out.println("\n--- Creazione Nuova Categoria ---");
        }

        public static DatiNuovaCategoriaInput getDatiNuovaCategoria(List<String> macrocategorieDisponibili) throws IOException {
            System.out.print("Inserisci il nome della nuova categoria: ");
            String nomeCategoria = reader.readLine().trim();

            if (nomeCategoria.isEmpty()) {
                throw new IllegalArgumentException("Il nome della categoria non può essere vuoto.");
            }

            System.out.println("\nMacrocategorie esistenti:");
            if (macrocategorieDisponibili.isEmpty()) {
                System.out.println("  (Nessuna macrocategoria disponibile. Questa sarà una categoria di primo livello.)");
            } else {
                for (int i = 0; i < macrocategorieDisponibili.size(); i++) {
                    System.out.println("  " + (i + 1) + ". " + macrocategorieDisponibili.get(i));
                }
            }
            System.out.print("Inserisci il nome della macrocategoria (lascia vuoto per categoria di primo livello): ");
            String macrocategoria = reader.readLine().trim();

            if (!macrocategoria.isEmpty() && !macrocategorieDisponibili.contains(macrocategoria)) {
                System.out.println("AVVISO: La macrocategoria '" + macrocategoria + "' non esiste tra quelle caricate.");
            }

            return new DatiNuovaCategoriaInput(nomeCategoria, macrocategoria);
        }

        public static void displaySuccess(String message) {
            System.out.println("\nSUCCESS: " + message);
        }

        public static void displayError(String message) {
            System.err.println("\nERRORE: " + message);
        }

        public static class DatiNuovaCategoriaInput {
            private String nomeCategoria;
            private String macrocategoria;

            public DatiNuovaCategoriaInput(String nomeCategoria, String macrocategoria) {
                this.nomeCategoria = nomeCategoria;
                this.macrocategoria = macrocategoria;
            }

            public String getNomeCategoria() {
                return nomeCategoria;
            }

            public String getMacrocategoria() {
                return macrocategoria;
            }
        }
    }

