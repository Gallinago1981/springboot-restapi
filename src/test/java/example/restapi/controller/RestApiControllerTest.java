package example.restapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.restapi.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.commons.util.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RestApiControllerTest {

    private MockMvc mockMvc;

    @Autowired
    RestApiController restApiController;

    @BeforeEach
    public void before() {
        mockMvc = MockMvcBuilders.standaloneSetup(restApiController).build();
    }

    @DisplayName("生徒一覧の取得")
    @Test
    public void get01() throws Exception {
        mockMvc.perform(get("/student"))
                .andExpect(status().isOk())
                .andExpect(content().json(makeExpect01()));
    }

    private String makeExpect01() throws JsonProcessingException {
        Student student = new Student();
        student.setId(1);
        student.setName("山田太郎");
        student.setNameKana("ヤマダタロウ");
        student.setGrade(1);
        student.setSubject("経済学部");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(List.of(student));
    }

    @DisplayName("生徒詳細情報の取得")
    @Test
    public void getStudent01() throws Exception {
        mockMvc.perform(get("/student/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(makeExpect02()));
    }

    private String makeExpect02() throws JsonProcessingException {
        Student student = new Student();
        student.setId(2);
        student.setName("山田次郎");
        student.setNameKana("ヤマダジロウ");
        student.setGrade(1);
        student.setSubject("経済学部");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(student);
    }
    @DisplayName("生徒詳細情報の取得時に不正ID指定")
    @Test
    public void getStudent02() throws Exception {
        mockMvc.perform(get("/student/a"))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("学生情報の登録")
    @Test
    public void post01() throws Exception {

        mockMvc.perform(
                    post("/student").contentType(MediaType.APPLICATION_JSON_UTF8).content(makeExpect03())
            ).andExpect(status().isOk());
    }
    private String makeExpect03() throws JsonProcessingException {
        Student student = new Student();
        student.setId(1);
        student.setName("山田太郎");
        student.setNameKana("ヤマダタロウ");
        student.setGrade(1);
        student.setSubject("経済学部");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(student);
    }
}
