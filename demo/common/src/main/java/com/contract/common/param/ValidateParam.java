package com.contract.common.param;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ValidateParam {
    private MultipartFile file;
    private Long id;
    private String type;
}
