package br.com.ismadrade.specificationargsresolver.controllers;

import br.com.ismadrade.specificationargsresolver.models.Course;
import br.com.ismadrade.specificationargsresolver.services.CourseService;
import lombok.AllArgsConstructor;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/courses")
@AllArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {

    private final CourseService courseService;


    @GetMapping("/all")
    public ResponseEntity<Page<Course>> getAllCoursesLikeByName(
            @Spec(path = "name", params = "name", spec = Like.class) Specification<Course> spec,
            @PageableDefault(page = 0, size = 10, sort = "courseId", direction = Sort.Direction.DESC) Pageable pageable
    ){
        Specification<Course> isEnabled = (root, query, cb) ->  cb.and(cb.isTrue(root.get("enabled")));

        return Objects.nonNull(spec) ?
                ResponseEntity.status(HttpStatus.OK).body(courseService.findAll(spec.and(isEnabled), pageable)) :
                ResponseEntity.status(HttpStatus.OK).body(courseService.findAll(isEnabled, pageable));

    }

    @GetMapping("/all/name-description")
    public ResponseEntity<Page<Course>> getAllCoursesLikeByNameOrDescription(
            @Or({
                    @Spec(path = "name", params = "name", spec = LikeIgnoreCase.class),
                    @Spec(path = "description", params = "name", spec = LikeIgnoreCase.class)
            }) Specification<Course> spec,
            @PageableDefault(page = 0, size = 10, sort = "courseId", direction = Sort.Direction.ASC) Pageable pageable
    ){

        return ResponseEntity.status(HttpStatus.OK).body(courseService.findAll(spec, pageable));

    }


}
