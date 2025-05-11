package aiss.gitminer.repository;

import aiss.gitminer.model.Issue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class IssueRepositoryTest {

    @Autowired
    IssueRepository repository;

    @Test
    void testSaveAndFindById() {
        Issue i = new Issue();
        i.setId("1");
        i.setTitle("Bug");
        i.setState("open");
        repository.save(i);

        assertTrue(repository.findById("1").isPresent());
    }

    @Test
    void testFindByState() {
        Issue i1 = new Issue();
        i1.setId("2");
        i1.setTitle("Bug2");
        i1.setState("open");
        repository.save(i1);

        Issue i2 = new Issue();
        i2.setId("3");
        i2.setTitle("Bug3");
        i2.setState("closed");
        repository.save(i2);

        List<Issue> openIssues = repository.findByState("open");
        assertFalse(openIssues.isEmpty());
        assertTrue(openIssues.stream().anyMatch(i -> "2".equals(i.getId())));
    }
}
