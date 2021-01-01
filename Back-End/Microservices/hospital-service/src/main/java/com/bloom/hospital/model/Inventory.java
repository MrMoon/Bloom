package com.bloom.hospital.model;

import lombok.Data;

@Data
public class Inventory {

    private Long inventoryMangedBy;
    private Integer inventoryAmount;
    private String inventoryRecord, inventoryRecordType;

}
