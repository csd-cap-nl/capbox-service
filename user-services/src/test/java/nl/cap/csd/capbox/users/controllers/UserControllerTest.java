package nl.cap.csd.capbox.users.controllers;

import java.util.Collection;
import java.util.Collections;
import nl.cap.csd.capbox.users.model.web.User;
import nl.cap.csd.capbox.users.service.DataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController fixture;

    @Mock
    private DataProvider dataProvider;

    @Test
    public void user_list() {
        when(dataProvider.getUserList()).thenReturn(Collections.EMPTY_LIST);

        final Collection<User> result = fixture.getUsers();

        assertNotNull(result);
    }

}