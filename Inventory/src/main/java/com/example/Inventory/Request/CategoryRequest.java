package com.example.Inventory.Request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CategoryRequest {
    private String id;
    private String name;

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
