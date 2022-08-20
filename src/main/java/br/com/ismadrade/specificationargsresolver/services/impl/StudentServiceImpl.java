package br.com.ismadrade.specificationargsresolver.services.impl;

import br.com.ismadrade.specificationargsresolver.models.Student;
import br.com.ismadrade.specificationargsresolver.repositories.StudentRepository;
import br.com.ismadrade.specificationargsresolver.services.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;



@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Page<Student> findAll(Specification<Student> spec, Pageable pageable) {
        return studentRepository.findAll(spec, pageable);
    }
}
