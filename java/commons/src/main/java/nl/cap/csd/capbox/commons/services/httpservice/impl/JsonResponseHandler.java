package nl.cap.csd.capbox.commons.services.httpservice.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.impl.client.AbstractResponseHandler;

import java.io.IOException;

public class JsonResponseHandler<T> extends AbstractResponseHandler<T> {

    private final Class<? extends T> contentClass;

    public JsonResponseHandler(final Class<? extends T> contentClass) {
        this.contentClass = contentClass;
    }

    @Override
    public T handleEntity(final HttpEntity httpEntity) throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(httpEntity.getContent(), contentClass);
    }

}
