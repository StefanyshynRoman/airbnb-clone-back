package com.stefanyshyn.airbnbcloneback.listing.repository;

import com.stefanyshyn.airbnbcloneback.listing.domain.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListingRepository extends JpaRepository<Listing, Long> {
}
