package com.sysoiev.app.controller;

import com.sysoiev.app.model.Specialty;
import com.sysoiev.app.repository.SpecialtiesRepository;
import com.sysoiev.app.repository.jdbc.JdbcSpecialtyRepository;

import java.util.List;

public class SpecialtyController {
    private SpecialtiesRepository specialtyRepository = new JdbcSpecialtyRepository();


    public List<Specialty> printAll() {
        return specialtyRepository.getAll();
    }

    public void saveSpecialty(Specialty newSpecialty) {
        specialtyRepository.save(newSpecialty);
    }

    public void deleteSpecialty(Long index) {
        specialtyRepository.deleteById(index);
    }

    public void updateSpecialty(Specialty updateSpecialty) {
        specialtyRepository.update(updateSpecialty);

    }

    public Specialty getValueByIndex(Long index){
        return specialtyRepository.getById(index);
    }

}
