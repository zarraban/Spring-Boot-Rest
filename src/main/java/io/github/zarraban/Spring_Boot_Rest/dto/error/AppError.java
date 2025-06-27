package io.github.zarraban.Spring_Boot_Rest.dto.error;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppError {
    private String message;
    private int statusCode;
}
