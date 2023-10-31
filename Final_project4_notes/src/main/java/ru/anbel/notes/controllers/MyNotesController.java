package ru.anbel.notes.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.anbel.notes.dao.MyNotesDAO;
import ru.anbel.notes.models.MyNote;

// Прототип контроллера
@Controller
@RequestMapping("/notes")
public class MyNotesController {

    private final MyNotesDAO myNotesDAO;

    @Autowired
    public MyNotesController(MyNotesDAO myNotesDAO) {
        this.myNotesDAO = myNotesDAO;
    }

    @GetMapping()
    public String index(Model model) {
        // Получаем все заметки из DAO и передаём их на веб-страницу полного списка
        model.addAttribute("notes",myNotesDAO.index());
        return "notes/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        // Получаем одну заметку по id из DAO и передаём её на веб-страницу просмотра
        model.addAttribute("note", myNotesDAO.show(id));
        return "notes/show";
    }

    @GetMapping("/new")
    public String newNote(Model model) {
        // Создаем новую заметку
        model.addAttribute("myNote", new MyNote());
        return "notes/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("myNote") MyNote myNote) {
        // Сохраняем новую заметку и отображаем веб-страницу полного списка
        myNotesDAO.save(myNote);
        return "redirect:/notes";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        // Получаем одну заметку по id из DAO и передаём её на веб-страницу изменения
        model.addAttribute("myNote",myNotesDAO.show(id));
        return "notes/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("myNote") MyNote myNote, @PathVariable("id") int id) {
        // Заменяем заметку со старым id новой
        myNotesDAO.update(id, myNote);
        return "redirect:/notes";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        // Удаляем заметку по id
        myNotesDAO.delete(id);
        return "redirect:/notes";
    }

    @GetMapping("/back")
    public String redirect() {
        return "redirect:/notes";
    }

}
