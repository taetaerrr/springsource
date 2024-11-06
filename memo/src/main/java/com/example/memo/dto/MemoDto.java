package com.example.memo.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemoDto {
    private Long mno;
    private String memoText;
    private LocalDateTime createdDateTime;
    private LocalDateTime lastModifiedDateTime;

}
