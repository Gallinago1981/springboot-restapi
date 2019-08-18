package example.restapi.model;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

public class Response {
    private String resultCode;
    private List<Error> error;

    public Response() {}

    public Response(BindingResult bindingResult) {
        this.resultCode = "NG";

        this.error = bindingResult.getAllErrors().stream()
                .filter(o -> o instanceof FieldError)
                .map(o -> (FieldError) o)
                .map(f -> new Error(f))
                .collect(Collectors.toList());

    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public List<Error> getError() {
        return error;
    }

    public void setError(List<Error> error) {
        this.error = error;
    }
}
