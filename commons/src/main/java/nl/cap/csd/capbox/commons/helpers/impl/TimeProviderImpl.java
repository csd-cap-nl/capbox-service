package nl.cap.csd.capbox.commons.helpers.impl;

import nl.cap.csd.capbox.commons.helpers.TimeProvider;

public class TimeProviderImpl implements TimeProvider {

    @Override
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

}
