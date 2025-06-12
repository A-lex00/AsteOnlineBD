package it.uniroma2.dicii.bd.utilities;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DatabaseConfig {

        private static final String CONFIG_FILE = "src/db.properties";
        private static final Properties properties = new Properties();

        static {
            try (InputStream input = DatabaseConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
                if (input == null) {
                    System.err.println("Spiacente, impossibile trovare " + CONFIG_FILE + " nel classpath.");
                    throw new IOException("File di configurazione del database non trovato: " + CONFIG_FILE);
                }
                properties.load(input);
            } catch (IOException ex) {
                System.err.println("Errore durante il caricamento del file di configurazione del database: " + ex.getMessage());
                // Esempio per fermare l'applicazione se la configurazione è critica
                throw new ExceptionInInitializerError("Impossibile caricare la configurazione del database.");
            }
        }

        public static String getDbUrl() {
            return properties.getProperty("db.url");
        }

        public static String getDbUsername() {
            return properties.getProperty("db.username");
        }

        public static String getDbPassword() {
            return properties.getProperty("db.password");
        }
    }

