package hello.review.controller;

import hello.itemservice.domain.item.ItemDto;
import hello.review.domain.Item;
import hello.review.repository.Repository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/basic/items")
public class BasicItemController {

    private final Repository repository;
    /*
    @Autowired
    public BasicItemController(Repository repository) {
        this.repository = repository;
    }
     */

    @GetMapping
    public String items(Model model) {
        List<Item> items = repository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item findItem = repository.findById(itemId);
        model.addAttribute("item", findItem);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    } // 상품등록폼에 들어가는 메서드

    @PostMapping("/add")
    public String addItem(Item item) {
        repository.save(item);
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = repository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        repository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }
}
