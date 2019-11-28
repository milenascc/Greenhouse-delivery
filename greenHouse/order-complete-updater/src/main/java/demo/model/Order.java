package demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Order {
    String id;

    private String restaurantId;
    private List<ItemQuantity> items;
    private int totalPrice;
    private long orderTime;
    private String specialNote;
    private long deliveryTime;
    private String paymentId;
    private String note;

    private UserInfo userInfo;

    public Order() {
    }

    public String getId() {
        return this.id;
    }

    public String getRestaurantId() {
        return this.restaurantId;
    }

    public List<ItemQuantity> getItems() {
        return this.items;
    }

    public int getTotalPrice() {
        return this.totalPrice;
    }

    public long getOrderTime() {
        return this.orderTime;
    }

    public String getSpecialNote() {
        return this.specialNote;
    }

    public long getDeliveryTime() {
        return this.deliveryTime;
    }

    public String getPaymentId() {
        return this.paymentId;
    }

    public String getNote() {
        return this.note;
    }

    public UserInfo getUserInfo() {
        return this.userInfo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public void setItems(List<ItemQuantity> items) {
        this.items = items;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setOrderTime(long orderTime) {
        this.orderTime = orderTime;
    }

    public void setSpecialNote(String specialNote) {
        this.specialNote = specialNote;
    }

    public void setDeliveryTime(long deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Order)) return false;
        final Order other = (Order) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$restaurantId = this.getRestaurantId();
        final Object other$restaurantId = other.getRestaurantId();
        if (this$restaurantId == null ? other$restaurantId != null : !this$restaurantId.equals(other$restaurantId))
            return false;
        final Object this$items = this.getItems();
        final Object other$items = other.getItems();
        if (this$items == null ? other$items != null : !this$items.equals(other$items)) return false;
        if (this.getTotalPrice() != other.getTotalPrice()) return false;
        if (this.getOrderTime() != other.getOrderTime()) return false;
        final Object this$specialNote = this.getSpecialNote();
        final Object other$specialNote = other.getSpecialNote();
        if (this$specialNote == null ? other$specialNote != null : !this$specialNote.equals(other$specialNote))
            return false;
        if (this.getDeliveryTime() != other.getDeliveryTime()) return false;
        final Object this$paymentId = this.getPaymentId();
        final Object other$paymentId = other.getPaymentId();
        if (this$paymentId == null ? other$paymentId != null : !this$paymentId.equals(other$paymentId)) return false;
        final Object this$note = this.getNote();
        final Object other$note = other.getNote();
        if (this$note == null ? other$note != null : !this$note.equals(other$note)) return false;
        final Object this$userInfo = this.getUserInfo();
        final Object other$userInfo = other.getUserInfo();
        if (this$userInfo == null ? other$userInfo != null : !this$userInfo.equals(other$userInfo)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Order;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $restaurantId = this.getRestaurantId();
        result = result * PRIME + ($restaurantId == null ? 43 : $restaurantId.hashCode());
        final Object $items = this.getItems();
        result = result * PRIME + ($items == null ? 43 : $items.hashCode());
        result = result * PRIME + this.getTotalPrice();
        final long $orderTime = this.getOrderTime();
        result = result * PRIME + (int) ($orderTime >>> 32 ^ $orderTime);
        final Object $specialNote = this.getSpecialNote();
        result = result * PRIME + ($specialNote == null ? 43 : $specialNote.hashCode());
        final long $deliveryTime = this.getDeliveryTime();
        result = result * PRIME + (int) ($deliveryTime >>> 32 ^ $deliveryTime);
        final Object $paymentId = this.getPaymentId();
        result = result * PRIME + ($paymentId == null ? 43 : $paymentId.hashCode());
        final Object $note = this.getNote();
        result = result * PRIME + ($note == null ? 43 : $note.hashCode());
        final Object $userInfo = this.getUserInfo();
        result = result * PRIME + ($userInfo == null ? 43 : $userInfo.hashCode());
        return result;
    }

    public String toString() {
        return "Order(id=" + this.getId() + ", restaurantId=" + this.getRestaurantId() + ", items=" + this.getItems() + ", totalPrice=" + this.getTotalPrice() + ", orderTime=" + this.getOrderTime() + ", specialNote=" + this.getSpecialNote() + ", deliveryTime=" + this.getDeliveryTime() + ", paymentId=" + this.getPaymentId() + ", note=" + this.getNote() + ", userInfo=" + this.getUserInfo() + ")";
    }
}
