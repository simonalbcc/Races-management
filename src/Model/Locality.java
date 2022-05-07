package Model;

public class Locality {
    private int number;
    private int postalCode;
    private String wording;
    private String coutry;

    public Locality(int number, int postalCode, String wording, String coutry) {
        this.number = number;
        this.postalCode = postalCode;
        this.wording = wording;
        this.coutry = coutry;
    }

    public int getNumber() {
        return number;
    }
    public void setNumber(int number) {
        this.number = number;
    }

    public int getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getWording() {
        return wording;
    }
    public void setWording(String wording) {
        this.wording = wording;
    }

    public String getCoutry() {
        return coutry;
    }
    public void setCoutry(String coutry) {
        this.coutry = coutry;
    }
}
