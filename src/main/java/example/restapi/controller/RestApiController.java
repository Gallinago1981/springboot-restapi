package example.restapi.controller;

import example.restapi.model.Response;
import example.restapi.model.Student;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path="/student")
public class RestApiController {



    @GetMapping(produces = "application/json;charset=UTF-8")
    public List<Student> get() {
        Student student = new Student();
        student.setId(1);
        student.setName("山田太郎");
        student.setNameKana("ヤマダタロウ");
        student.setGrade(1);
        student.setSubject("経済学部");
        return List.of(student);
    }

    @GetMapping(path = "/{id}", produces = "application/json;charset=UTF-8")
    public Student getStudent(@PathVariable Integer id) {
        Student student = new Student();
        student.setId(2);
        student.setName("山田次郎");
        student.setNameKana("ヤマダジロウ");
        student.setGrade(1);
        student.setSubject("経済学部");
        return student;
    }


    @PostMapping(produces = "application/json;charset=UTF-8")
    public Response post(@RequestBody @Valid Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new Response(bindingResult);
        }
        Response response = new Response();
        response.setResultCode("OK");
        return response;
    }

}
