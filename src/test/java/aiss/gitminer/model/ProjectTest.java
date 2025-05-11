package aiss.gitminer.model;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class ProjectTest {

    @Test
    void testGettersAndSetters() {
        Project p = new Project();
        p.setId("1");
        p.setName("Test Project");
        p.setWebUrl("http://test.com");
        assertEquals("1", p.getId());
        assertEquals("Test Project", p.getName());
        assertEquals("http://test.com", p.getWebUrl());
    }

    @Test
    void testCommitsAndIssues() {
        Project p = new Project();
        Commit c = new Commit();
        Issue i = new Issue();
        p.setCommits(List.of(c));
        p.setIssues(List.of(i));
        assertEquals(1, p.getCommits().size());
        assertEquals(1, p.getIssues().size());
    }
}
