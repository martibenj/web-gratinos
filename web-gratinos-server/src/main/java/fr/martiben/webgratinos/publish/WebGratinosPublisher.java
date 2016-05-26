package fr.martiben.webgratinos.publish;

import fr.martiben.webgratinos.srv.GratinImpl;

import javax.xml.ws.Endpoint;

public class WebGratinosPublisher {
    /** Publish the Web Service locally. */
    public static void main(String[] args) {
        Endpoint.publish("http://0.0.0.0:9845/gratin", new GratinImpl());
    }
}