package nl.cap.csd.capbox.commons.services.httpservice.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.cap.csd.capbox.commons.services.httpservice.HttpRequestHeaderUpdater;
import nl.cap.csd.capbox.commons.services.httpservice.HttpService;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * This is a basic request service, which uses the {@link HttpClient} to send the requests. It relies on the
 * {@link ResponseHandler} to process the received information.
 *
 * This class does not support HEAD or DELETE as requests. Subclass this class if those methods are required.
 *
 * POST/PUT: This class encodes the body of a POST and PUT into a json string
 *
 * @param <T>
 */
public class BasicHttpService<T> implements HttpService<T> {

    private final HttpClient httpClient;
    private final HttpRequestHeaderUpdater headerUpdater;

    @Autowired
    public BasicHttpService(final HttpClient httpClient, final HttpRequestHeaderUpdater headerUpdater) {
        this.httpClient = httpClient;
        this.headerUpdater = headerUpdater;
    }

    @Override
    public T head(final URL url, ResponseHandler<T> handler) throws URISyntaxException, IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public T get(final URL url, final ResponseHandler<T> handler) throws URISyntaxException, IOException {
        return processRequest(RequestBuilder.get(), url, null, handler);
    }

    @Override
    public T post(final URL url, final Object data, final ResponseHandler<T> handler)
            throws URISyntaxException, IOException {
        return processRequest(RequestBuilder.post(), url, data, handler);
    }

    @Override
    public T put(final URL url, final Object data, final ResponseHandler<T> handler)
            throws URISyntaxException, IOException {
        return processRequest(RequestBuilder.put(), url, data, handler);
    }

    @Override
    public T delete(final URL url, ResponseHandler<T> handler) throws URISyntaxException, IOException {
        throw new UnsupportedOperationException();
    }

    private T processRequest(final RequestBuilder builder, final URL url, final Object data,
                             final ResponseHandler<T> handler) throws URISyntaxException, IOException {
        builder.setUri(url.toURI());
        if (headerUpdater != null) {
            headerUpdater.updateHeaders(builder);
        }
        if (data != null) {
            final ObjectMapper mapper = new ObjectMapper();
            final HttpEntity entity = new StringEntity(mapper.writeValueAsString(data), ContentType.APPLICATION_JSON);
            builder.setEntity(entity);
        }
        return httpClient.execute(builder.build(), handler);
    }

}
