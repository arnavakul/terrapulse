package com.terrapulse.backend.controller;

import com.terrapulse.backend.model.Target;
import com.terrapulse.backend.service.TargetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/target")
public class TargetController {
    private final TargetService targetService;

    public TargetController(TargetService targetService){
        this.targetService = targetService;
    }

    @PostMapping
    public Target createTarget(@RequestBody Target target){
        return targetService.createTarget(target);
    }

    @GetMapping
    public List<Target> getAllTarget(){
        return targetService.getAllTarget();
    }

    @GetMapping("/{id}")
    public Target getTarget(@PathVariable Long id){
        return targetService.getTargetById(id);
    }

    @PutMapping("/{id}")
    public Target updateTarget(
            @PathVariable Long id,
            @RequestBody Target target
    ){
        return targetService.updateTarget(id, target);
    }

    @DeleteMapping("/{id}")
    public void deleteTarget(@PathVariable Long id){
        targetService.deleteTarget(id);
    }

}
