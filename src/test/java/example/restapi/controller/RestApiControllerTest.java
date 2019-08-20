package example.restapi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.restapi.model.SearchResponse;
import example.restapi.model.Student;
import example.restapi.type.SubjectType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RestApiControllerTest {

    Logger logger = LoggerFactory.getLogger(RestApiControllerTest.class);

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

    @DisplayName("生徒一覧の取得（Request Parameterあり）")
    @Test
    public void get02() throws Exception {
        mockMvc.perform(get("/student?page=1"))
                .andExpect(status().isOk())
                .andExpect(content().json(makeExpect01()));
    }

    private String makeExpect01() throws JsonProcessingException {
        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setResultCode("OK");
        Student student = new Student();
        student.setId(1);
        student.setName("山田太郎");
        student.setNameKana("ヤマダタロウ");
        student.setGrade(1);
        student.setSubject(SubjectType.経営学部);
        searchResponse.setStudentList(List.of(student));
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(searchResponse);
    }

    @DisplayName("生徒詳細情報の取得")
    @Test
    public void getStudent01() throws Exception {
        mockMvc.perform(get("/student/1"))
                .andExpect(status().isOk())
                .andExpect(content().json(makeExpect02()));
    }

    private String makeExpect02() throws JsonProcessingException {
        SearchResponse searchResponse = new SearchResponse();
        searchResponse.setResultCode("OK");
        Student student = new Student();
        student.setId(1);
        student.setName("山田太郎");
        student.setNameKana("ヤマダタロウ");
        student.setGrade(1);
        student.setSubject(SubjectType.経営学部);
        searchResponse.setStudentList(List.of(student));
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(searchResponse);
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
                    post("/student")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(makeTestData01())
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("resultCode", is("OK")));

    }
    private String makeTestData01() throws JsonProcessingException {
        Student student = new Student();
        student.setId(1);
        student.setName("山田太郎");
        student.setNameKana("ヤマダタロウ");
        student.setGrade(1);
        student.setSubject(SubjectType.経営学部);
        ObjectMapper objectMapper = new ObjectMapper();
        String testJson = objectMapper.writeValueAsString(student);
        logger.info(testJson);
        return testJson;
    }

    @DisplayName("学生情報の登録(入力エラー)")
    @Test
    public void post02() throws Exception {
        ResultActions resultActions = mockMvc.perform(
                post("/student")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(makeTestData02()));

        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("resultCode", is("NG")));
        resultActions.andExpect(jsonPath("errorInfo.error", hasSize(1)));


    }
    private String makeTestData02() throws JsonProcessingException {
        Student student = new Student();
        student.setName("山田太郎");
        student.setNameKana("ヤマダタロウ");
        student.setGrade(1);
        student.setSubject(SubjectType.経営学部);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(student);
    }

}
