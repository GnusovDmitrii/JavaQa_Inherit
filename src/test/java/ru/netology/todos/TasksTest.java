package ru.netology.todos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TasksTest {

    @Test
    public void testSimpleTaskMatches() {
        SimpleTask task = new SimpleTask(1, "Купить молоко");
        assertTrue(task.matches("молоко"));
        assertFalse(task.matches("хлеб"));
    }

    @Test
    public void testEpicMatches() {
        String[] subtasks = {"Купить хлеб", "Позвонить маме", "Сделать отчёт"};
        Epic epic = new Epic(2, subtasks);
        assertTrue(epic.matches("отчёт"));
        assertTrue(epic.matches("хлеб"));
        assertFalse(epic.matches("кофе"));
    }

    @Test
    public void testMeetingMatches() {
        Meeting meeting = new Meeting(
                3,
                "Совещание по проекту",
                "Проект X",
                "Завтра в 14:00"
        );
        assertTrue(meeting.matches("проект"));
        assertTrue(meeting.matches("X"));
        assertFalse(meeting.matches("конференция"));
    }

    @Test
    public void testMatchesCaseInsensitive() {
        SimpleTask task = new SimpleTask(1, "Купить МОЛОКО");
        assertTrue(task.matches("молоко"));  // регистронезависимо

        Meeting meeting = new Meeting(3, "СОВЕЩАНИЕ", "ПРОЕКТ", "Время");
        assertTrue(meeting.matches("проект"));  // регистронезависимо
    }
}
