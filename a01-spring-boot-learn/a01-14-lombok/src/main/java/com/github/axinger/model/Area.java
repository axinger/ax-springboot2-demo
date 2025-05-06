package com.github.axinger.model;

import com.github.axinger.extensions.NullSafeExtensions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.ExtensionMethod;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@ExtensionMethod({NullSafeExtensions.class})
public class Area {
    private String name;
}
