package br.com.ismadrade.specificationargsresolver.services;

import br.com.ismadrade.specificationargsresolver.models.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;



public interface StudentService {

    Page<Student> findAll(Specification<Student> spec, Pageable pageable);
}
