package com.team3.techniko.services;

import com.team3.techniko.repositories.Repository;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceImpl<T> implements Service<T,Long> {

    protected final Repository<T, Long> repository;
    // with this command he stopped reddening the log
    private static final Logger log = LoggerFactory.getLogger(ServiceImpl.class);

    public ServiceImpl(Repository<T, Long> repository) {
        this.repository = repository;
    }

    @Override
    public Optional<T> getById(Long id) {

        for (int i = 0; i < repository.findAll().size(); i++) {
            if (repository.findAll().get(i).equals(id)) {
                return repository.findById(id);

            }
        }
        throw new NoSuchElementException("Den vrethike  Property owner me id: " + id);

    }

    @Override
    public List<T> getAll() {

        // Validate : if List<PropertyOwner> is empty return one empty list
        if (repository.findAll().isEmpty()) {
            return List.of();
        }
        return repository.findAll();
    }

    @Override
    public Optional save(T t) {
        return repository.save(t);
    }

    @Override
    public boolean deleteById(Long id) {

        for (int i = 0; i < repository.findAll().size(); i++) {
            if (repository.findAll().get(i).equals(id)) {
                repository.deleteById(id);
                break;
            }
        }
        throw new NoSuchElementException("Den vrethike department me id: " + id);
    }
}
