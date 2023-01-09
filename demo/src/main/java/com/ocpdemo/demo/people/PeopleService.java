package com.ocpdemo.demo.people;

import com.ocpdemo.demo.Exeception.ApiRequestException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeopleService {

    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository){
        this.peopleRepository = peopleRepository;
    }

    public List<People> getPeole() {
        return peopleRepository.findAll();
    }

    public People addPeople(People people) {
        Optional<People> PeopleOptional = peopleRepository.findPeopleByName(people.getName());

        if(PeopleOptional.isPresent()){
            throw new ApiRequestException("People already exists");
        }
        else{
           return peopleRepository.save(people);
        }

    }

    @Transactional
    public void updatePeople(Long Id,String name, String email, String place) {
        People people = peopleRepository.findById(Id)
        .orElseThrow(() -> new ApiRequestException("People not found"));

        if(name != null && name.length() > 0 && !name.equals(people.getName())){
            people.setName(name);
        }
        else if(email != null && email.length() > 0 && !email.equals(people.getEmail())){
            people.setEmail(email);
        }
        else if(place != null && place.length() > 0 && !place.equals(people.getPlace())){
            people.setPlace(place);
        }
        else {
            throw new ApiRequestException("No changes made");
        }
        peopleRepository.save(people);
    
    }

    public void deletePeople(Long id) {
        boolean exist = peopleRepository.existsById(id);
        if(!exist){
            throw new ApiRequestException("People not found");
        }

        peopleRepository.deleteById(id);
    }
}
