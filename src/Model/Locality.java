//region packages & imports
package Model;
//endregion

public class Locality {
    private Integer number;
    private Integer postalCode;
    private String city;
    private String country;
    private Driver drivers[];

    public Locality(Integer number, Integer postalCode, String city, String country) {
        setNumber(number);
        setPostalCode(postalCode);
        setCity(city);
        setCountry(country);
    }

    //region getters & setters
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public Integer getNumber() {
        return number;
    }
    public void setNumber(Integer number) {
        this.number = number;
    }
    //endregion

}
