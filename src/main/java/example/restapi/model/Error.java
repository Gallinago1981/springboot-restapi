package example.restapi.model;

import org.springframework.validation.FieldError;

public class Error {
    private String fieldId;
    private String message;

    public Error() {}

    public Error(FieldError fieldError) {
        this.fieldId = fieldError.getField();
        this.message = fieldError.getDefaultMessage();
    }

    public String getFieldId() {
        return fieldId;
    }

    public void setFieldId(String fieldId) {
        this.fieldId = fieldId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
