package com.stefanyshyn.airbnbcloneback.listing.application.dto;


import com.stefanyshyn.airbnbcloneback.listing.application.dto.sub.PictureDTO;
import com.stefanyshyn.airbnbcloneback.listing.application.dto.vo.PriceVO;
import com.stefanyshyn.airbnbcloneback.listing.domain.BookingCategory;

import java.util.UUID;

public record DisplayCardListingDTO(PriceVO price,
                                    String location,
                                    PictureDTO cover,
                                    BookingCategory bookingCategory,
                                    UUID publicId) {
}