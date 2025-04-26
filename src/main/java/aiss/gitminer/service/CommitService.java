package aiss.gitminer.service;

import aiss.gitminer.model.Commit;
import aiss.gitminer.repository.CommitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommitService {

    @Autowired
    CommitRepository repository;

    public List<Commit> findAll() {
        return repository.findAll();
    }

    public Optional<Commit> findById(String id) {
        return repository.findById(id);
    }

    public Commit save(Commit commit) {
        return repository.save(commit);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}