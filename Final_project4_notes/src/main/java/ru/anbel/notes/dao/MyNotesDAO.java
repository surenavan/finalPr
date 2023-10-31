package ru.anbel.notes.dao;

import org.springframework.stereotype.Component;
import ru.anbel.notes.models.MyNote;

import java.util.ArrayList;
import java.util.List;

// Класс для доступа к заметкам
@Component
public class MyNotesDAO {
    private static int NOTES_COUNT; // Переменная для установки id
    private List<MyNote> notes; // База данных заметок

    public MyNotesDAO() {
        // Создаем базу данных заметок
        notes = new ArrayList<MyNote>();
    }

    public List<MyNote> index() { // Метод, возвращающий все заметки
        return notes;
    }

    public MyNote show(int id) { // Метод возвращает заметку по id или null
        return notes.stream().filter(n -> n.getId() == id).findAny().orElse(null);
    }

    public void save(MyNote webNote) { // Метод сохранения
        webNote.setId(++NOTES_COUNT);
        notes.add(webNote);
    }

    public void update(int id, MyNote updatedNote) { // Метод изменения
        MyNote toUpdateNote = show(id);
        toUpdateNote.setNote(updatedNote.getNote());
    }

    public void delete(int id) { // Метод удаления
        notes.removeIf(n -> n.getId() == id);
    }

}
