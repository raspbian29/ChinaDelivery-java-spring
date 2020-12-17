package com.chinabox.delivery.controllers;

import com.chinabox.delivery.dao.PackageRequestRepository;
import com.chinabox.delivery.model.PackageRequest;
import com.chinabox.delivery.service.RestControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("rest/package")
public class PackageRequestController {

    @Autowired
    RestControllerService restControllerService;

    @Autowired
    PackageRequestRepository packageRequestRepository;


    @PostMapping(value = "addPackageRequest")
    public void addPackageRequest(@RequestBody PackageRequest packageRequest) {
        packageRequest.setUser(restControllerService.requestUser());
        packageRequest.setCreatedDate(LocalDateTime.now());
        packageRequestRepository.save(packageRequest);
    }

    @GetMapping(value = "getAllRequests")
    public List<PackageRequest> getPackageRequestByUserId() {
        List<PackageRequest> packageRequests = packageRequestRepository.findByUser(restControllerService.requestUser());
        packageRequests.removeIf(pr -> pr.getPackageRequestClose() != null);
        packageRequests.sort(Comparator.comparingLong(PackageRequest::getId).reversed());

        return packageRequests;
    }

    @GetMapping(value = "getRequestHistory")
    public List<PackageRequest> getClosedPackageRequests() {
        List<PackageRequest> packageRequests = packageRequestRepository.findByUser(restControllerService.requestUser());
        packageRequests.removeIf(pr -> pr.getPackageRequestClose() == null);
        packageRequests.sort((a, b) -> {
            if (a.getPackageRequestClose() == null || b.getPackageRequestClose() == null)
            return 0;
            return a.getPackageRequestClose().compareTo(b.getPackageRequestClose());
        });
        return packageRequests;
    }
}
