package aiss.gitminer.service;

import aiss.gitminer.model.Issue;
import aiss.gitminer.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IssueService {

    @Autowired
    IssueRepository repository;

    public List<Issue> findAll() {
        return repository.findAll();
    }

    public Optional<Issue> findById(String id) {
        return repository.findById(id);
    }

    public List<Issue> findByState(String state) {
        return repository.findByState(state);
    }

    public Issue save(Issue issue) {
        return repository.save(issue);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}