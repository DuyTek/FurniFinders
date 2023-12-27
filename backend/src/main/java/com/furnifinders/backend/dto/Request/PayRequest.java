package com.furnifinders.backend.dto.Request;

import lombok.Data;

@Data
public class PayRequest {
    private long user_id;
    private String order_delivery_address;
    private String order_delivery_phone;
    private String order_delivery_note;
    private String order_payment_method;

}
