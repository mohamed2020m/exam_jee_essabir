package com.essabir.exam.services;

import com.essabir.exam.dao.IDoa;
import com.essabir.exam.entities.Service;
import com.essabir.exam.repositories.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;


@org.springframework.stereotype.Service
public class ServiceService implements IDoa<Service> {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public Service create(Service o) {
        return serviceRepository.save(o);
    }
    @Override
    public boolean delete(Service o) {
        try {
            serviceRepository.delete(o);
            return true;
        }
        catch(Exception ex) {
            return false;
        }
    }

    @Override
    public Service update(Service o) {
        return serviceRepository.save(o);
    }

    @Override
    public List<Service> findAll() {
        return serviceRepository.findAll();
    }

    @Override
    public Service findById(Long id) {
        Optional<Service> optionalService = serviceRepository.findById(id);

        if (optionalService.isPresent()) {
            return optionalService.get();

        }
        return null;
    }

    public void findCollaborateurByService(Service s){

    }
}
