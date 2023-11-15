//package com.essabir.exam.controllers;
//
//import com.essabir.exam.dto.TestDTO;
//import com.essabir.exam.entities.Test;
//import com.essabir.exam.services.TestService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/v1/tests")
//@CrossOrigin
//public class TestController {
//
//    @Autowired
//    private TestService testService;
//
//    @GetMapping
//    public List<TestDTO> findAllTest(){
//        List<Test> tests = testService.findAll();
//        List<TestDTO> reTests = new ArrayList<>();
//        for(Test t : tests){
//            TestDTO temp = new TestDTO();
//            temp.setId(t.getId());
//            temp.setName(t.getName());
//            temp.setDateNaissance(t.getDateNaissance());
//            if(t.getImage() != null){
//                temp.setImageUrl(t.getId() + "/image");
//            }else{
//                temp.setImageUrl(null);
//            }
//
//            reTests.add(temp);
//        }
//        return reTests;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Object> findById(@PathVariable int id) {
//        Test test = testService.findById(id);
//
//        if(test == null) {
//            return new ResponseEntity<Object>("Test with ID " + id + " not found", HttpStatus.BAD_REQUEST);
//        }
//        else {
//            TestDTO testDTO = new TestDTO();
//            testDTO.setId(test.getId());
//            testDTO.setName(test.getName());
//            testDTO.setDateNaissance(test.getDateNaissance());
//            if(test.getImage() != null){
//                testDTO.setImageUrl(test.getId() + "/image");
//            }else{
//                testDTO.setImageUrl(null);
//            }
//            return ResponseEntity.ok(testDTO);
//        }
//    }
//
//    @PostMapping
//    public Test createTest(@RequestBody Test test) {
//        test.setId(0);
//        return testService.create(test);
//    }
//
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Object> updateTest(@PathVariable int id, @RequestBody Test test) {
//        if(testService.findById(id) == null) {
//            return new ResponseEntity<Object>("Test with ID " + id + " not found", HttpStatus.BAD_REQUEST);
//        }
//        else {
////            test.setId(id);
//            testService.update(test);
//            return new ResponseEntity<>("{\"message\": \"UPDATE AVEC SUCCES\"}", HttpStatus.OK);
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> deleteTest(@PathVariable int id){
//        Test test = testService.findById(id);
//        if(test == null) {
//            return new ResponseEntity<Object>("Test with ID " + id + " not found", HttpStatus.BAD_REQUEST);
//        }
//        else {
//            testService.delete(test);
//            return ResponseEntity.ok("Test has been deleted");
//        }
//    }
//
//    @GetMapping("{id}/image")
//    public @ResponseBody ResponseEntity<byte[]> getImage(@PathVariable Integer id) {
//        byte[] img = testService.getImage(id);
//        if(img == null) {
//            return null;
//        }
//        else {
//            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(img);
//        }
//    }
//
//
//    @PostMapping("{id}/image")
//    public ResponseEntity<Object> uploadImage(@PathVariable Integer id, @RequestParam("image") MultipartFile file) {
//        testService.uploadImage(id, file);
//        return ResponseEntity.ok().body("Uploded");
//    }
//
//    @PutMapping("{id}/image")
//    public ResponseEntity<Object> updateImage(@PathVariable Integer id, @RequestParam("image") MultipartFile file) {
//        testService.uploadImage(id, file);
//        return ResponseEntity.ok().body("Image updated!");
//    }
//
//    @DeleteMapping("{id}/image")
//    public ResponseEntity<Object> deleteImage(@PathVariable Integer id){
//        if(testService.deleteImage(id)){
//            return ResponseEntity.ok("Image has been deleted");
//        }
//        return new ResponseEntity<Object>("Image with ID " + id + " not found", HttpStatus.BAD_REQUEST);
//    }
//}
