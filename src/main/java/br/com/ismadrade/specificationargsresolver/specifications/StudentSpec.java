package br.com.ismadrade.specificationargsresolver.specifications;

import br.com.ismadrade.specificationargsresolver.models.Student;
import net.kaczmarzyk.spring.data.jpa.domain.*;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;


public class StudentSpec {


    @And({
            @Spec(path = "name", spec= Like.class),
            @Spec(path = "gender", spec = Equal.class)
    })
    public interface LikeByNameEqualsByGender extends Specification<Student> {}

    @And({
            @Spec(path = "name", spec= LikeIgnoreCase.class),
            @Spec(path = "gender", spec= EqualIgnoreCase.class)
    })
    public interface LikeByNameEqualsByGenderIgnoreCase extends Specification<Student> {}

    @And({
            @Spec(path = "gender", params="byGendersIn", paramSeparator = ',', spec= In.class)
    })
    public interface InByGender extends Specification<Student> {}


    @Join(path = "courses", alias= "c")
    @And({
            @Spec(path = "c.name", params="byCoursesIn", paramSeparator = ',', spec= In.class)
    })
    public interface InByCourseNameWithJoin extends Specification<Student> {}

}
