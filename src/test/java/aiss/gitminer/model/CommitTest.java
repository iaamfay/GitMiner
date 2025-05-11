package aiss.gitminer.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommitTest {

    @Test
    void testGettersAndSetters() {
        Commit c = new Commit();
        c.setId("1");
        c.setTitle("Initial commit");
        c.setMessage("msg");
        c.setAuthorName("author");
        c.setAuthorEmail("mail@test.com");
        c.setAuthoredDate("2024-01-01");
        c.setWebUrl("http://test.com");
        assertEquals("1", c.getId());
        assertEquals("Initial commit", c.getTitle());
        assertEquals("msg", c.getMessage());
        assertEquals("author", c.getAuthorName());
        assertEquals("mail@test.com", c.getAuthorEmail());
        assertEquals("2024-01-01", c.getAuthoredDate());
        assertEquals("http://test.com", c.getWebUrl());
    }
}
