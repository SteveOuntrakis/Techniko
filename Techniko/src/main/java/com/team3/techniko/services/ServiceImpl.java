package com.team3.techniko.services;

import com.team3.techniko.repositories.Repository;
import java.util.List;
import java.util.Optional;

public class ServiceImpl<T> implements Service<T, Long> {

    protected final Repository<T, Long> repository;

    public ServiceImpl(Repository<T, Long> repository) {
        this.repository = repository;
    }

    @Override
    public Optional<T> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public Optional save(T t) {
        return repository.save(t);
    }

    @Override
    public boolean deleteById(Long id) {
        return repository.deleteById(id);
    }
}
