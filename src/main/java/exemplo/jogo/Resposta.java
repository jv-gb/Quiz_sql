package exemplo.jogo;

public class Resposta {
    public String resposta;
    public boolean correta;

    public Resposta(String resposta, boolean correta) {
        this.resposta = resposta;
        this.correta = correta;
    }

    @Override
    public String toString() {
        return "Resposta{" +
                "resposta='" + resposta + '\'' +
                ", correta=" + correta +
                '}';
    }
}
