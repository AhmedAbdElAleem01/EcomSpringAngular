package com.springboot.bakefinity.model.dtos;

public class OrderItemDTO {
    private Integer userId;
    private Integer orderId;
    private Integer productId;
    private String productName;
    private Double price;
    private int quantity;

    public OrderItemDTO() {}

    public OrderItemDTO(Integer userId, Integer productId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public Integer getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }
    public Integer getProductId() { return productId; }
    public String getProductName() { return this.productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public Double getPrice() { return this.price; }
    public void setPrice(Double price) { this.price = price;}
    public void setProductId(Integer productId) { this.productId = productId; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
