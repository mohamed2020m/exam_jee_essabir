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
import java.util.Date;
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
            temp.setPrenom(t.getPrenom());
            temp.setService(t.getService());
            temp.setDateNaissance(t.getDateNaissance());
//            temp.setChef(t.getChef());
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

//    @GetMapping("/byServices/{id}")
//    public ResponseEntity<Object> getColab(@PathVariable Long id) {
//        Service service = serviceService.findById(id);
//
//        List<Employe> employeList = employeEmploye.findAll();
//        List<Employe> list_colaborateur = new ArrayList<>();
//        Employe chef = null;
//
//        // getting collaborateur
//        for (Employe e : employeList) {
//            if (e.getService().getId() == id) {
//                if (chef == null) {
//                    chef = e.getChef();
//                }
//                list_colaborateur.add(e);
//            }
//        }
//
//        // Create an EmployeDTO to represent the response
//        EmployeDTO employeDTO = new EmployeDTO();
//        employeDTO.setService(service);
//
//        if (chef != null) {
//            employeDTO.setChefName(chef.getChef());
//        } else {
//            employeDTO.setChefName(null);
//        }
//
//        employeDTO.setCollaborateurs(list_colaborateur);
//
//        return ResponseEntity.ok().body(employeDTO);
//    }

//    @GetMapping("/byServices/{id}")
//    public ResponseEntity<Object> getColab(@PathVariable Long id) {
//        Service service = serviceService.findById(id);
//        List<Employe> employeList = employeEmploye.findAll();
//        List<EmployeDTO> list_colaborateur = new ArrayList<>();
//        Employe chef = null;
//
//        // Getting collaborators
//        for (Employe e : employeList) {
//            if (e.getService().getId().equals(id)) {
//                if (chef == null) {
//                    chef = e.getChef();
//                }
//                // Create an EmployeDTO for each collaborator
//                EmployeDTO collaborateurDTO = new EmployeDTO();
//                collaborateurDTO.setId(e.getId());
//                collaborateurDTO.setNom(e.getNom());
//                collaborateurDTO.setPrenom(e.getPrenom());
//                collaborateurDTO.setPhoto(e.getId() + "/image");
//                collaborateurDTO.setDateNaissance(e.getDateNaissance());
//                collaborateurDTO.setService(e.getService());
//                // Set other attributes as needed
//
//                list_colaborateur.add(collaborateurDTO);
//            }
//        }
//
//        // Create an EmployeDTO to represent the response
//        EmployeDTO employeDTO = new EmployeDTO();
//        employeDTO.setService(service);
//
//        if (chef != null) {
//            employeDTO.setChefName(chef);
//        } else {
//            employeDTO.setChefName(null);
//        }
//
//        employeDTO.setCollaborateurs(list_colaborateur);
//
//        return ResponseEntity.ok().body(employeDTO);
//    }


    @GetMapping("/byServices/{id}")
    public ResponseEntity<List<EmployeDTO>> getColab(@PathVariable Long id) {
        Service service = serviceService.findById(id);

        if (service == null) {
            return ResponseEntity.notFound().build();
        }

        List<Employe> employeList = employeEmploye.findByService(service);
        List<EmployeDTO> list_colaborateur = new ArrayList<>();
        Employe chef = null;

        for (Employe e : employeList) {
            // Create an EmployeDTO for each collaborator
            EmployeDTO collaborateurDTO = new EmployeDTO();
            collaborateurDTO.setId(e.getId());
            collaborateurDTO.setNom(e.getNom());
            collaborateurDTO.setPrenom(e.getPrenom());
            collaborateurDTO.setPhoto(e.getId() + "/image");
            collaborateurDTO.setDateNaissance(e.getDateNaissance());
            collaborateurDTO.setService(e.getService());

            // Set the new property to identify the chef
            collaborateurDTO.setChef(e.getChef() == null);

            // Add to the list
            list_colaborateur.add(collaborateurDTO);

            // Check if the employe is the chef
            if (collaborateurDTO.isChef()) {
                chef = e;
            }
        }

        // Optionally, set the chef property in each collaborator to false
        if (chef != null) {
            for (EmployeDTO collaborateurDTO : list_colaborateur) {
                collaborateurDTO.setChef(false);
            }
            // Set the chef property in the chef to true
            EmployeDTO chefDTO = list_colaborateur.stream().filter(EmployeDTO::isChef).findFirst().orElse(null);
            if (chefDTO != null) {
                chefDTO.setChef(true);
            }
        }

        // Return the list of EmployeDTO
        return ResponseEntity.ok().body(list_colaborateur);
    }

//    @GetMapping("/chefs/{id}")
//    public ResponseEntity<Object> getAllChefsExceptCurrent(@PathVariable Long id) {
//        List<Employe> employeList = employeEmploye.findAll();
//
//        // Find the current employe
//        Employe currentEmploye = employeList.stream()
//                .filter(e -> e.getId().equals(id))
//                .findFirst()
//                .orElse(null);
//
//        // If the current employe is not found, return an error or handle accordingly
//        if (currentEmploye == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        // Remove the current employe from the list
//        employeList.remove(currentEmploye);
//
//        // Create a list of EmployeDTOs representing the remaining employes
//        List<EmployeDTO> employeDTOs = new ArrayList<>();
//        for (Employe employe : employeList) {
//            EmployeDTO employeDTO = new EmployeDTO();
//            employeDTO.setId(employe.getId());
//            employeDTO.setNom(employe.getNom());
//            employeDTO.setPrenom(employe.getPrenom());
//            // Set other properties as needed
//            employeDTOs.add(employeDTO);
//        }
//
//        return ResponseEntity.ok().body(employeDTOs);
//    }


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
