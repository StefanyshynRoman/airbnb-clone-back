package com.stefanyshyn.airbnbcloneback.booking.mapper;

import com.stefanyshyn.airbnbcloneback.booking.application.dto.BookedDateDTO;
import com.stefanyshyn.airbnbcloneback.booking.application.dto.NewBookingDTO;
import com.stefanyshyn.airbnbcloneback.booking.domain.Booking;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface BookingMapper {
    Booking newBookingToBooking(NewBookingDTO newBookingDTO);
    BookedDateDTO bookingToCheckAvailability(Booking booking);
}
