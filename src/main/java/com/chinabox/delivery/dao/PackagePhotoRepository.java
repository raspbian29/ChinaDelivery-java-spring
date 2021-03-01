package com.chinabox.delivery.dao;

import com.chinabox.delivery.model.PackagePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PackagePhotoRepository extends JpaRepository<PackagePhoto, Long> {

    Optional<PackagePhoto> findByName(String name);
    List<PackagePhoto> findByPackageRequestId(Long id);

}
