package com.hari.sb.httpdemo.controller;

import com.hari.sb.httpdemo.dao.Item;
import com.hari.sb.httpdemo.repo.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    ItemRepo itemRepo;

    @GetMapping("/get")
    public ResponseEntity<?> getAllItems() {
        List<Item> items = itemRepo.findAll();
        return ResponseEntity.ok(items);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveItem(@RequestBody Item item) {
        Item newItem = new Item();
        newItem.setItemName(item.getItemName()); // if we dont do this it always expects ID, createdby, creaetddt all
        Item savedItem = itemRepo.save(newItem);
        return ResponseEntity.ok(savedItem);
    }

    @PutMapping("/update/{itemId}")
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public ResponseEntity<?> updateItem(@PathVariable("itemId") Integer id, @RequestBody Item item) {
        Item existingItem = itemRepo.findById(id).orElseThrow();
        existingItem.setItemId(id);
        existingItem.setItemName(item.getItemName()); // if we dont do this it always expects ID, createdby, creaetddt all
        Item savedItem = itemRepo.save(existingItem);
        return ResponseEntity.ok(savedItem);
    }
}
