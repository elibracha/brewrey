package com.github.elibracha.brewrey.web.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.github.elibracha.brewrey.models.Beer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
    static final long serialVersionUID = -5815566940065181210L;

    @Null
    private UUID id;

    @Null
    private Integer version;

    @Null
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime createdDate;

    @Null
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime lastModifiedDate;

    @NotBlank
    private String beerName;

    @NotNull
    private String beerStyle;

    @NotNull
    private String upc;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Positive
    @NotNull
    private BigDecimal price;

    private Integer quantityOnHand;

    public BeerDto(Beer beer) {
        this.id = beer.getId();
        this.version = beer.getVersion().intValue();
        this.createdDate = OffsetDateTime.ofInstant(beer.getCreatedDate().toInstant(), ZoneId.systemDefault());
        this.lastModifiedDate = OffsetDateTime.ofInstant(beer.getLastModifiedDate().toInstant(), ZoneId.systemDefault());
        this.beerName = beer.getBeerName();
        this.beerStyle = beer.getBeerStyle();
        this.price = beer.getPrice();
        this.upc = beer.getUpc();
    }
}
