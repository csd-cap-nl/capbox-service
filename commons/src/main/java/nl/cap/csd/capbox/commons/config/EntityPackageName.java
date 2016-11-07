package nl.cap.csd.capbox.commons.config;

public final class EntityPackageName {

    private final String name;

    public EntityPackageName(final Class<?> entityClass) {
        this.name = entityClass.getPackage().getName();
    }

    public final String getName() {
        return name;
    }

}
