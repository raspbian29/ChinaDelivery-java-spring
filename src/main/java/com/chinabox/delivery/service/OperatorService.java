package com.chinabox.delivery.service;

import com.chinabox.delivery.dao.PackageRequestRepository;
import com.chinabox.delivery.model.PackageRequest;
import com.chinabox.delivery.request.body.PackageRequestValidationBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class OperatorService {
    
    @Autowired
    RestControllerService restControllerService;
    @Autowired
    PackageRequestRepository packageRequestRepository;

    public void validatePackage(PackageRequestValidationBody body) {

        PackageRequest packageRequest = packageRequestRepository.findById(body.packageRequestId).get();
        packageRequest.setOperator(restControllerService.requestUser());
        packageRequest.setHeight(new BigDecimal(body.height));
        packageRequest.setLength(new BigDecimal(body.length));
        packageRequest.setWidth(new BigDecimal(body.width));
        packageRequest.setWeight(new BigDecimal(body.weight));
        packageRequest.setDeliveryPrice(calculateDeliveryPrice(new BigDecimal(body.weight),
                                                               new BigDecimal(body.height),
                                                               new BigDecimal(body.width),
                                                               new BigDecimal(body.length),
                                                               packageRequest));
        packageRequest.setChinaWarehouseArrivedDate(LocalDateTime.now());
        packageRequestRepository.saveAndFlush(packageRequest);



    }


    public BigDecimal calculateDeliveryPrice(BigDecimal weight, BigDecimal height, BigDecimal width,
                                             BigDecimal length, PackageRequest packageRequest) {
        BigDecimal volumetricWeight = length.multiply(width).multiply(height).divide(BigDecimal.valueOf(5000));
        BigDecimal measuredWeight = weight.multiply(BigDecimal.valueOf(0.001));
        BigDecimal higherPrice = volumetricWeight.max(measuredWeight).multiply(BigDecimal.valueOf(10));
        if (packageRequest.getItemPhoto()){ higherPrice = higherPrice.add(new BigDecimal("1.0"));}
        if (packageRequest.getItemCheck()){ higherPrice = higherPrice.add(new BigDecimal("1.0"));}
        if (packageRequest.getItemRepack()){ higherPrice = higherPrice.add(new BigDecimal("1.0"));}
        if (packageRequest.getItemSplit()){ higherPrice = higherPrice.add(new BigDecimal("1.0"));}
        if (packageRequest.getItemInsurance()){
            higherPrice = higherPrice.add(packageRequest.getPrice().multiply(BigDecimal.valueOf(0.1)));}

        return  higherPrice;
    }


}
