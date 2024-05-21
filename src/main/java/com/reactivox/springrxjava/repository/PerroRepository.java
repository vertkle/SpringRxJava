package com.reactivox.springrxjava.repository;

import com.reactivox.springrxjava.model.Perro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerroRepository extends JpaRepository<Perro, Integer> {
}
