package aiss.gitminer.controller;

import aiss.gitminer.model.Commit;
import aiss.gitminer.repository.CommitRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommitController.class)
class CommitControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CommitRepository repository;

    @Test
    void testFindAll() throws Exception {
        Mockito.when(repository.findAll()).thenReturn(List.of(new Commit()));
        mockMvc.perform(get("/gitminer/commits"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindByIdNotFound() throws Exception {
        Mockito.when(repository.findById("1")).thenReturn(Optional.empty());
        mockMvc.perform(get("/gitminer/commits/1"))
                .andExpect(status().isNotFound());
    }
}
