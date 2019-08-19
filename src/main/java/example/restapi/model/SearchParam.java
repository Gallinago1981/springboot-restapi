package example.restapi.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class SearchParam {

    private Integer page;
    @Min(10)
    @Max(100)
    private Integer count;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String toString() {
        return "page:" + this.page + ", count:" + this.count;
    }

}
