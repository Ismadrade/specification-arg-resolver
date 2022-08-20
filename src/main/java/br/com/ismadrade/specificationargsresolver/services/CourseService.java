package br.com.ismadrade.specificationargsresolver.services;

import br.com.ismadrade.specificationargsresolver.models.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

public interface CourseService {

    Page<Course> findAll(Specification<Course> spec, Pageable pageable);
}
