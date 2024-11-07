package com.mikolajjanik.hospital_catering_admin.controller;

import com.mikolajjanik.hospital_catering_admin.dto.DiaryDTO;
import com.mikolajjanik.hospital_catering_admin.entity.Diary;
import com.mikolajjanik.hospital_catering_admin.service.DiaryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diary")
public class DiaryController {
    private final DiaryService diaryService;
    @Autowired
    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Diary> getDiaryById(@PathVariable ("id") Long id) {
        Diary diary = diaryService.getDiaryById(id);
        return new ResponseEntity<>(diary, HttpStatus.OK);
    }

    @PostMapping("/add")
    @ResponseBody
    public ResponseEntity<Diary> addDiary(@Valid @RequestBody DiaryDTO diaryDTO) {
        Diary diary = diaryService.createDiary(diaryDTO);
        return new ResponseEntity<>(diary, HttpStatus.CREATED);
    }

}