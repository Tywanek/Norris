
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NorrisJokeList {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("result")
    @Expose
    private List<NorrisJoke> result = null;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<NorrisJoke> getResult() {
        return result;
    }

    public void setResult(List<NorrisJoke> result) {
        this.result = result;
    }

}