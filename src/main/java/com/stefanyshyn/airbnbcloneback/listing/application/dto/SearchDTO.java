package com.stefanyshyn.airbnbcloneback.listing.application.dto;

import com.stefanyshyn.airbnbcloneback.booking.application.dto.BookedDateDTO;
import com.stefanyshyn.airbnbcloneback.listing.application.dto.sub.ListingInfoDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

public record SearchDTO(@Valid BookedDateDTO dates,
                        @Valid ListingInfoDTO infos,
                        @NotEmpty String location) {
}