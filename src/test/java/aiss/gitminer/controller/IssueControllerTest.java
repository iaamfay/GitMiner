package aiss.gitminer.controller;

import aiss.gitminer.model.Issue;
import aiss.gitminer.repository.IssueRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import java.util.Optional;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(IssueController.class)
class IssueControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    IssueRepository repository;

    @Test
    void testFindAll() throws Exception {
        Mockito.when(repository.findAll()).thenReturn(List.of(new Issue()));
        mockMvc.perform(get("/gitminer/issues"))
                .andExpect(status().isOk());
    }

    @Test
    void testFindByIdNotFound() throws Exception {
        Mockito.when(repository.findById("1")).thenReturn(Optional.empty());
        mockMvc.perform(get("/gitminer/issues/1"))
                .andExpect(status().isNotFound());
    }
}
