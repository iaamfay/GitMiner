package aiss.gitminer.repository;

import aiss.gitminer.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository repository;

    @Test
    void testSaveAndFindById() {
        Comment c = new Comment();
        c.setId("1");
        c.setBody("body");
        c.setCreatedAt("2024-01-01");
        c.setUpdatedAt("2024-01-02");
        repository.save(c);

        Optional<Comment> found = repository.findById("1");
        assertTrue(found.isPresent());
        assertEquals("body", found.get().getBody());
    }
}
