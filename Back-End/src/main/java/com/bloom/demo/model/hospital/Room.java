package com.bloom.demo.model.hospital;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Room {

    @Id
    private Long roomId;
    private String roomType;

}
