package aiss.gitminer.controller;

import aiss.gitminer.exception.ProjectNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Project;
import aiss.gitminer.repository.ProjectRepository;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/gitminer/projects")
@Tag(name = "Project",description = "Project management API")
public class ProjectController {

    @Autowired
    ProjectRepository repository;
    @Operation(
            summary = "Lists all the projects",
            description = "Creates a List of all the projects",
            tags = {"Project","GET"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {@Content(schema =
            @Schema(implementation = Project[].class))})
    })
    @GetMapping
    public List<Project> findAll() {
        return repository.findAll();
    }
    @Operation(
            summary = "Returns a Project",
            description = "Selects a project by his ID",
            tags = {"Project","GET"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {@Content(schema =
            @Schema(implementation = Project[].class))}),
            @ApiResponse(responseCode = "404",content = {@Content(schema =
            @Schema())})
    })
    @GetMapping("/{id}")
    public Project findById(@PathVariable String id) throws ProjectNotFoundException {
        Optional<Project> project = repository.findById(id);
        if(!project.isPresent()){
            throw new ProjectNotFoundException();
        }
        return project.get();
    }
    @Operation(
            summary = "Creates a Project",
            description = "Saves a Project",
            tags = {"Project","POST"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {@Content(schema =
            @Schema(implementation = Project.class))})
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(@Valid @RequestBody Project project) {
        return repository.save(project);
    }
    @Operation(
            summary = "Updates a Project",
            description = "Selects a project by his ID and updates it",
            tags = {"Project","GET"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {@Content(schema =
            @Schema(implementation = Project[].class))}),
            @ApiResponse(responseCode = "404",content = {@Content(schema =
            @Schema())})
    })
    @PutMapping("/{id}")
    public Project update(@PathVariable String id, @Valid @RequestBody Project updatedProject) throws ProjectNotFoundException {
        Optional<Project> existing = repository.findById(id);
        if (!existing.isPresent()) {
            throw new ProjectNotFoundException();
        }

        Project project = existing.get();
        project.setName(updatedProject.getName());
        project.setWebUrl(updatedProject.getWebUrl());
        project.setCommits(updatedProject.getCommits());
        project.setIssues(updatedProject.getIssues());

        return repository.save(project);
    }



    @Operation(
            summary = "Deletes a Project",
            description = "Eliminates a Project given by a ID",
            tags = {"Project","DELETE"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {@Content(schema =
            @Schema())})
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }
}