package br.com.ismadrade.specificationargsresolver.services.impl;

import br.com.ismadrade.specificationargsresolver.models.Course;
import br.com.ismadrade.specificationargsresolver.repositories.CourseRepository;
import br.com.ismadrade.specificationargsresolver.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;


    @Override
    public Page<Course> findAll(Specification<Course> spec, Pageable pageable) {
        return courseRepository.findAll(spec, pageable);
    }
}
