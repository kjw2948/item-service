package hello.itemservice.controller;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "/basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable Long itemId, Model model) {
        Item findItem = itemRepository.findById(itemId);
        model.addAttribute("item", findItem);
        return "/basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "/basic/addForm";
    }

    // 폼 데이터 받아오기 --> @RequestParam 으로 필드 각각 받기
    // @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                            @RequestParam int price,
                            @RequestParam int quantity,
                            Model model) {
        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);
        itemRepository.save(item);

        model.addAttribute("item", item);
        return "/basic/item";
    }

    // 폼 데이터 받아오기 --> @ModelAttribute 로 한번에 받아오기
    // 이름 따로 등록 안하면 모델에 저장될 때 클래스의 첫글자 소문자로 변환하여 모델이름으로 저장
    //@PostMapping("/add")
    public String addItemV2(@ModelAttribute Item item, Model model) {
        itemRepository.save(item);
        return "/basic/item";
    }

    /**
     * @ModelAttribute 생략 가능
     * 새로고침 시 get 형식으로 끝나게 하기 --> PRG (Post Redirect Get) --> 아래 return 값은 실제 url 경로임 (resource 경로 x)
     */

    //@PostMapping("/add")
    public String addItemV3(Item item) {
        itemRepository.save(item);
        return "redirect:/basic/items" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV4(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}"; // redirectAttributes 에 있는 값을 사용할 수 있음
        // 그러면 return 에 없는 status 는 --> 쿼리 파라미터로 넘어감 --> ?status=true 이렇게
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item findItem = itemRepository.findById(itemId);
        model.addAttribute("item", findItem);
        return "/basic/editForm";
    }

    @PostMapping("{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }
}
