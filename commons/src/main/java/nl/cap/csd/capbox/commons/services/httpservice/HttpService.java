package nl.cap.csd.capbox.commons.services.httpservice;

import org.apache.http.client.ResponseHandler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * This interface provides a basic way of calling backend services on which this module depends
 *
 * @param <T> Class, which is returned by the http calls. The returning object might be null, partially filled or
 *           completely filled based on the precise description of the called service.
 */
public interface HttpService<T> {

    /**
     * Perform a HEAD http call to the provided url and then process the retrieved information
     *
     * @param url     url to GET the information from
     * @param handler handler to process the retrieved information
     * @return a POJO containing the processed information
     */
    T head(URL url, ResponseHandler<T> handler) throws URISyntaxException, IOException;

    /**
     * Perform a GET http call and then transform the retrieved information
     *
     * @param url     url to GET the information from
     * @param handler handler to process the retrieved information
     * @return a POJO containing the processed information
     * @throws URISyntaxException
     * @throws IOException
     */
    T get(URL url, ResponseHandler<T> handler) throws URISyntaxException, IOException;

    /**
     * Perform a POST http call with the provided data as body and then transform the retrieved information
     *
     * @param url     url to POST the information to
     * @param data    data, which is the body of the message
     * @param handler handler to process the retrieved information
     * @return a POJO containing the processed information
     * @throws URISyntaxException
     * @throws IOException
     */
    T post(URL url, Object data, ResponseHandler<T> handler) throws URISyntaxException, IOException;

    /**
     * Perform a PUT http call with the provided data as body and then transform the retrieved information
     *
     * @param url     url to POST the information to
     * @param data    data, which is the body of the message
     * @param handler handler to process the retrieved information
     * @return a POJO containing the processed information
     * @throws URISyntaxException
     * @throws IOException
     */
    T put(URL url, Object data, ResponseHandler<T> handler) throws URISyntaxException, IOException;

    /**
     * Perform a DELETE http call and then transform the retrieved information
     *
     * @param url     url to GET the information from
     * @param handler handler to process the retrieved information
     * @return a POJO containing the processed information
     * @throws URISyntaxException
     * @throws IOException
     */
    T delete(URL url, ResponseHandler<T> handler) throws URISyntaxException, IOException;

}
