package com.essabir.exam.controllers;

import com.essabir.exam.dto.EmployeDTO;
import com.essabir.exam.entities.Employe;
import com.essabir.exam.entities.Service;
import com.essabir.exam.services.EmployeService;
import com.essabir.exam.services.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employes")
@CrossOrigin
public class EmployeController {
    @Autowired
    private EmployeService employeEmploye;

    @Autowired
    private ServiceService serviceService;

    @GetMapping
    public List<EmployeDTO> findAll(){
//        return employeEmploye.findAll();

        List<Employe> tests = employeEmploye.findAll();
        List<EmployeDTO> reTests = new ArrayList<>();
        for(Employe t : tests){
            EmployeDTO temp = new EmployeDTO();
            temp.setId(t.getId());
            temp.setNom(t.getNom());
            temp.setService(t.getService());
            temp.setDateNaissance(t.getDateNaissance());
            if(t.getPhoto() != null){
                temp.setPhoto(t.getId() + "/image");
            }else{
                temp.setPhoto(null);
            }

            reTests.add(temp);
        }
        return reTests;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Employe service = employeEmploye.findById(id);

        if(service == null) {
            return new ResponseEntity<Object>("Employe with ID " + id + " not found", HttpStatus.BAD_REQUEST);
        }
        else {
            return ResponseEntity.ok(service);
        }
    }

    @PostMapping
    public Employe createTest(@RequestBody Employe test) {
        test.setId(0L);
        return employeEmploye.create(test);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateTest(@PathVariable Long id, @RequestBody Employe test) {
        if(employeEmploye.findById(id) == null) {
            return new ResponseEntity<Object>("Employe with ID " + id + " not found", HttpStatus.BAD_REQUEST);
        }
        else {
//            test.setId(id);
            employeEmploye.update(test);
            return new ResponseEntity<>("{\"message\": \"UPDATE AVEC SUCCES\"}", HttpStatus.OK);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTest(@PathVariable Long id){
        Employe test = employeEmploye.findById(id);
        if(test == null) {
            return new ResponseEntity<Object>("Employe with ID " + id + " not found", HttpStatus.BAD_REQUEST);
        }
        else {
            employeEmploye.delete(test);
            return ResponseEntity.ok("Employe has been deleted");
        }
    }
    @GetMapping("/colab/{id}")
    public ResponseEntity<Object> getColab(@PathVariable Long id){
        Service service = serviceService.findById(id);

        List<Employe> employeList = employeEmploye.findAll();
        List<Employe> list_colaborateur = new ArrayList<>();
        Employe chef = null;

        // getting colaberateur
        for(Employe e : employeList){
            if(e.getService().getId() == id){
                if(chef == null){
                    chef = e.getChef();
                }
                list_colaborateur.add(e);
            }
        }


        String res = "Service: " + service.getNom()      + "\n" +
                "chef de service: " + chef.getNom() + "\n" +
                "Collaborateur: " + list_colaborateur;

        return ResponseEntity.ok().body(res);
    }

    @GetMapping("{id}/image")
    public @ResponseBody ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        byte[] img = employeEmploye.getImage(id);
        if(img == null) {
            return null;
        }
        else {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(img);
        }
    }

    @PostMapping("{id}/image")
    public ResponseEntity<Object> uploadImage(@PathVariable Long id, @RequestParam("image") MultipartFile file) {
        employeEmploye.uploadImage(id, file);
        return ResponseEntity.ok().body("Uploded");
    }

    @PutMapping("{id}/image")
    public ResponseEntity<Object> updateImage(@PathVariable Long id, @RequestParam("image") MultipartFile file) {
        employeEmploye.uploadImage(id, file);
        return ResponseEntity.ok().body("Image updated!");
    }

    @DeleteMapping("{id}/image")
    public ResponseEntity<Object> deleteImage(@PathVariable Long id){
        if(employeEmploye.deleteImage(id)){
            return ResponseEntity.ok("Image has been deleted");
        }
        return new ResponseEntity<Object>("Image with ID " + id + " not found", HttpStatus.BAD_REQUEST);
    }
}
