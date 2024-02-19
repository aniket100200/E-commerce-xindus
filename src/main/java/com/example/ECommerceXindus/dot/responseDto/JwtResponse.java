package com.example.ECommerceXindus.dot.responseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class JwtResponse {
    String jwtToken;
    String username;
}
