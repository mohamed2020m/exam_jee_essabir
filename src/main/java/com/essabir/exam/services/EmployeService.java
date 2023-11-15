package com.essabir.exam.services;

import com.essabir.exam.dao.IDoa;
import com.essabir.exam.entities.Employe;
import com.essabir.exam.repositories.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeService implements IDoa<Employe> {

    @Autowired
    private EmployeRepository employeEmploye;

    @Override
    public Employe create(Employe o) {
        return employeEmploye.save(o);
    }
    @Override
    public boolean delete(Employe o) {
        try {
            employeEmploye.delete(o);
            return true;
        }
        catch(Exception ex) {
            return false;
        }
    }

    @Override
    public Employe update(Employe o) {
        return employeEmploye.save(o);
    }

    @Override
    public List<Employe> findAll() {
        return employeEmploye.findAll();
    }

    @Override
    public Employe findById(Long id) {
        Optional<Employe> optionalEmploye = employeEmploye.findById(id);

        if (optionalEmploye.isPresent()) {
            return optionalEmploye.get();

        }
        return null;
    }

    public byte[] getImage(Long id) {
        Optional<Employe> optionalTest = employeEmploye.findById(id);

        if (optionalTest.isPresent()) {
            Employe test = optionalTest.get();
            return test.getPhoto();

        }
        return null;
    }

    public Employe uploadImage(Long id, MultipartFile image) {
        Optional<Employe> optionalTest = employeEmploye.findById(id);

        if (optionalTest.isPresent()) {
            Employe test = optionalTest.get();

            try {
                // Convert the MultipartFile to a byte array
                byte[] imageBytes = image.getBytes();

                // Update the user's imageProfile attribute with the byte array
                test.setPhoto(imageBytes);

                // Save the updated user entity
                return employeEmploye.save(test);
            } catch (IOException e) {
                e.printStackTrace(); // Log the exception
                throw new RuntimeException("Failed to upload image.");
            }
        } else {
            throw new RuntimeException("Test not found with id: " + id);
        }
    }

    public boolean deleteImage(Long id){
        Optional<Employe> optionalTest = employeEmploye.findById(id);
        if (optionalTest.isPresent()) {
            Employe test = optionalTest.get();
            test.setPhoto(null);
            employeEmploye.save(test);
            return true;
        }
        return false;
    }
}
