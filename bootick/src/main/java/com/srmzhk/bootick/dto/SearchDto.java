package com.srmzhk.bootick.dto;

import com.srmzhk.bootick.model.RouteStop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDto {
    LocalDateTime date;
    RouteStop fromStop;
    RouteStop toStop;
}
