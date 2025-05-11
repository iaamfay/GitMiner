package aiss.gitminer.model;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class IssueTest {

    @Test
    void testGettersAndSetters() {
        Issue i = new Issue();
        i.setId("1");
        i.setTitle("Bug");
        i.setDescription("Desc");
        i.setState("open");
        i.setLabels(List.of("bug"));
        assertEquals("1", i.getId());
        assertEquals("Bug", i.getTitle());
        assertEquals("Desc", i.getDescription());
        assertEquals("open", i.getState());
        assertEquals(List.of("bug"), i.getLabels());
    }
}
