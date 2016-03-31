
package eu.alfred.api.market.request;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Changes {

    @SerializedName("change")
    @Expose
    private List<Change> change = new ArrayList<Change>();

    /**
     * 
     * @return
     *     The change
     */
    public List<Change> getChange() {
        return change;
    }

    /**
     * 
     * @param change
     *     The change
     */
    public void setChange(List<Change> change) {
        this.change = change;
    }

}
