package com.furnifinders.backend.dto.Request;

import com.furnifinders.backend.Entity.Enum.DeliveryStatus;
import com.furnifinders.backend.Entity.Enum.PaymentMethod;
import com.furnifinders.backend.Entity.Enum.PaymentStatus;
import lombok.Data;

@Data
public class PayRequest {
    private long user_id;
    private String order_delivery_address;
    private String order_delivery_phone;
    private String order_delivery_note;
    private PaymentMethod order_payment_method;
    private DeliveryStatus order_delivery_status;
    private PaymentStatus order_payment_status;
}
