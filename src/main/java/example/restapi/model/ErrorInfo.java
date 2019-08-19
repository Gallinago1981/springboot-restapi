package example.restapi.model;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;


public class ErrorInfo {
    private List<Error> error;

    public ErrorInfo(BindingResult bindingResult) {
        this.error = bindingResult.getAllErrors().stream()
                .filter(o -> o instanceof FieldError)
                .map(o -> (FieldError) o)
                .map(f -> new Error(f))
                .collect(Collectors.toList());

    }
    public List<Error> getError() {
        return error;
    }

    public void setError(List<Error> error) {
        this.error = error;
    }
}
