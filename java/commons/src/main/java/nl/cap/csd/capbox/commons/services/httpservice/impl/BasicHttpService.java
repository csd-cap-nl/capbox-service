package nl.cap.csd.capbox.commons.services.httpservice.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.cap.csd.capbox.commons.services.httpservice.HttpService;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class BasicHttpService<T> implements HttpService<T> {

    private final HttpClient httpClient;

    @Autowired
    public BasicHttpService(final HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public T head(final URL url) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T get(final URL url, final ResponseHandler<T> handler) throws URISyntaxException, IOException {
        return httpClient.execute(new HttpGet(url.toURI()), handler);
    }

    private T sendJsonData(final HttpEntityEnclosingRequestBase httpMethod, final T data,
                           final ResponseHandler<T> handler)
            throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        final HttpEntity entity = new StringEntity(mapper.writeValueAsString(data), ContentType.APPLICATION_JSON);
        httpMethod.setEntity(entity);
        return httpClient.execute(httpMethod, handler);
    }

    @Override
    public T post(final URL url, final T data, final ResponseHandler<T> handler)
            throws URISyntaxException, IOException {
        return sendJsonData(new HttpPost(url.toURI()), data, handler);
    }

    @Override
    public T put(final URL url, final T data, final ResponseHandler<T> handler) throws URISyntaxException, IOException {
        return sendJsonData(new HttpPut(url.toURI()), data, handler);
    }

    @Override
    public T delete(final URL url) {
        throw new UnsupportedOperationException();
    }

}
