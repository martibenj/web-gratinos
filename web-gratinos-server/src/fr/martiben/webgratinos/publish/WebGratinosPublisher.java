package fr.martiben.webgratinos.publish;

import javax.xml.ws.Endpoint;

import fr.martiben.webgratinos.srv.GratinImpl;

public class WebGratinosPublisher
{

   /** Publish the Web Service locally. */
   public static void main(String[] args)
   {
      Endpoint.publish("http://0.0.0.0:9845/gratin", new GratinImpl());
   }
}