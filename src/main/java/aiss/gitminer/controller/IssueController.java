package aiss.gitminer.controller;

import aiss.gitminer.exception.IssueNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Issue;
import aiss.gitminer.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gitminer/issues")
public class IssueController {

    @Autowired
    IssueRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Issue create(@Valid @RequestBody Issue issue) {
        return repository.save(issue);
    }

    @GetMapping
    public List<Issue> findAll(@RequestParam(required = false) String state) {
        return (state != null) ? repository.findByState(state) : repository.findAll();
    }

    @GetMapping("/{id}/comments")
    public List<Comment> findCommentsById(@PathVariable String id) throws IssueNotFoundException {
    Optional<Issue> _issue = repository.findById(id);
    if(!_issue.isPresent()){
        throw new IssueNotFoundException();
    }
    return _issue.map(Issue::getComments).orElse(Collections.emptyList());
    }

    @GetMapping("/{id}")
    public Issue findById(@PathVariable String id) throws IssueNotFoundException {
        Optional<Issue> issue = repository.findById(id);
        if(!issue.isPresent()){
            throw new IssueNotFoundException();
        }
        return issue.get();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }
}