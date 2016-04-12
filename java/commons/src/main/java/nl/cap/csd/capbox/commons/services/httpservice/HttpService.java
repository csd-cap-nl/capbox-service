package nl.cap.csd.capbox.commons.services.httpservice;

import org.apache.http.client.ResponseHandler;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public interface HttpService<T> {

    T head(URL url);
    T get(URL url, ResponseHandler<T> handler) throws URISyntaxException, IOException;
    T post(URL url, T data, ResponseHandler<T> handler) throws URISyntaxException, IOException;
    T put(URL url, T data, ResponseHandler<T> handler) throws URISyntaxException, IOException;
    T delete(URL url);

}
