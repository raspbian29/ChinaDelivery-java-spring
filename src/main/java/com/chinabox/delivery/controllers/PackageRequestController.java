package com.chinabox.delivery.controllers;

import com.chinabox.delivery.dao.PackageRequestRepository;
import com.chinabox.delivery.model.PackageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("rest/package")
public class PackageRequestController {

    @Autowired
    ApplicationController applicationController;

    @Autowired
    @Resource
    PackageRequestRepository packageRequestRepository;


    @PostMapping(value = "addPackageRequest")
    public void addPackageRequest(@RequestBody PackageRequest packageRequest) {
        packageRequest.setUser(applicationController.requestUser());
        packageRequest.setCreatedDate(LocalDateTime.now());
        packageRequestRepository.save(packageRequest);
    }
    @GetMapping(value = "getAllRequests")
    public List<PackageRequest> getPackageRequestByUserId(){
        return packageRequestRepository.findByUser(applicationController.requestUser());
        }
}
