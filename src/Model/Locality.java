package Model;

public class Locality {
    private Integer postalCode;
    private String city;
    private String country;

    public Locality(int postalCode, String city, String country) {
        this.postalCode = postalCode;
        this.city = city;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
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
