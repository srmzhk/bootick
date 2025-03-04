package com.srmzhk.bootick.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDto {
    LocalDate date;
    String fromStop;
    String toStop;
}
