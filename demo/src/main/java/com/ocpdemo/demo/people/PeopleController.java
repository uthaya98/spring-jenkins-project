package com.ocpdemo.demo.people;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/new")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public  PeopleController(PeopleService peopleService){
        this.peopleService = peopleService;
    }

    @GetMapping("")
    public List<People> GetPeople(){
        return peopleService.getPeole();
    }

    @PostMapping("")
    public void AddPeople(@RequestBody People people){
        peopleService.addPeople(people);
    }

    @PutMapping(path = "{Id}")
    public void UpdatePeople(@PathVariable("Id") Long Id,
    @RequestParam (required = false) String name,
    @RequestParam (required = false) String email,
    @RequestParam (required = false) String place
    ){
        peopleService.updatePeople(Id, name, email, place);
    }

    @DeleteMapping(path = "{Id}")
    public void DeletePeople(@PathVariable("Id") Long Id){
        peopleService.deletePeople(Id);
    }

}
