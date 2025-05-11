package aiss.gitminer.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    @Test
    void testGettersAndSetters() {
        Comment c = new Comment();
        c.setId("1");
        c.setBody("body");
        c.setCreatedAt("2024-01-01");
        c.setUpdatedAt("2024-01-02");
        assertEquals("1", c.getId());
        assertEquals("body", c.getBody());
        assertEquals("2024-01-01", c.getCreatedAt());
        assertEquals("2024-01-02", c.getUpdatedAt());
    }
}
