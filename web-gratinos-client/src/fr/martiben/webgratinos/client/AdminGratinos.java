package fr.martiben.webgratinos.client;

import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import fr.martiben.webgratinos.srv.GratinInterface;

public class AdminGratinos {

    private static final String GRATIN_NAME = "standard";

    private static final Boolean NEW_VALUE = Boolean.FALSE;

    public static void main(String[] args) throws MalformedURLException {
        URL url = new URL("http://raspi:9845/gratin");
        QName name = new QName("http://srv.webgratinos.martiben.fr/", "GratinImplService");

        Service service = Service.create(url, name);

        GratinInterface gratin = service.getPort(GratinInterface.class);
        gratin.changeGratinStatus(GRATIN_NAME, NEW_VALUE);

        System.out.println(gratin.isGratinDispo(GRATIN_NAME));
    }
}
