package aiss.gitminer.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testGettersAndSetters() {
        User u = new User();
        u.setId("1");
        u.setUsername("user");
        u.setName("User Name");
        u.setAvatarUrl("http://avatar.com");
        u.setWebUrl("http://web.com");
        assertEquals("1", u.getId());
        assertEquals("user", u.getUsername());
        assertEquals("User Name", u.getName());
        assertEquals("http://avatar.com", u.getAvatarUrl());
        assertEquals("http://web.com", u.getWebUrl());
    }
}
