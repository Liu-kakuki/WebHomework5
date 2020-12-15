package com.example.demo.dao;

import com.example.demo.entity.Linker;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LinkerRepository extends CrudRepository<Linker,Long> {
    List<Linker> findAll();
    void deleteById(Long id);
}
