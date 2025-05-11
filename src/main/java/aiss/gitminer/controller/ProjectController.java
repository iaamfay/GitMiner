package aiss.gitminer.controller;

import aiss.gitminer.exception.ProjectNotFoundException;
import aiss.gitminer.model.Project;
import aiss.gitminer.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gitminer/projects")
public class ProjectController {

    @Autowired
    ProjectRepository repository;

    @GetMapping
    public List<Project> findAll() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Project findById(@PathVariable String id) throws ProjectNotFoundException {
        Optional<Project> project = repository.findById(id);
        if(!project.isPresent()){
            throw new ProjectNotFoundException();
        }
        return project.get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(@Valid @RequestBody Project project) {
        return repository.save(project);
    }

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


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }
}