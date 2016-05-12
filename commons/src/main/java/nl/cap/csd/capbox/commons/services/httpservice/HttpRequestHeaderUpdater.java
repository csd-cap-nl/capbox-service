package nl.cap.csd.capbox.commons.services.httpservice;

import org.apache.http.client.methods.RequestBuilder;

/**
 * This interface is a callback interface to add manipulate the request, before it is sent to the server.
 */
public interface HttpRequestHeaderUpdater {

    void updateHeaders(RequestBuilder requestBuilder);

}
