package fr.martiben.webgratinos.srv;

import fr.martiben.webgratinos.utils.PropertiesManager;

import javax.jws.WebService;
import java.util.Properties;
import java.util.logging.Logger;

@WebService(endpointInterface = "fr.martiben.webgratinos.srv.GratinInterface")
public class GratinImpl implements GratinInterface {

    /** Logger de classe. */
    public static Logger mLogger = Logger.getLogger(PropertiesManager.class.getName());

    /** Chemin d'accès au fichier stockant l'état des gratins. */
    private static final String DISPO_GRATIN_PROPS_PATH = "dispo_gratins.properties";

    /** {@inheritDoc} */
    public Boolean isGratinDispo(String pName) {
        Properties props = PropertiesManager.readPropertiesFile(DISPO_GRATIN_PROPS_PATH);
        mLogger.fine("Prop read : " + props.get(pName));
        mLogger.fine("Prop returned : " + Boolean.parseBoolean("" + props.get(pName)));
        return Boolean.parseBoolean("" + props.get(pName));
    }

    /** {@inheritDoc} */
    @Override
    public void changeGratinStatus(String pName, Boolean pValue) {
        Properties props = PropertiesManager.readPropertiesFile(DISPO_GRATIN_PROPS_PATH);
        mLogger.severe("Props red before change : " + props.toString());
        props.setProperty(pName, pValue.toString());
        mLogger.severe("Props changed : " + props.toString());
        PropertiesManager.writeProperties(DISPO_GRATIN_PROPS_PATH, props);
    }
}
