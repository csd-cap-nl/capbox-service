package nl.cap.csd.capbox.commons.services.version;

public class VersionInformation {

    private final String name;
    private final String version;


    public VersionInformation(final String name, final String version) {
        this.name = name;
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public String getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "VersionInformation{" +
                "name='" + name + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
