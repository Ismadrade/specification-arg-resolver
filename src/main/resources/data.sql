INSERT INTO TB_STUDENT(student_id, name, gender) VALUES(1, 'João da Silva', 'MALE');
INSERT INTO TB_STUDENT(student_id, name, gender) VALUES(2, 'Maria Antônia', 'FEMALE');
INSERT INTO TB_STUDENT(student_id, name, gender) VALUES(3, 'José Ricardo', 'MALE');
INSERT INTO TB_STUDENT(student_id, name, gender) VALUES(4, 'Marcia dos Santos', 'FEMALE');


INSERT INTO TB_COURSE(course_id, name, description, enabled) VALUES(1, 'ANGULAR', 'This is a front-end angular course', true);
INSERT INTO TB_COURSE(course_id, name, description, enabled) VALUES(2, 'JAVA', 'This is a back-end java course', true);
INSERT INTO TB_COURSE(course_id, name, description, enabled) VALUES(3, 'DOCKER', 'This is a devops docker course. You can use it with java, golang and angular.', true);
INSERT INTO TB_COURSE(course_id, name, description, enabled) VALUES(4, 'GOLANG', 'This is a backend-end golang course', true);
INSERT INTO TB_COURSE(course_id, name, description, enabled) VALUES(5, 'REACTJS', 'This is a frontend-end reactjs course', false);

INSERT INTO TB_COURSES_STUDENTS(id, student_id, course_id) VALUES(1, 1, 1);
INSERT INTO TB_COURSES_STUDENTS(id, student_id, course_id) VALUES(2, 1, 2);
INSERT INTO TB_COURSES_STUDENTS(id, student_id, course_id) VALUES(3, 1, 3);
INSERT INTO TB_COURSES_STUDENTS(id, student_id, course_id) VALUES(4, 2, 1);
INSERT INTO TB_COURSES_STUDENTS(id, student_id, course_id) VALUES(5, 2, 3);
INSERT INTO TB_COURSES_STUDENTS(id, student_id, course_id) VALUES(6, 2, 4);
INSERT INTO TB_COURSES_STUDENTS(id, student_id, course_id) VALUES(7, 3, 1);
INSERT INTO TB_COURSES_STUDENTS(id, student_id, course_id) VALUES(8, 4, 4);
