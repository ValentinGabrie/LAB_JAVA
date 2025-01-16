package lab7;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/*clasa abstractă InstrumentMuzical cu variabile membre:
o producator
o pret
*/

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
public class InstrumentMuzical {
    private String producator;
    private int pret;

    public InstrumentMuzical() {

    }
    public InstrumentMuzical(String producator, int pret) {

        this.producator = producator;
        this.pret = pret;
    }

    public String getProducator() {
        return producator;
    }

    public void setProducator(String producator) {
        this.producator = producator;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "InstrumentMuzical{" +
                "producator='" + producator + '\'' +
                ", pret=" + pret +
                '}';
    }
}
