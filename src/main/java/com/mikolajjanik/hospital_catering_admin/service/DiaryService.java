package com.mikolajjanik.hospital_catering_admin.service;

import com.mikolajjanik.hospital_catering_admin.dto.DiaryDTO;
import com.mikolajjanik.hospital_catering_admin.entity.Diary;

public interface DiaryService {
    Diary getDiaryById(Long id);
    Diary createDiary(DiaryDTO diaryDTO);
}
