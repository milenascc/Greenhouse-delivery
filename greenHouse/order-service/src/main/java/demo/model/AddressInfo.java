package demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Document
public class AddressInfo {
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;

    public AddressInfo() {
    }

    public String getAddress1() {
        return this.address1;
    }

    public String getAddress2() {
        return this.address2;
    }

    public String getCity() {
        return this.city;
    }

    public String getState() {
        return this.state;
    }

    public String getZip() {
        return this.zip;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AddressInfo)) return false;
        final AddressInfo other = (AddressInfo) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$address1 = this.getAddress1();
        final Object other$address1 = other.getAddress1();
        if (this$address1 == null ? other$address1 != null : !this$address1.equals(other$address1)) return false;
        final Object this$address2 = this.getAddress2();
        final Object other$address2 = other.getAddress2();
        if (this$address2 == null ? other$address2 != null : !this$address2.equals(other$address2)) return false;
        final Object this$city = this.getCity();
        final Object other$city = other.getCity();
        if (this$city == null ? other$city != null : !this$city.equals(other$city)) return false;
        final Object this$state = this.getState();
        final Object other$state = other.getState();
        if (this$state == null ? other$state != null : !this$state.equals(other$state)) return false;
        final Object this$zip = this.getZip();
        final Object other$zip = other.getZip();
        if (this$zip == null ? other$zip != null : !this$zip.equals(other$zip)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AddressInfo;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $address1 = this.getAddress1();
        result = result * PRIME + ($address1 == null ? 43 : $address1.hashCode());
        final Object $address2 = this.getAddress2();
        result = result * PRIME + ($address2 == null ? 43 : $address2.hashCode());
        final Object $city = this.getCity();
        result = result * PRIME + ($city == null ? 43 : $city.hashCode());
        final Object $state = this.getState();
        result = result * PRIME + ($state == null ? 43 : $state.hashCode());
        final Object $zip = this.getZip();
        result = result * PRIME + ($zip == null ? 43 : $zip.hashCode());
        return result;
    }

    public String toString() {
        return "AddressInfo(address1=" + this.getAddress1() + ", address2=" + this.getAddress2() + ", city=" + this.getCity() + ", state=" + this.getState() + ", zip=" + this.getZip() + ")";
    }
}
