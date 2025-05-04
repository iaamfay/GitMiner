package aiss.gitminer.controller;

import aiss.gitminer.model.Commit;
import aiss.gitminer.repository.CommitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public Commit create(@RequestBody Commit commit) {
        return repository.save(commit);
    }

    // GET: Obtener todos los commits
    @GetMapping
    public List<Commit> findAll() {
        return repository.findAll();
    }

    // GET: Obtener un commit por ID
    @GetMapping("/{id}")
    public Optional<Commit> findById(@PathVariable String id) {
        return repository.findById(id);
    }

    // DELETE: Eliminar un commit por ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }
}