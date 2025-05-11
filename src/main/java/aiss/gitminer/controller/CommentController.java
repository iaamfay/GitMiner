package aiss.gitminer.controller;

import aiss.gitminer.exception.CommentNotFoundException;
import aiss.gitminer.model.Comment;
import aiss.gitminer.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/gitminer/comments")
public class CommentController {

    @Autowired
    CommentRepository repository;

    // POST: Crear comentario
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@Valid @RequestBody Comment comment) {
        return repository.save(comment);
    }

    // GET: Listar todos
    @GetMapping
    public List<Comment> findAll() {
        return repository.findAll();
    }

    // GET: Buscar por ID
    @GetMapping("/{id}")
    public Comment findById(@PathVariable String id) throws CommentNotFoundException {
        Optional<Comment> comment = repository.findById(id);
        if(!comment.isPresent()){
            throw new CommentNotFoundException();
        }
        return comment.get();
    }

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


    // DELETE: Eliminar
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }
}