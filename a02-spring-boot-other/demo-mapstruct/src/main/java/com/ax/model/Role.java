package com.ax.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Role {
    private Long id;
    private String roleName;
    private String description;
}
