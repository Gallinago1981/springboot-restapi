package example.restapi.model;

import org.springframework.validation.BindingResult;

import java.util.List;

public class RegistResponse {
    private String resultCode;
    private ErrorInfo errorInfo;
    public RegistResponse() {}

    public RegistResponse(BindingResult bindingResult) {
        this.resultCode = "NG";
        this.errorInfo = new ErrorInfo(bindingResult);
    }


    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }


}
