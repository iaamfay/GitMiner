package aiss.gitminer.controller;

import aiss.gitminer.exception.CommentNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.repository.CommentRepository;
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
@RequestMapping("/gitminer/comments")

@Tag(name = "Comment",description = "Comment management API")
public class CommentController {

    @Autowired
    CommentRepository repository;

    // POST: Crear comentario
    @PostMapping
    @Operation(
            summary = "Create a comment",
            description = "Saves a comment given by the user",
            tags = {"Comment","POST"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {@Content(schema =
            @Schema(implementation = Comment.class))})
    })
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@Valid @RequestBody Comment comment) {
        return repository.save(comment);
    }

    // GET: Listar todos
    @Operation(
            summary = "Lists all the comments",
            description = "Creates a List of all the commets",
            tags = {"Comment","Get"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {@Content(schema =
            @Schema(implementation = Comment[].class))})
    })
    @GetMapping
    public List<Comment> findAll() {
        return repository.findAll();
    }

    // GET: Buscar por ID
    @Operation(
            summary = "Searches for a specific Comment",
            description = "Retrieves a comment given his ID",
            tags = {"Comments","GET"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {@Content(schema =
            @Schema(implementation = Comment.class))}),
            @ApiResponse(responseCode = "404",content = {@Content(schema =
            @Schema())})
    })
    @GetMapping("/{id}")
    public Comment findById(@PathVariable String id) throws CommentNotFoundException {
        Optional<Comment> comment = repository.findById(id);
        if(!comment.isPresent()){
            throw new CommentNotFoundException();
        }
        return comment.get();
    }
    @Operation(
            summary = "Updates for a specific Comment",
            description = "Updates a comment given his ID",
            tags = {"Comments","PUT"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {@Content(schema =
            @Schema(implementation = Comment.class))}),
            @ApiResponse(responseCode = "404",content = {@Content(schema =
            @Schema())})
    })
    // PUT: Actualizar un comentario existente
    @PutMapping("/{id}")
    public Comment update(@PathVariable String id, @Valid @RequestBody Comment updatedComment) throws CommentNotFoundException {
        Optional<Comment> existingComment = repository.findById(id);
        if (!existingComment.isPresent()) {
            throw new CommentNotFoundException();
        }

        Comment comment = existingComment.get();

        comment.setBody(updatedComment.getBody());
        comment.setAuthor(updatedComment.getAuthor());
        comment.setCreatedAt(updatedComment.getCreatedAt()); // Aunque no siempre se actualiza este campo
        comment.setUpdatedAt(updatedComment.getUpdatedAt());

        return repository.save(comment);
    }

    @Operation(
            summary = "Deletes a Comment",
            description = "Eliminates a Comment given by a ID",
            tags = {"Comment","DELETE"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = {@Content(schema =
            @Schema())})
    })
    // DELETE: Eliminar
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }
}