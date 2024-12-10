package lp.unife.it.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Iscrizione {
    private IntegerProperty id;
    private Atleta atleta;
    private AttivitaSportiva attivita;

    public Iscrizione(int id,Atleta atleta, AttivitaSportiva attivita) {
        this.id = new SimpleIntegerProperty(id);
        this.atleta = atleta;
        this.attivita = attivita;
    }

     public Iscrizione() {
        this(0, null, null);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public void setAtleta(Atleta atleta) {
        this.atleta = atleta;
    }

    public AttivitaSportiva getAttivita() {
        return attivita;
    }

    public void setAttivita(AttivitaSportiva attivita) {
        this.attivita = attivita;
    }

    @Override
    public String toString() {
        return "Iscrizione{" +
                "atleta=" + atleta +
                ", attivita=" + attivita +
                '}';
    }
}
