package nl.cap.csd.capbox.commons.services.version.impl;

import nl.cap.csd.capbox.commons.services.version.VersionInformation;
import nl.cap.csd.capbox.commons.services.version.VersionService;
import nl.cap.csd.capbox.commons.services.version.VersionedBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@SuppressWarnings("unchecked")
@RunWith(MockitoJUnitRunner.class)
public class VersionServiceImplTest {

    private static final String KNOWN_VERSIONED_BEAN = "KnownVersionedBean";
    private static final String KNOWN_UNVERSIONED_BEAN = "KnownUnversionedBean";
    private static final String UNKNOWN_BEAN = "UnknownBean";

    private final VersionInformation version1Stub = new VersionInformation("version1Stub", "1.0");
    private final VersionInformation version2Stub = new VersionInformation("test2", "2.4");
    private final VersionInformation unversionedStub = new VersionInformation("Integer", "Unknown");
    private final Map<String, VersionedBean> versionedBeanStubs = new HashMap<>();

    @Mock
    private ApplicationContext contextMock;

    private VersionService fixture = new VersionServiceImpl();

    @Before
    public void setUp() {
        when(contextMock.getBeansOfType(VersionedBean.class)).thenReturn(versionedBeanStubs);
        when(contextMock.getBean(KNOWN_VERSIONED_BEAN)).thenReturn(new Test1VersionedBean());
        when(contextMock.getBean(KNOWN_UNVERSIONED_BEAN)).thenReturn(Integer.parseInt("10"));
        when(contextMock.getBean(UNKNOWN_BEAN)).thenThrow(new NoSuchBeanDefinitionException("Test"));
        when(contextMock.getBean(Test1VersionedBean.class)).thenReturn(new Test1VersionedBean());
        when(contextMock.getBean(Integer.class)).thenReturn(Integer.parseInt("10"));
        when(contextMock.getBean(String.class)).thenThrow(new NoSuchBeanDefinitionException("Test"));
        ((VersionServiceImpl) fixture).setApplicationContext(contextMock);
    }

    @Test
    public void get_all_versions_filled_list() {
        versionedBeanStubs.put("test1", new Test1VersionedBean());
        versionedBeanStubs.put("test2", new Test2VersionedBean());
        versionedBeanStubs.put("test3", new TestNullVersionedBean());

        final List<VersionInformation> result = fixture.getAllVersions();

        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.contains(version1Stub));
        assertTrue(result.contains(version2Stub));
        assertTrue(result.contains(null));
        verify(contextMock).getBeansOfType(VersionedBean.class);
        verify(contextMock, never()).getBean(any(Class.class));
        verify(contextMock, never()).getBean(anyString());
    }

    @Test
    public void get_all_versions_empty_list() {
        final List<VersionInformation> result = fixture.getAllVersions();

        assertNotNull(result);
        assertEquals(0, result.size());
        verify(contextMock).getBeansOfType(VersionedBean.class);
        verify(contextMock, never()).getBean(any(Class.class));
        verify(contextMock, never()).getBean(anyString());
    }

    @Test
    public void get_version_of_versioned_bean() {
        final VersionInformation resultByClass = fixture.getVersionForBean(Test1VersionedBean.class);
        final VersionInformation resultByString = fixture.getVersionForBean(KNOWN_VERSIONED_BEAN);

        assertEquals(version1Stub, resultByClass);
        assertEquals(version1Stub, resultByString);
        verify(contextMock, never()).getBeansOfType(any(Class.class));
        verify(contextMock).getBean(Test1VersionedBean.class);
        verify(contextMock).getBean(KNOWN_VERSIONED_BEAN);
    }

    @Test
    public void get_version_of_unversioned_bean() {
        final VersionInformation resultByClass = fixture.getVersionForBean(Integer.class);
        final VersionInformation resultByString = fixture.getVersionForBean(KNOWN_UNVERSIONED_BEAN);

        assertEquals(unversionedStub.toString(), resultByClass.toString());
        assertEquals(unversionedStub.toString(), resultByString.toString());
        verify(contextMock, never()).getBeansOfType(any(Class.class));
        verify(contextMock).getBean(Integer.class);
        verify(contextMock).getBean(KNOWN_UNVERSIONED_BEAN);
    }

    @Test
    public void get_version_of_unknown_bean_by_class() {
        final VersionInformation result = fixture.getVersionForBean(String.class);

        assertEquals("Unknown", result.getName());
        assertEquals("Error", result.getVersion());
        verify(contextMock, never()).getBeansOfType(any(Class.class));
        verify(contextMock).getBean(String.class);
    }

    @Test
    public void get_version_of_unknown_bean_by_name() {
        final VersionInformation result = fixture.getVersionForBean(UNKNOWN_BEAN);

        assertEquals("Unknown", result.getName());
        assertEquals("Error", result.getVersion());
        verify(contextMock, never()).getBeansOfType(any(Class.class));
        verify(contextMock).getBean(UNKNOWN_BEAN);
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