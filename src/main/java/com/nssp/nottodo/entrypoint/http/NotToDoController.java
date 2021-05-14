package com.nssp.nottodo.entrypoint.http;

import com.nssp.nottodo.core.entities.NotToDo;
import com.nssp.nottodo.core.usecase.dto.NotToDoDto;
import com.nssp.nottodo.core.usecase.inbound.IncludeNotToDoInputOutbound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class NotToDoController {
    private IncludeNotToDoInputOutbound inputOutbound;
    @Autowired
    void setInputOutbound(IncludeNotToDoInputOutbound inputOutbound) {
        this.inputOutbound = inputOutbound;
    }

    @GetMapping("/items/{user_id}")
    public List<NotToDoDto> listAll(@RequestBody NotToDoDto notToDoDto, @PathVariable("user_id") Long id ) {
        return this.inputOutbound.listAllByUserId(id);
    }
    @GetMapping("/items/{id}")
    public NotToDoDto get(@PathVariable Long id) {
        return this.inputOutbound.findNotToDoById(id);
    }

    @PostMapping("/items")
    public NotToDoDto post(@RequestBody NotToDoDto notToDoDto) {
        return this.inputOutbound.includeNotToDo(notToDoDto);
    }
    @PutMapping("/items")
    public NotToDoDto update(@RequestBody NotToDoDto notToDoDto) {
        return this.inputOutbound.updateNotToDo(notToDoDto);
    }

}
