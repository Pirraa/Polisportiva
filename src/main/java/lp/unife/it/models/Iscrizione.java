package lp.unife.it.models;

public class Iscrizione {
    private Atleta atleta;
    private AttivitaSportiva attivita;

    public Iscrizione(Atleta atleta, AttivitaSportiva attivita) {
        this.atleta = atleta;
        this.attivita = attivita;
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
