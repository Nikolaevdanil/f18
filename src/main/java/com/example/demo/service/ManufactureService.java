package com.example.demo.service;

import com.example.demo.models.Manufacture;
import com.example.demo.repozitory.ManufactureRepozitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManufactureService implements ManufactureServiceInterface{
    @Autowired
    private ManufactureRepozitory manufactureRepozitory;

    @Override
    public void addManufacture(Manufacture manufacture) {
        manufactureRepozitory.save(manufacture);
    }

    @Override
    public List<Manufacture> getAllManufacture() {
        List<Manufacture> manufactures = manufactureRepozitory.findAll();
        return manufactures;
    }

    @Override
    public List<Manufacture> filterByName() {
        return manufactureRepozitory.findByOrderByName();
    }

    @Override
    public List<Manufacture> filterByAddress() {
        return manufactureRepozitory.findByOrderByAddress();
    }

    @Override
    public List<Manufacture> filterByManId() {
        return manufactureRepozitory.findByOrderById();
    }

    @Override
    public Manufacture getManufactureById(Long id) {
        return manufactureRepozitory.getOne(id);
    }

    @Override
    public void deleteManufactureById(Long id) {
        try {
            manufactureRepozitory.deleteById(id);
        } catch (Exception e) {
            throw new IllegalStateException("Manufacture has linked workers! You cant remove it!");
        }
    }

    @Override
    public void deleteAllManufactures() {
        manufactureRepozitory.deleteAll();
    }


    @Override
    public Long getManufactureId(String name) {
        return manufactureRepozitory.findByName(name).getId();
    }

    @Override
    public String printManufactures(List<Manufacture> list) {
        String buff = "<h>Manufactures</h><br/>";
        int i = 0;
        for (Manufacture item: list) {
            buff += "<tr><td>"+item.getId()+" - " + item.getName() + "  " + item.getAddress() + "<br/></td></tr>";
            i++;
        }
        if (i == 0)
        {
            buff+= "<tr><td>Manufacture list is empty</td></tr>";
        }
        i = 0;
        return buff;
    }
}
