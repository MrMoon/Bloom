package com.bloom.hospital.model;

import lombok.Data;

@Data
public class Inventory {

    private Long inventoryMangedBy;
    private String inventoryRecord , inventoryRecordType , inventoryAmount;

}
