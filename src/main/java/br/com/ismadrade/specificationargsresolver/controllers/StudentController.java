package br.com.ismadrade.specificationargsresolver.controllers;

import br.com.ismadrade.specificationargsresolver.models.Student;
import br.com.ismadrade.specificationargsresolver.services.StudentService;
import br.com.ismadrade.specificationargsresolver.specifications.StudentSpec;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/all")
    public ResponseEntity<Page<Student>> getAllStudentsLikeByNameEqualByGender(StudentSpec.LikeByNameEqualsByGender spec,
                                                       @PageableDefault(page = 0, size = 10, sort = "studentId", direction = Sort.Direction.ASC) Pageable pageable){

        return ResponseEntity.status(HttpStatus.OK).body(studentService.findAll(spec, pageable));

    }

    @GetMapping("/all/gender")
    public ResponseEntity<Page<Student>> getAllStudentsInByGender(StudentSpec.InByGender spec,
                                                                 @PageableDefault(page = 0, size = 10, sort = "studentId", direction = Sort.Direction.ASC) Pageable pageable){

        return ResponseEntity.status(HttpStatus.OK).body(studentService.findAll(spec, pageable));

    }

    @GetMapping("/all/ignore-case")
    public ResponseEntity<Page<Student>> getAllStudentsLikeByNameEqualByGenderIgnoreCase(StudentSpec.LikeByNameEqualsByGenderIgnoreCase spec,
                                                                                         @PageableDefault(page = 0, size = 10, sort = "studentId", direction = Sort.Direction.ASC) Pageable pageable){

        return ResponseEntity.status(HttpStatus.OK).body(studentService.findAll(spec, pageable));

    }



    @GetMapping("/all/courses")
    public ResponseEntity<Page<Student>> getAllStudentsInCourseName(StudentSpec.InByCourseNameWithJoin spec,
                                                                                        @PageableDefault(page = 0, size = 10, sort = "studentId", direction = Sort.Direction.ASC) Pageable pageable){

        return ResponseEntity.status(HttpStatus.OK).body(studentService.findAll(spec, pageable));

    }

}
