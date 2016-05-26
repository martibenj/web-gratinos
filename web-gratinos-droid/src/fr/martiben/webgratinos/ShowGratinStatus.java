package fr.martiben.webgratinos;

import java.io.IOException;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import fr.martiben.webgratinos.utils.Utilitaires;

public class ShowGratinStatus extends Activity {

    /** Nom du gratin classique. A dégager le jour où il y en aura plusieurs... */
    private static final String GRATIN_NAME = "standard";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_gratin_status);
        initGUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_show_gratin_status, menu);
        return true;
    }

    /** Init GUI elements. */
    private void initGUI() {
        ((ImageButton) findViewById(R.id.sgs_img_context)).setVisibility(View.INVISIBLE);
        new CallURLInBackground().execute((Void) null, (Void) null, (Void) null);
    }

    /**
     * Inner Class d'accès au web de manière asynchrone par rapport à l'UI.
     * 
     * @see http://android-developers.blogspot.fr/2009/05/painless-threading.html
     * @see http://techtej.blogspot.fr/2011/03/android-thread-constructs-part-3.html
     * @author martinelli-b
     */
    private class CallURLInBackground extends AsyncTask<Void, Void, Boolean> {
        private static final String SOAP_ACTION = "http://srv.webgratinos.martiben.fr/isGratinDispo";

        private static final String METHOD_NAME = "isGratinDispo";

        private static final String NAMESPACE = "http://srv.webgratinos.martiben.fr/";

        /**
         * Appel au WebServiceGratinos
         * 
         * @param pVoids
         *            Rien !
         * @return Retour de l'appel HTTP
         */
        @Override
        protected Boolean doInBackground(Void... pVoids) {
            Boolean result = null;
            SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
            request.addProperty("arg0", GRATIN_NAME);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.setOutputSoapObject(request);
            HttpTransportSE httpTransport = new HttpTransportSE("http://" + Utilitaires.readPropertyFromFile(getApplicationContext(), "default.properties", "hostWebService") + ":"
                    + Utilitaires.readPropertyFromFile(getApplicationContext(), "default.properties", "portWebService") + "/gratin?wsdl");
            httpTransport.debug = true;
            try {
                httpTransport.call(SOAP_ACTION, envelope);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            if (envelope.bodyIn != null) {
                SoapObject response = (SoapObject) envelope.bodyIn;
                String resultOfCall = response.getProperty(0).toString();

                result = Boolean.parseBoolean(resultOfCall);
            }
            return result;
        }

        /**
         * Après la recupération de la requête HTTP, affichage du resultat.
         * 
         * @param pRapportTechnique
         *            Résultat à afficher
         */
        protected void onPostExecute(Boolean pStatusGratin) {
            TextView gratinStatus = (TextView) findViewById(R.id.sgs_status);
            if (pStatusGratin == null) {
                ((ImageButton) findViewById(R.id.sgs_img_context)).setImageResource(R.drawable.chan);
                ((ImageButton) findViewById(R.id.sgs_img_context)).setVisibility(View.VISIBLE);
                gratinStatus.setText(getApplicationContext().getString(R.string.gratin_unknown));
            } else if (pStatusGratin) {
                ((ImageButton) findViewById(R.id.sgs_img_context)).setImageResource(R.drawable.ash);
                ((ImageButton) findViewById(R.id.sgs_img_context)).setVisibility(View.VISIBLE);
                gratinStatus.setText(getApplicationContext().getString(R.string.gratin_ok));
            } else {
                ((ImageButton) findViewById(R.id.sgs_img_context)).setImageResource(R.drawable.nelson);
                ((ImageButton) findViewById(R.id.sgs_img_context)).setVisibility(View.VISIBLE);
                gratinStatus.setText(getApplicationContext().getString(R.string.gratin_ko));
            }
        }
    }
}
