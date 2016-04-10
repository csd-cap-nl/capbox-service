package nl.cap.csd.capbox.commons.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class KeepAliveControllerTest {

    @Mock
    private ApplicationContext contextMock;

    @InjectMocks
    private KeepAliveController fixture;

    private final VersionInformation version1Stub = new VersionInformation("version1Stub", "1.0");
    private final VersionInformation version2Stub = new VersionInformation("test2", "2.4");
    private final Map<String, VersionedBean> controllersStubs = new HashMap<>();

    @Before
    public void setUp() {
        fixture.setApplicationContext(contextMock);
        when(contextMock.getBeansOfType(VersionedBean.class)).thenReturn(controllersStubs);
    }

    @Test
    public void get_multiple_versions() {
        controllersStubs.put("test1", new Test1VersionedBean());
        controllersStubs.put("test2", new Test2VersionedBean());

        final List<VersionInformation> result = fixture.getVersions();

        assertEquals(2, result.size());
        assertTrue(result.contains(version1Stub));
        assertTrue(result.contains(version2Stub));
        assertFalse(result.contains(null));
        verify(contextMock).getBeansOfType(VersionedBean.class);
    }

    @Test
    public void get_empty_versions_list() {

        final List<VersionInformation> result = fixture.getVersions();

        assertEquals(0, result.size());
        verify(contextMock).getBeansOfType(VersionedBean.class);
    }

    @Test
    public void get_null_version_information() {
        controllersStubs.put("test1", new Test1VersionedBean());
        controllersStubs.put("test2", new TestNullVersionedBean());

        final List<VersionInformation> result = fixture.getVersions();

        assertEquals(2, result.size());
        assertTrue(result.contains(version1Stub));
        assertTrue(result.contains(null));
        assertFalse(result.contains(version2Stub));
        verify(contextMock).getBeansOfType(VersionedBean.class);
    }

    private class Test1VersionedBean implements VersionedBean {
        @Override
        public VersionInformation version() {
            return version1Stub;
        }
    }

    private class Test2VersionedBean implements VersionedBean {
        @Override
        public VersionInformation version() {
            return version2Stub;
        }
    }

    private class TestNullVersionedBean implements VersionedBean {
        @Override
        public VersionInformation version() {
            return null;
        }
    }
}