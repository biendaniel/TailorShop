package dbien.demo.controller;

import dbien.demo.domain.TextileType;
import dbien.demo.repository.TextileTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/textiles")
public class TextileTypeController {

    @Autowired
//    TextileTypeService textileTypeRepository;
    TextileTypeRepository textileTypeRepository;

    @RequestMapping(method = RequestMethod.POST)
    public void setTextileType(@RequestBody TextileType textileType) {
        TextileType textileType1 = new TextileType();
        textileType1.setName("bawole");
        textileType1.setNatural(true);
        textileTypeRepository.save(textileType);
    }

    @RequestMapping("/{id}")
    public Optional<TextileType> getTextileType (@PathVariable("id") Integer id) {
        return  textileTypeRepository.findById(id);
    }

    @RequestMapping("")
    public Iterable<TextileType> getTextileTypeList() {
        return textileTypeRepository.findAll();
    }
}

