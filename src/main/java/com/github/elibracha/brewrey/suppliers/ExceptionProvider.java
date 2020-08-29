package com.github.elibracha.brewrey.suppliers;

import com.github.elibracha.brewrey.domain.Beer;

import javax.persistence.EntityNotFoundException;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ExceptionProvider {
    public final static Supplier<EntityNotFoundException> ENTITY_NOT_FOUND_ERROR_SUPPLER =
            () -> new EntityNotFoundException("Beer not found");

    public final static Consumer<Beer> UPC_FOUND_ERROR_CONSUMER =
            beer -> {
                throw new EntityNotFoundException("Upc already exist");
            };
}
