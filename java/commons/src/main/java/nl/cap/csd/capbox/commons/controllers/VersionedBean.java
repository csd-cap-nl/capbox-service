package nl.cap.csd.capbox.commons.controllers;

/**
 * This interface adds version information to toplevel beans.
 *
 * The KeepAliveController reads all the versions from the versioned beans to display, when requested. This information
 * is presented as an json array of VersionInformation objects.
 */
public interface VersionedBean {

    /**
     * Returns the name and version of this bean.
     *
     * @return version information for this bean.
     */
    VersionInformation version();

}
