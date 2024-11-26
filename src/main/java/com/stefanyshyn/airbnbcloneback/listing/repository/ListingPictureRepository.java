package com.stefanyshyn.airbnbcloneback.listing.repository;

import com.stefanyshyn.airbnbcloneback.listing.domain.ListingPicture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingPictureRepository extends JpaRepository<ListingPicture, Long> {
}
