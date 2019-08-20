package example.restapi.model;

import org.springframework.validation.BindingResult;

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

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfo errorInfo) {
        this.errorInfo = errorInfo;
    }
}
