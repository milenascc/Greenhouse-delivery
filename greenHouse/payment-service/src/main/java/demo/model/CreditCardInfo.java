package demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreditCardInfo {
    private String firstName;
    private String lastName;
    private int expiredMonth;
    private int expiredYear;
    private int securityCode;

    public CreditCardInfo() {
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getExpiredMonth() {
        return this.expiredMonth;
    }

    public int getExpiredYear() {
        return this.expiredYear;
    }

    public int getSecurityCode() {
        return this.securityCode;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setExpiredMonth(int expiredMonth) {
        this.expiredMonth = expiredMonth;
    }

    public void setExpiredYear(int expiredYear) {
        this.expiredYear = expiredYear;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CreditCardInfo)) return false;
        final CreditCardInfo other = (CreditCardInfo) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$firstName = this.getFirstName();
        final Object other$firstName = other.getFirstName();
        if (this$firstName == null ? other$firstName != null : !this$firstName.equals(other$firstName)) return false;
        final Object this$lastName = this.getLastName();
        final Object other$lastName = other.getLastName();
        if (this$lastName == null ? other$lastName != null : !this$lastName.equals(other$lastName)) return false;
        if (this.getExpiredMonth() != other.getExpiredMonth()) return false;
        if (this.getExpiredYear() != other.getExpiredYear()) return false;
        if (this.getSecurityCode() != other.getSecurityCode()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CreditCardInfo;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $firstName = this.getFirstName();
        result = result * PRIME + ($firstName == null ? 43 : $firstName.hashCode());
        final Object $lastName = this.getLastName();
        result = result * PRIME + ($lastName == null ? 43 : $lastName.hashCode());
        result = result * PRIME + this.getExpiredMonth();
        result = result * PRIME + this.getExpiredYear();
        result = result * PRIME + this.getSecurityCode();
        return result;
    }

    public String toString() {
        return "CreditCardInfo(firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", expiredMonth=" + this.getExpiredMonth() + ", expiredYear=" + this.getExpiredYear() + ", securityCode=" + this.getSecurityCode() + ")";
    }
}
