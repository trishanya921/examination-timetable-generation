package com.example.timetable.repository;

import com.example.timetable.model.TimetableEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimetableEntryRepository extends JpaRepository<TimetableEntry, Long> {
}
