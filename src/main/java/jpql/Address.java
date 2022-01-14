package jpql;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String city;
    private String strret;
    private String zipcode;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStrret() {
        return strret;
    }

    public void setStrret(String strret) {
        this.strret = strret;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }
}
