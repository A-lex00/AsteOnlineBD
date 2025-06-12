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
                    throw new IOException("File di configurazione del database non trovato");
                }
                properties.load(input);
            } catch (IOException ex) {
                throw new ExceptionInInitializerError("Impossibile caricare la configurazione del database.");
            }
        }

    }

