package aiss.gitminer.controller;

import aiss.gitminer.exception.CommitNotFoundException;
import aiss.gitminer.model.Commit;
import aiss.gitminer.repository.CommitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gitminer/commits")
public class CommitController {

    @Autowired
    CommitRepository repository;

    // POST: Crear un commit
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Commit create(@Valid @RequestBody Commit commit) {
        return repository.save(commit);
    }

    // GET: Obtener todos los commits
    @GetMapping
    public List<Commit> findAll() {
        return repository.findAll();
    }

    // GET: Obtener un commit por ID
    @GetMapping("/{id}")
    public Commit findById(@PathVariable String id) throws CommitNotFoundException {
        Optional<Commit> commit = repository.findById(id);
        if(!commit.isPresent()){
            throw new CommitNotFoundException();
        }
        return commit.get();
    }

    @PutMapping("/{id}")
    public Commit update(@PathVariable String id, @Valid @RequestBody Commit updatedCommit) throws CommitNotFoundException {
        Optional<Commit> existing = repository.findById(id);
        if (!existing.isPresent()) {
            throw new CommitNotFoundException();
        }

        Commit commit = existing.get();
        commit.setTitle(updatedCommit.getTitle());
        commit.setMessage(updatedCommit.getMessage());
        commit.setAuthorName(updatedCommit.getAuthorName());
        commit.setAuthorEmail(updatedCommit.getAuthorEmail());
        commit.setAuthoredDate(updatedCommit.getAuthoredDate());
        commit.setWebUrl(updatedCommit.getWebUrl());

        return repository.save(commit);
    }


    // DELETE: Eliminar un commit por ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }
}