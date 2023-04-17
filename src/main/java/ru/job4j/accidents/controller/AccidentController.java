package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

/**
 * 3. Мидл
 * 3.4. Spring
 * 3.4.2. MVC
 * 3. @ModelAttribute. Создание инцидента. [#261013]
 *
 * @author Dmitry Stepanov, user Dima_Nout
 * @since 16.04.2023
 */
@Controller
@AllArgsConstructor
@RequestMapping("/accidents")
public class AccidentController {
    private final AccidentService accidentService;

    @GetMapping("/list")
    public String getAllAccidents(Model model) {
        model.addAttribute("user", "Dmitry");
        model.addAttribute("accidents", accidentService.findAll());
        return "accidents/list";
    }

    @GetMapping("/create")
    public String getCreateAccident(Model model) {
        model.addAttribute("user", "Dmitry");
        return "accidents/create";
    }

    @PostMapping("/saveAccident")
    public String saveAccident(@ModelAttribute Accident accident) {
        accidentService.create(accident);
        return "redirect:/accidents/list";
    }

    @GetMapping("/editAccident")
    public String getEditAccident(@RequestParam("id") int id, Model model) {
        var accidentOptional = accidentService.findById(id);
        if (accidentOptional.isEmpty()) {
            model.addAttribute("message",
                    "Accident by ID: " + id + ",  not found");
            return "errors/404";
        }
        model.addAttribute("accident", accidentOptional.get());
        model.addAttribute("user", "Dima");
        return "accidents/edit";
    }

    @PostMapping("/editAccident")
    public String editAccident(@ModelAttribute Accident accident) {
        accidentService.update(accident);
        return "redirect:/accidents/list";
    }

    @GetMapping("/delete")
    public String deleteAccident(@RequestParam("id") int id, Model model) {
        var result = accidentService.delete(id);
        if (!result) {
            model.addAttribute("message",
                    "Accident by ID: " + id + ",  not found");
            return "errors/404";
        }
        return "redirect:/accidents/list";
    }
}