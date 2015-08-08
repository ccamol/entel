package cl.entel.appswlsdesa.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by alex on 8/6/14.
 */
public class User {

    @SerializedName("titular")
    private String owner;
    @SerializedName("mercado")
    private String market;
    @SerializedName("nombre")
    private String name;
    private int aaa = -1;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAaa() {
        return aaa;
    }

    public void setAaa(int aaa) {
        this.aaa = aaa;
    }
}
