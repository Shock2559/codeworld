package com.shock.codeworld.codeworld.repository;

import com.shock.codeworld.codeworld.entity.StructuralDivision;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StructuralDivisionRepository extends CrudRepository<StructuralDivision, Integer> {
}
