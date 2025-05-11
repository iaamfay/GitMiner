package aiss.gitminer.repository;

import aiss.gitminer.model.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProjectRepositoryTest {

    @Autowired
    ProjectRepository repository;

    @Test
    void testSaveAndFindById() {
        Project p = new Project();
        p.setId("1");
        p.setName("Test Project");
        p.setWebUrl("http://test.com");
        repository.save(p);

        Optional<Project> found = repository.findById("1");
        assertTrue(found.isPresent());
        assertEquals("Test Project", found.get().getName());
    }

    @Test
    void testDelete() {
        Project p = new Project();
        p.setId("2");
        p.setName("ToDelete");
        p.setWebUrl("http://delete.com");
        repository.save(p);
        repository.deleteById("2");
        assertFalse(repository.findById("2").isPresent());
    }
}
