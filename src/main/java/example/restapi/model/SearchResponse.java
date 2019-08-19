package example.restapi.model;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResponse {
    private String resultCode;
    private ErrorInfo errorInfo;
    private List<Student> studentList;

    public SearchResponse() {}

    public SearchResponse(BindingResult bindingResult) {
        this.resultCode = "NG";
        this.errorInfo = new ErrorInfo(bindingResult);
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }
}
