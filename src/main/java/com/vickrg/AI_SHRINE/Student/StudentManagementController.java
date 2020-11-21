package com.vickrg.AI_SHRINE.Student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students")
public class StudentManagementController {

    private  static final List<Student> STUDENTS = Arrays.asList(
            new Student(1, "James Bond"),
            new Student(2, "Maria Jones"),
            new Student(3, "Anna Smith")
    );

    @GetMapping // to tell spring that this is a get request
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMIN_TRAINEE')") //this has hasRole("ROLE_"), hasAnyRole("ROLE_") hasAuthority('permission') hasAnyPermission('permission')
    public List<Student> getAllStudents(){
        System.out.println("getALlStudents");
        return STUDENTS;
    }

    @PostMapping // to tell spring that this is a post request for registering a student
                    //@requestBody tells spring to get student from the request.
    @PreAuthorize("hasAuthority('student:write')")
    public  void registerNewStudent(@RequestBody Student student){
        System.out.println("registerNewStudent");
        System.out.println(student);
    }

    @DeleteMapping(path = "{studentId}") // to tell spring to delete student
                                        //@Pathvariable tells spring the exact student to delete
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable("studentId") Integer studentId){
        System.out.println("deleteStudent");
        System.out.println(studentId);
    }

    @PutMapping(path = "{studentId}") // tells spring to update student
                                        //@Pathvariable tells spring the exact studentId to update

    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student){
        System.out.println("updateStudent");
        System.out.println(String.format("st%s %s", studentId, student));
    }



}
