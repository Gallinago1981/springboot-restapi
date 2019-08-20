package example.restapi.type;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Optional;

public enum SubjectType {
    法学部("A"),
    文学部("B"),
    経営学部("C"),
    国際文化学部("D"),
    経済学部("E"),
    社会科学部("F");
    String value;
    SubjectType(String value) {
        this.value = value;
    }
    @JsonValue
    public String getValue() {
        return this.value;
    }
    @JsonCreator
    public static SubjectType getSubjectType(String value) {
        Optional<SubjectType> st = Arrays.stream(values()).filter(t -> t.value.equals(value)).findFirst();
        return st.orElseThrow();
    }


}
