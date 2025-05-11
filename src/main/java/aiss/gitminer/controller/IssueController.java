package aiss.gitminer.controller;

import aiss.gitminer.exception.IssueNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.model.Issue;
import aiss.gitminer.repository.IssueRepository;
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
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gitminer/issues")
@Tag(name = "Issues",description = "Issue management API")
public class IssueController {

    @Autowired
    IssueRepository repository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create a issue",
            description = "Saves a issue given by the user",
            tags = {"Issue","POST"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {@Content(schema =
            @Schema(implementation = Issue.class))})
    })
    public Issue create(@Valid @RequestBody Issue issue) {
        return repository.save(issue);
    }
    @Operation(
            summary = "Retrieves all the Issues",
            description = "Creates a List of all the issues",
            tags = {"Issue","GET"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {@Content(schema =
            @Schema(implementation = Issue[].class))})
    })
    @GetMapping
    public List<Issue> findAll(@RequestParam(required = false) String state) {
        return (state != null) ? repository.findByState(state) : repository.findAll();
    }

    @GetMapping("/{id}/comments")
    @Operation(
            summary = "Retrieves the comments of a specific Issue",
            description = "Returns a List of comments by the Issue's ID",
            tags = {"Issue","GET"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {@Content(schema =
            @Schema(implementation = Comment[].class))}),
            @ApiResponse(responseCode = "404",content = {@Content(schema =
            @Schema())})
    })
    public List<Comment> findCommentsById(@PathVariable String id) throws IssueNotFoundException {
    Optional<Issue> _issue = repository.findById(id);
    if(!_issue.isPresent()){
        throw new IssueNotFoundException();
    }
    return _issue.map(Issue::getComments).orElse(Collections.emptyList());
    }
    @Operation(
            summary = "Retrieves a specific Issue",
            description = "Returns an Issue by his ID",
            tags = {"Issue","GET"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {@Content(schema =
            @Schema(implementation = Comment[].class))}),
            @ApiResponse(responseCode = "404",content = {@Content(schema =
            @Schema())})
    })
    @GetMapping("/{id}")
    public Issue findById(@PathVariable String id) throws IssueNotFoundException {
        Optional<Issue> issue = repository.findById(id);
        if(!issue.isPresent()){
            throw new IssueNotFoundException();
        }
        return issue.get();
    }
    @Operation(
            summary = "Updates a specific Issue",
            description = "Updates an Issue by his ID",
            tags = {"Issue","POST"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {@Content(schema =
            @Schema(implementation = Comment[].class))}),
            @ApiResponse(responseCode = "404",content = {@Content(schema =
            @Schema())})
    })
    @PutMapping("/{id}")
    public Issue update(@PathVariable String id, @Valid @RequestBody Issue updatedIssue) throws IssueNotFoundException {
        Optional<Issue> existing = repository.findById(id);
        if (!existing.isPresent()) {
            throw new IssueNotFoundException();
        }

        Issue issue = existing.get();
        issue.setTitle(updatedIssue.getTitle());
        issue.setDescription(updatedIssue.getDescription());
        issue.setState(updatedIssue.getState());
        issue.setCreatedAt(updatedIssue.getCreatedAt());
        issue.setUpdatedAt(updatedIssue.getUpdatedAt());
        issue.setClosedAt(updatedIssue.getClosedAt());
        issue.setLabels(updatedIssue.getLabels());
        issue.setAuthor(updatedIssue.getAuthor());
        issue.setAssignee(updatedIssue.getAssignee());
        issue.setVotes(updatedIssue.getVotes());
        issue.setComments(updatedIssue.getComments());

        return repository.save(issue);
    }

    @Operation(
            summary = "Deletes a specific Issue",
            description = "Deletes an Issue by his ID",
            tags = {"Issue","DELETE"}
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