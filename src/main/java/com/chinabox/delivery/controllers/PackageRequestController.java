package com.chinabox.delivery.controllers;

import com.chinabox.delivery.dao.PackageRequestRepository;
import com.chinabox.delivery.model.PackageRequest;
import com.chinabox.delivery.service.RestControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityExistsException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("rest/package")
public class PackageRequestController {

    @Autowired
    RestControllerService restControllerService;

    @Autowired
    PackageRequestRepository packageRequestRepository;


    @PostMapping(value = "addPackageRequest")
    public void addPackageRequest(@RequestBody PackageRequest packageRequest) {
        if (packageRequestRepository.findByTrackCode(packageRequest.getTrackCode()) != null) {
            throw new EntityExistsException("Track code already exists");
        } else {
            packageRequest.setUser(restControllerService.requestUser());
            packageRequest.setCreatedDate(LocalDateTime.now());
            packageRequestRepository.save(packageRequest);
        }

    }

    @GetMapping(value = "getAllRequests")
    public List<PackageRequest> getPackageRequestByUserId() {
        List<PackageRequest> packageRequests = packageRequestRepository.findByUser(restControllerService.requestUser());
        packageRequests.removeIf(pr -> pr.getPackageRequestClose() != null
                | pr.getChinaWarehouseArrivedDate() != null
                | pr.getChinaWarehouseSentDate() != null);
        packageRequests.sort(Comparator.comparingLong(PackageRequest::getId).reversed());

        return packageRequests;
    }

    @GetMapping(value = "getRequestHistory")
    public List<PackageRequest> getClosedPackageRequests() {
        List<PackageRequest> packageRequests = packageRequestRepository.findByUser(restControllerService.requestUser());
        packageRequests.removeIf(pr -> pr.getPackageRequestClose() == null);
        packageRequests.sort(Comparator.comparing(PackageRequest::getPackageRequestClose).reversed());
        return packageRequests;
    }

    @GetMapping(value = "myWarehouse")
    public List<PackageRequest> getWarehousePackages() {
        List<PackageRequest> packageRequests = packageRequestRepository.findByUser(restControllerService.requestUser());
        packageRequests.removeIf(pr -> pr.getPackageRequestClose() != null
                | pr.getChinaWarehouseArrivedDate() == null);
        packageRequests.sort(Comparator.comparing(PackageRequest::getChinaWarehouseArrivedDate));
        return packageRequests;
    }

    @DeleteMapping(value = "deletePackageRequest")
    public void deletePackageRequestById(@RequestParam Long id) {
        if (restControllerService.requestUser().getEmail()
            .equals(packageRequestRepository.findById(id).get().getUser().getEmail())) {
            packageRequestRepository.deleteById(id);
        }
    }

}
