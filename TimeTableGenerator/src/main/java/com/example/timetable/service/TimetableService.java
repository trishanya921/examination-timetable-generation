package com.example.timetable.service;

import com.example.timetable.model.*;
import com.example.timetable.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class TimetableService {
    @Autowired
    private TimetableEntryRepository timetableEntryRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private ClassroomRepository classroomRepository;

    public void generateTimetable() {
        List<Course> courses = courseRepository.findAll();
        List<Teacher> teachers = teacherRepository.findAll();
        List<Classroom> classrooms = classroomRepository.findAll();

        String[] daysOfWeek = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
        LocalTime startTime = LocalTime.of(9, 0);  // Start time: 9:00 AM
        LocalTime endTime = LocalTime.of(17, 0);   // End time: 5:00 PM
        int duration = 1;  // Duration of each class in hours

        int teacherIndex = 0;
        int classroomIndex = 0;

        for (Course course : courses) {
            for (String day : daysOfWeek) {
                LocalTime currentTime = startTime;

                while (currentTime.plusHours(duration).isBefore(endTime) || currentTime.plusHours(duration).equals(endTime)) {
                    Teacher teacher = teachers.get(teacherIndex % teachers.size());
                    Classroom classroom = classrooms.get(classroomIndex % classrooms.size());

                    TimetableEntry entry = new TimetableEntry();
                    entry.setCourseId(course.getId());
                    entry.setTeacherId(teacher.getId());
                    entry.setClassroomId(classroom.getId());
                    entry.setDayOfWeek(day);
                    entry.setStartTime(currentTime);
                    entry.setEndTime(currentTime.plusHours(duration));

                    timetableEntryRepository.save(entry);

                    currentTime = currentTime.plusHours(duration);

                    // Move to the next teacher and classroom
                    teacherIndex++;
                    classroomIndex++;
                }
            }
        }
    }

    public List<TimetableEntry> getAllTimetableEntries() {
        return timetableEntryRepository.findAll();
    }
}
