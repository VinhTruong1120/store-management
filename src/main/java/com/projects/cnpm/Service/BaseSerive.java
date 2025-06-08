package com.projects.cnpm.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class BaseSerive<T,ID extends Serializable, R extends JpaRepository<T,ID>> {
    protected R repositoty;

    @Autowired
    public void setRepository(R repository) {
        this.repositoty = repository;
    }

    public boolean kiemTraTonTai(ID id) {
        Optional<T> optional = repositoty.findById(id);
        return optional.isPresent();
    }

    
    public T timTheoId(ID id) {
        Optional<T> optional = repositoty.findById(id);
        return optional.orElse(null);
    }

    public List<T> FindAll(){
        return repositoty.findAll();
    }

    
}
