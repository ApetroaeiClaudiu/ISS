public class Comanda {
    private String idClient;
    private int idMed;
    private int cantitate;

    public String getIdClient() {
        return idClient;
    }

    public int getIdMed() {
        return idMed;
    }

    public int getCantitate() {
        return cantitate;
    }

    public Comanda(String idClient, int idMed, int cantitate) {
        this.idClient = idClient;
        this.idMed = idMed;
        this.cantitate = cantitate;
    }
}
