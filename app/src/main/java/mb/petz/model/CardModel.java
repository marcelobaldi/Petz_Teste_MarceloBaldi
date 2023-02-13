package mb.petz.model;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class CardModel implements Serializable {
    @SerializedName("cardId")       private String id;
    private String name;
    private String img;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }
}

//Model
//- Atributos:      Uns Preferem @SerializedName,       Outros o Nome do Atributo Conforme JSON;
//- Acesso:         Uns Preferem Public,                Outros Private com GetterSetter;