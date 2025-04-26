package aiss.gitminer.controller;

import aiss.gitminer.model.Issue;
import aiss.gitminer.service.IssueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gitminer/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Issue create(@RequestBody Issue issue) {
        return issueService.save(issue);
    }

    @GetMapping
    public List<Issue> findAll(@RequestParam(required = false) String state) {
        return (state != null) ? issueService.findByState(state) : issueService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Issue> findById(@PathVariable String id) {
        return issueService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        issueService.deleteById(id);
    }
}