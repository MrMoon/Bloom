package com.bloom.demo.model;

import lombok.Data;

@Data
public class StatNumbers {

    Long total, x;

    public void addX() {
        ++this.x;
    }

}
