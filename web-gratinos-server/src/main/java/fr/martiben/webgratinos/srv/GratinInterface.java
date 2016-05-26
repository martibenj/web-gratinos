package fr.martiben.webgratinos.srv;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService(endpointInterface = "fr.martiben.webgratinos.srv.GratinInterface")
@SOAPBinding(style = Style.RPC)
public interface GratinInterface {
    /**
     * Indique la disponibilité d'un gratin donné.
     *
     * @param pName le type de gratin demandé.
     * @return la disponibilité d'un gratin donné.
     */
    @WebMethod()
    Boolean isGratinDispo(String pName);

    /**
     * Change la disponibilité d'un gratin donné.
     *
     * @param pName  le type de gratin.
     * @param pValue le status du gratin.
     */
    @WebMethod()
    void changeGratinStatus(String pName, Boolean pValue);
}
