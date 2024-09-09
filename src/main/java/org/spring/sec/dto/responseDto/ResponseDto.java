package org.spring.sec.dto.responseDto;

import lombok.Data;

import java.time.LocalDateTime;


public record ResponseDto(String message, LocalDateTime timestamp,Object data) {
}
