package com.example.timetable.controller;

import com.example.timetable.model.TimetableEntry;
import com.example.timetable.service.TimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/timetable")
public class TimetableController {
    @Autowired
    private TimetableService timetableService;

    @GetMapping("/generate")
    public ResponseEntity<String> generateTimetable() {
        timetableService.generateTimetable();
        return new ResponseEntity<>("Timetable generated successfully!", HttpStatus.OK);
    }

    @GetMapping("/entries")
    public ResponseEntity<List<TimetableEntry>> getAllTimetableEntries() {
        List<TimetableEntry> entries = timetableService.getAllTimetableEntries();
        return new ResponseEntity<>(entries, HttpStatus.OK);
    }
}
