package com.example.edufygenre.services;

import com.example.edufygenre.models.dto.CreateMediaRecordRequest;
import com.example.edufygenre.models.dto.CreateMediaRecordResponse;

//ED-296-AWS
public interface MediaRecordService {
    CreateMediaRecordResponse createRecordOfMedia(CreateMediaRecordRequest request);
}
