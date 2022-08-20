package br.com.ismadrade.specificationargsresolver;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebAppConfiguration
@SpringBootTest
@ActiveProfiles("test")
public class SpecificationArgsResolverTests {

    private static final String STUDENT_URL = "/students";
    private static final String COURSE_URL ="/courses";

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;


    @BeforeEach
    private void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    @DisplayName("Find a list of students with filter by name and gender ascending order.")
    void shouldFindStudentListWithFilterByNameAndGender() throws Exception{
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(STUDENT_URL + "/all")
                .param("name", "Mar")
                .param("gender", "FEMALE")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].studentId").value("2"))
                .andExpect(jsonPath("$.content[0].name").value("Maria Antônia"))
                .andExpect(jsonPath("$.content[0].gender").value("FEMALE"))
                .andExpect(jsonPath("$.content[1].studentId").value("4"))
                .andExpect(jsonPath("$.content[1].name").value("Marcia dos Santos"))
                .andExpect(jsonPath("$.content[1].gender").value("FEMALE"));
    }

    @Test
    @DisplayName("Find a list of students with filter by multiples gender ascending order.")
    void shouldFindStudentListWithFilterByMultiplesGender() throws Exception{
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(STUDENT_URL + "/all/gender")
                .param("byGendersIn", "FEMALE,MALE")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(4)))
                .andExpect(jsonPath("$.content[0].studentId").value("1"))
                .andExpect(jsonPath("$.content[0].gender").value("MALE"))
                .andExpect(jsonPath("$.content[1].studentId").value("2"))
                .andExpect(jsonPath("$.content[1].gender").value("FEMALE"));
    }

    @Test
    @DisplayName("Find a list of students with filter by name ignoring case sensitive ascending order.")
    void shouldFindStudentListWithFilterByNameIgnoringCaseSensitive() throws Exception{
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(STUDENT_URL + "/all/ignore-case")
                .param("name","mAr")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].studentId").value("2"))
                .andExpect(jsonPath("$.content[0].name").value("Maria Antônia"))
                .andExpect(jsonPath("$.content[0].gender").value("FEMALE"))
                .andExpect(jsonPath("$.content[1].studentId").value("4"))
                .andExpect(jsonPath("$.content[1].name").value("Marcia dos Santos"))
                .andExpect(jsonPath("$.content[1].gender").value("FEMALE"));
    }

    @Test
    @DisplayName("Find a list of students with filter by multiples courses name ascending order.")
    void shouldFindStudentListWithFilterByMultiplesCoursesName() throws Exception{
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(STUDENT_URL + "/all/courses")
                .param("byCoursesIn", "ANGULAR,DOCKER,GOLANG")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(4)));
    }

    @Test
    @DisplayName("Find a list of courses with filter by name descending order.")
    void shouldFindCourseListWithFilterByName() throws Exception{
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(COURSE_URL + "/all")
                .param("name", "JAVA")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)));
    }

    @Test
    @DisplayName("Find a list of courses with filter by name and should to return by name or description ascending order.")
    void shouldFindCourseListWithFilterByNameThatReturnByNameOrDescription() throws Exception{
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(COURSE_URL + "/all/name-description")
                .param("name", "java")
                .accept(MediaType.APPLICATION_JSON);

        mockMvc
                .perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)));
    }






}
