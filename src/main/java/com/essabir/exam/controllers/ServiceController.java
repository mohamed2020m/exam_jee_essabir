package com.essabir.exam.controllers;

import com.essabir.exam.entities.Service;
import com.essabir.exam.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/services")
@CrossOrigin
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @GetMapping
    public List<Service> findAll(){
        return serviceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Service service = serviceService.findById(id);

        if(service == null) {
            return new ResponseEntity<Object>("Service with ID " + id + " not found", HttpStatus.BAD_REQUEST);
        }
        else {
            return ResponseEntity.ok(service);
        }
    }

    @PostMapping
    public Service createTest(@RequestBody Service test) {
        test.setId(0L);
        return serviceService.create(test);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTest(@PathVariable Long id, @RequestBody Service test) {
        if(serviceService.findById(id) == null) {
            return new ResponseEntity<Object>("Service with ID " + id + " not found", HttpStatus.BAD_REQUEST);
        }
        else {
//            test.setId(id);
            serviceService.update(test);
            return new ResponseEntity<>("{\"message\": \"UPDATE AVEC SUCCES\"}", HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTest(@PathVariable Long id){
        Service test = serviceService.findById(id);
        if(test == null) {
            return new ResponseEntity<Object>("Service with ID " + id + " not found", HttpStatus.BAD_REQUEST);
        }
        else {
            serviceService.delete(test);
            return ResponseEntity.ok("Service has been deleted");
        }
    }


}
