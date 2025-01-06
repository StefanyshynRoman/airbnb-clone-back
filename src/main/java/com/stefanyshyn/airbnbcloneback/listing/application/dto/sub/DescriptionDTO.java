package com.stefanyshyn.airbnbcloneback.listing.application.dto.sub;

import com.stefanyshyn.airbnbcloneback.listing.application.dto.vo.DescriptionVO;
import com.stefanyshyn.airbnbcloneback.listing.application.dto.vo.TitleVO;
import jakarta.validation.constraints.NotNull;

public record DescriptionDTO(
        @NotNull TitleVO title,
        @NotNull DescriptionVO description
) {
}