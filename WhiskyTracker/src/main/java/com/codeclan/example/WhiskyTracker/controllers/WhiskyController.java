package com.codeclan.example.WhiskyTracker.controllers;

import com.codeclan.example.WhiskyTracker.models.Distillery;
import com.codeclan.example.WhiskyTracker.models.Whisky;
import com.codeclan.example.WhiskyTracker.repositories.DistilleryRepository;
import com.codeclan.example.WhiskyTracker.repositories.WhiskyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WhiskyController {
    @Autowired
    WhiskyRepository whiskyRepository;
    @Autowired
    DistilleryRepository distilleryRepository;

    @GetMapping(value="/whiskies")
    public ResponseEntity<List<Whisky>> getAllWhiskies(@RequestParam (name = "age", required = false) Integer age,
                                                       @RequestParam(name= "distillery", required = false)String distillery, @RequestParam(name="year", required = false) Integer year){
        if(age != null && distillery != null){
            Distillery foundDistillery = distilleryRepository.findByName(distillery);
            return new ResponseEntity<>(whiskyRepository.findByAgeAndDistillery(age, foundDistillery),
                    HttpStatus.OK);
        }
        else if (age != null) {
            return new ResponseEntity<>(whiskyRepository.findByAge(age), HttpStatus.OK);
        }
        else if (distillery != null){
            Distillery foundDistillery = distilleryRepository.findByName(distillery);
            return new ResponseEntity<>(whiskyRepository.findByDistillery(foundDistillery), HttpStatus.OK);
        }
        else if (year != null){
            return new ResponseEntity<>(whiskyRepository.findByYear(year), HttpStatus.OK);
        }
        return new ResponseEntity<>(whiskyRepository.findAll(), HttpStatus.OK);
    }

}


//  @Autowired
//    PirateRepository pirateRepository;
//
//
//    @GetMapping(value = "/pirates")
//    public ResponseEntity<List<Pirate>> getAllPirates(@RequestParam (name = "age", required = false) Integer age){
//        if (age != null){
//            return new ResponseEntity<>(pirateRepository.findByAgeGreaterThan(age), HttpStatus.OK);
//        }
//        return new ResponseEntity<>(pirateRepository.findAll(), HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/pirates/{id}")
//    public ResponseEntity getPirate(@PathVariable Long id){
//        return new ResponseEntity<>(pirateRepository.findById(id), HttpStatus.OK);
//    }
//
//    @PostMapping(value = "/pirates")
//    public ResponseEntity<Pirate> postPirate(@RequestBody Pirate pirate){
//        pirateRepository.save(pirate);
//        return new ResponseEntity<>(pirate, HttpStatus.CREATED);
//    }