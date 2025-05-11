package aiss.gitminer.repository;

import aiss.gitminer.model.Commit;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommitRepositoryTest {

    @Autowired
    CommitRepository repository;

    @Test
    void testSaveAndFindById() {
        Commit c = new Commit();
        c.setId("1");
        c.setTitle("Initial commit");
        c.setMessage("msg");
        c.setAuthorName("author");
        c.setAuthorEmail("mail@test.com");
        c.setAuthoredDate("2024-01-01");
        c.setWebUrl("http://test.com");
        repository.save(c);

        Optional<Commit> found = repository.findById("1");
        assertTrue(found.isPresent());
        assertEquals("Initial commit", found.get().getTitle());
    }
}
