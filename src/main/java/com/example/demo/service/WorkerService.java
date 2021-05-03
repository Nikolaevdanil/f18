package com.example.demo.service;

import com.example.demo.models.Manufacture;
import com.example.demo.models.Worker;
import com.example.demo.repozitory.WorkerRepozitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WorkerService implements WorkerServiceInterface{
    @Autowired
    private WorkerRepozitory workerRepozitory;



    @Override
    public void addWorker(Worker worker) {
        workerRepozitory.save(worker);
    }

    @Override
    public List<Worker> getAllWorker() {
        List<Worker> workers = workerRepozitory.findAll();
        return workers;
    }

    @Override
    public List<Worker> filterByFirstName() {
        return workerRepozitory.findByOrderByFirstName();
    }

    @Override
    public List<Worker> filterByLastName() {
        return workerRepozitory.findByOrderByLastName();
    }

    @Override
    public List<Worker> filterByMiddleName() {
        return workerRepozitory.findByOrderByMiddleName();
    }

    @Override
    public List<Worker> filterByWorId() {
        return workerRepozitory.findByOrderById();
    }


    @Override
    public Worker getWorkerById(Long id) {
        return workerRepozitory.getOne(id);
    }

    @Override
    public void deleteWorkerById(Long id) {
        workerRepozitory.deleteById(id);
    }

    @Override
    public void deleteAllWorkers() {
        workerRepozitory.deleteAll();
    }

    @Override
    public Manufacture getManufactureByWorker(Long id) {
        return workerRepozitory.findById(id).orElseThrow(() ->
                new IllegalStateException("Worker with this id not found")).getManufacture();
    }

    @Override
    public Long getWorkerId(String firstName) {
        return workerRepozitory.findByFirstName(firstName).getId();
    }

    @Override
    public String printWorkers(List<Worker> list) {
        String buff ="";
        int i = 0;
        buff += "<h>Workers</h><br/>";
        for (Worker item: list) {
            buff += "<tr><td>"+item.getId()+" - "+ item.getFirstName()+"  " + item.getLastName() + "  " + item.getMiddleName()+"<br/></td></tr>";
            i++;
        }
        if (i == 0)
        {
            buff+= "<tr><td>Worker list is empty</td></tr>";
        }
        return buff;
    }
}
