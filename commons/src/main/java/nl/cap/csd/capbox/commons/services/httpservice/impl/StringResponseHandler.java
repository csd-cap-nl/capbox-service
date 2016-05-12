package nl.cap.csd.capbox.commons.services.httpservice.impl;

import org.apache.http.HttpEntity;
import org.apache.http.impl.client.AbstractResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public abstract class StringResponseHandler<T> extends AbstractResponseHandler<T> {

    @Override
    public final T handleEntity(final HttpEntity entity) throws IOException {
        final String entityString = EntityUtils.toString(entity);
        return handleEntity(entityString);
    }

    protected abstract T handleEntity(final String entityString);

}
