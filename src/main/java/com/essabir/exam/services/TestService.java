//package com.essabir.exam.services;
//
//import com.essabir.exam.dao.IDoa;
//import com.essabir.exam.entities.Test;
//import com.essabir.exam.repositories.TestRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class TestService implements IDoa<Test> {
//
//    @Autowired
//    private TestRepository testRepository;
//
//    @Override
//    public Test create(Test o) {
//        return testRepository.save(o);
//    }
//    @Override
//    public boolean delete(Test o) {
//        try {
//            testRepository.delete(o);
//            return true;
//        }
//        catch(Exception ex) {
//            return false;
//        }
//    }
//
//    @Override
//    public Test update(Test o) {
//        return testRepository.save(o);
//    }
//
//    @Override
//    public List<Test> findAll() {
//        return testRepository.findAll();
//    }
//
//    @Override
//    public Test findById(Long id) {
//        Optional<Test> optionalTest = testRepository.findById(id);
//
//        if (optionalTest.isPresent()) {
//            return optionalTest.get();
//
//        }
//        return null;
//    }
//
//    public byte[] getImage(Integer id) {
//        Optional<Test> optionalTest = testRepository.findById(id);
//
//        if (optionalTest.isPresent()) {
//            Test test = optionalTest.get();
//            return test.getImage();
//
//        }
//        return null;
//    }
//
//    public Test uploadImage(Integer id, MultipartFile image) {
//        Optional<Test> optionalTest = testRepository.findById(id);
//
//        if (optionalTest.isPresent()) {
//            Test test = optionalTest.get();
//
//            try {
//                // Convert the MultipartFile to a byte array
//                byte[] imageBytes = image.getBytes();
//
//                // Update the user's imageProfile attribute with the byte array
//                test.setImage(imageBytes);
//
//                // Save the updated user entity
//                return testRepository.save(test);
//            } catch (IOException e) {
//                e.printStackTrace(); // Log the exception
//                throw new RuntimeException("Failed to upload image.");
//            }
//        } else {
//            throw new RuntimeException("Test not found with id: " + id);
//        }
//    }
//
//    public boolean deleteImage(Integer id){
//        Optional<Test> optionalTest = testRepository.findById(id);
//        if (optionalTest.isPresent()) {
//            Test test = optionalTest.get();
//            test.setImage(null);
//            testRepository.save(test);
//            return true;
//        }
//        return false;
//    }
//}
