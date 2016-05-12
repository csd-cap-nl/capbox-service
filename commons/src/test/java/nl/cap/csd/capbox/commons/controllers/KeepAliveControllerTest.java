package nl.cap.csd.capbox.commons.controllers;

import nl.cap.csd.capbox.commons.services.version.VersionInformation;
import nl.cap.csd.capbox.commons.services.version.VersionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class KeepAliveControllerTest {

    @Mock
    private VersionService versionServiceMock;

    @InjectMocks
    private KeepAliveController fixture;

    private final VersionInformation version1Stub = new VersionInformation("version1Stub", "1.0");
    private final VersionInformation version2Stub = new VersionInformation("test2", "2.4");

    @Before
    public void setUp() {
        when(versionServiceMock.getAllVersions()).thenReturn(Arrays.asList(version1Stub, version2Stub));
    }

    @Test
    public void get_versions() {
        final List<VersionInformation> result = fixture.getVersions();

        assertEquals(2, result.size());
        assertTrue(result.contains(version1Stub));
        assertTrue(result.contains(version2Stub));
        assertFalse(result.contains(null));
        verify(versionServiceMock).getAllVersions();
    }

    @Test
    public void get_keepalive() {
        final String result = fixture.keepalive();

        Pattern pattern = Pattern.compile("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}.\\d+");
        assertTrue(pattern.matcher(result)
                .matches());
    }

    @Test
    public void get_own_version() {
        final VersionInformation result = fixture.version();

        assertNotNull(result);
        assertNull(result.getName());
        assertNull(result.getVersion());
    }

}