package Model;

public class Locality {
    private int postalCode;
    private String city;

    public Locality(int postalCode, String city) {
        this.postalCode = postalCode;
        this.city = city;
    }


    public int getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
}
