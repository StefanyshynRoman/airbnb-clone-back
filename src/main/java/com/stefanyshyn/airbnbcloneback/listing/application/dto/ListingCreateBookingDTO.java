package com.stefanyshyn.airbnbcloneback.listing.application.dto;

import com.stefanyshyn.airbnbcloneback.listing.application.dto.vo.PriceVO;

import java.util.UUID;

public record ListingCreateBookingDTO(
        UUID listingPublicId, PriceVO price) {
}