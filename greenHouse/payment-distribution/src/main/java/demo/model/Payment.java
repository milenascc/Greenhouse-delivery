package demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.annotation.Id;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Payment {
    @Id
    String id;

    private long timestamp;
    private int amount;
    private String orderId;
    private CreditCardInfo creditCardInfo;

    public Payment() {
    }

    @JsonIgnore
    public CreditCardInfo getCreditCardInfo() {
        return creditCardInfo;
    }

    public String getId() {
        return this.id;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public int getAmount() {
        return this.amount;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setCreditCardInfo(CreditCardInfo creditCardInfo) {
        this.creditCardInfo = creditCardInfo;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Payment)) return false;
        final Payment other = (Payment) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        if (this.getTimestamp() != other.getTimestamp()) return false;
        if (this.getAmount() != other.getAmount()) return false;
        final Object this$orderId = this.getOrderId();
        final Object other$orderId = other.getOrderId();
        if (this$orderId == null ? other$orderId != null : !this$orderId.equals(other$orderId)) return false;
        final Object this$creditCardInfo = this.getCreditCardInfo();
        final Object other$creditCardInfo = other.getCreditCardInfo();
        if (this$creditCardInfo == null ? other$creditCardInfo != null : !this$creditCardInfo.equals(other$creditCardInfo))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Payment;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final long $timestamp = this.getTimestamp();
        result = result * PRIME + (int) ($timestamp >>> 32 ^ $timestamp);
        result = result * PRIME + this.getAmount();
        final Object $orderId = this.getOrderId();
        result = result * PRIME + ($orderId == null ? 43 : $orderId.hashCode());
        final Object $creditCardInfo = this.getCreditCardInfo();
        result = result * PRIME + ($creditCardInfo == null ? 43 : $creditCardInfo.hashCode());
        return result;
    }

    public String toString() {
        return "Payment(id=" + this.getId() + ", timestamp=" + this.getTimestamp() + ", amount=" + this.getAmount() + ", orderId=" + this.getOrderId() + ", creditCardInfo=" + this.getCreditCardInfo() + ")";
    }
}
