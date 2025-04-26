package aiss.gitminer.service;

import aiss.gitminer.model.Comment;
import aiss.gitminer.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    CommentRepository repository;

    public List<Comment> findAll() {
        return repository.findAll();
    }

    public Optional<Comment> findById(String id) {
        return repository.findById(id);
    }

    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}