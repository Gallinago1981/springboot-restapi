package example.restapi.model;

import example.restapi.type.SubjectType;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Student {

    @NotNull(message = "{ERR001}")
    private Integer id;
    @NotNull(message = "{ERR001}")
    private String name;
    @NotNull(message = "{ERR001}")
    private String nameKana;
    @NotNull(message = "{ERR001}")
    private SubjectType subject;
    @Min(value = 1, message="{ERR002}")
    @Max(value = 4, message="{ERR003}")
    private Integer grade;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameKana() {
        return nameKana;
    }

    public void setNameKana(String nameKana) {
        this.nameKana = nameKana;
    }

    public SubjectType getSubject() {
        return subject;
    }

    public void setSubject(SubjectType subject) {
        this.subject = subject;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }
}
