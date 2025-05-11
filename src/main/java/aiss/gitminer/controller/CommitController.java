package aiss.gitminer.controller;

import aiss.gitminer.exception.CommitNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Commit;
import aiss.gitminer.repository.CommitRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gitminer/commits")
@Tag(name = "Commit",description = "Commit managements API")
public class CommitController {

    @Autowired
    CommitRepository repository;
    @Operation(
            summary = "Create a commit",
            description = "Saves a commit given by the user",
            tags = {"Commits","POST"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {@Content(schema =
            @Schema(implementation = Commit.class))})
    })
    // POST: Crear un commit
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Commit create(@Valid @RequestBody Commit commit) {
        return repository.save(commit);
    }
    @Operation(
            summary = "Obtains all the commits",
            description = "Creates a List with all the commits",
            tags = {"Commits","GET"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {@Content(schema =
            @Schema(implementation = Commit.class))})
    })
    // GET: Obtener todos los commits
    @GetMapping
    public List<Commit> findAll() {
        return repository.findAll();
    }

    // GET: Obtener un commit por ID
    @Operation(
            summary = "Obtains a commit",
            description = "Retrieves a Commit object by his ID",
            tags = {"Commits","GET"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {@Content(schema =
            @Schema(implementation = Commit.class))}),
            @ApiResponse(responseCode = "404",content = {@Content(schema =
            @Schema())})
    })
    @GetMapping("/{id}")
    public Commit findById(@Parameter(description = "commit's ID") @PathVariable String id) throws CommitNotFoundException {
        Optional<Commit> commit = repository.findById(id);
        if(!commit.isPresent()){
            throw new CommitNotFoundException();
        }
        return commit.get();
    }
    @Operation(
            summary = "Deletes a commit",
            description = "Deletes a Commit object by his ID",
            tags = {"Commits","DELETE"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {@Content(schema =
            @Schema())})
    })
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
    public void delete(@Parameter(description = "Id of the commit")@PathVariable String id) {
        repository.deleteById(id);
    }
}