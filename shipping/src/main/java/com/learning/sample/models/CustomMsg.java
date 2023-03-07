package com.learning.sample.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomMsg {
    @Id
    public String id;

    public String rid;

    public String name;

    public Long ts;
}
