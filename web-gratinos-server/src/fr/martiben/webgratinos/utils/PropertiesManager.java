package fr.martiben.webgratinos.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertiesManager {

    /** Logger de classe. */
    public static Logger mLogger = Logger.getLogger(PropertiesManager.class.getName());

    /**
     * Lecture générique d'un fichier de Properties
     * 
     * @return Les Properties du fichier
     */
    public static Properties readPropertiesFile(String pFile) {
        Properties p = new Properties();
        try {
            p.load(PropertiesManager.class.getClassLoader().getResourceAsStream(pFile));
            mLogger.fine("Props read : " + p.toString());
        } catch (IOException exception) {
        }
        return p;
    }

    /**
     * Recherche d'un property.
     * 
     * @param pFilePath
     *            Chemin du fichier de valeurs simples
     * @param pNomProperty
     *            le nom de la property recherchée
     * @return la property ou null si introuvable
     */
    public static String readPropertyFromFile(String pFilePath, String pNomProperty) {
        return readPropertiesFile(pFilePath).getProperty(pNomProperty);
    }

    /**
     * Ecriture d'un fichier de properties.
     * 
     * @param pFile
     *            chemin du fichier de props
     * @param pProps
     *            Properties à enregistrer
     */
    public static void writeProperties(String pFile, Properties pProps) {
        final URL ressource = PropertiesManager.class.getClassLoader().getResource(pFile);
        mLogger.severe("Path to props to save : " + ressource.getPath());
        mLogger.severe("Props to save : " + pProps.toString());
        try {
            pProps.store(new FileOutputStream(new File(ressource.toURI())), null);
        } catch (IOException exception) {
            mLogger.severe("Error IO in writing props " + exception.getStackTrace());
        } catch (URISyntaxException e) {
            mLogger.severe("Error URI in writing props " + e.getStackTrace());
        }
    }

}
