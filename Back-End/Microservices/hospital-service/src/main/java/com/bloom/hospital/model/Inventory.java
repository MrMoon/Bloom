package com.bloom.hospital.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Inventory {

    @Id
    private Long inventoryId;
    private Long inventoryMangedBy;
    private Integer inventoryAmount;
    private String inventoryRecord, inventoryRecordType;

}
