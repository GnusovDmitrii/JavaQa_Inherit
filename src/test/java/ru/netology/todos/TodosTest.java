package ru.netology.todos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TodosTest {

    @Test
    public void shouldAddTasks() {
        Todos todos = new Todos();
        SimpleTask simple = new SimpleTask(1, "Задача 1");
        Epic epic = new Epic(2, new String[]{"Подзадача 1"});
        Meeting meeting = new Meeting(3, "Тема", "Проект", "Время");

        todos.add(simple);
        todos.add(epic);
        todos.add(meeting);

        Task[] all = todos.findAll();
        assertEquals(3, all.length);
        assertEquals(simple, all[0]);
        assertEquals(epic, all[1]);
        assertEquals(meeting, all[2]);
    }

    @Test
    public void shouldSearchByQuery() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Купить продукты"));
        todos.add(new Epic(2, new String[]{"Хлеб", "Молоко", "Сыр"}));
        todos.add(new Meeting(3, "Совещание по проекту", "Проект X", "Завтра"));

        Task[] found = todos.search("проект");
        assertEquals(1, found.length);
        assertTrue(found[0] instanceof Meeting);
        assertEquals(3, found[0].getId());
    }


    @Test
    public void shouldFindMultipleMatches() {
        Todos todos = new Todos();
        SimpleTask task1 = new SimpleTask(1, "Отчёт по проекту");
        Meeting meeting2 = new Meeting(2, "Обсуждение проекта", "Проект Y", "Сегодня");
        Epic epic3 = new Epic(3, new String[]{"Задача по проекту", "Другая задача"});


        todos.add(task1);
        todos.add(meeting2);
        todos.add(epic3);

        Task[] expected = new Task[]{task1, meeting2, epic3};  // Ожидаемый порядок
        Task[] found = todos.search("проект");


        assertArrayEquals(expected, found);  // Сравниваем массивы целиком
    }

    @Test
    public void shouldReturnEmptyArrayIfNoMatches() {
        Todos todos = new Todos();
        todos.add(new SimpleTask(1, "Покупка хлеба"));
        todos.add(new Epic(2, new String[]{"Уборка", "Стирка"}));

        Task[] found = todos.search("проект");
        assertEquals(0, found.length);
    }

    @Test
    public void shouldHandleEmptyTaskList() {
        Todos todos = new Todos();
        Task[] found = todos.search("что-то");  // Поиск в пустом списке
        assertEquals(0, found.length);
    }
}
