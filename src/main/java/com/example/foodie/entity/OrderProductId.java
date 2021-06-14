package com.example.foodie.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class OrderProductId implements Serializable {
    private static final long serialVersionUID = 1L;

    private long productId;

    private long orderId;

    public OrderProductId() {
    }

    public OrderProductId(long productId, long orderId) {
        this.productId = productId;
        this.orderId = orderId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderProductId that = (OrderProductId) o;
        return productId == that.productId && orderId == that.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, orderId);
    }
}
