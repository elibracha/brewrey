package com.github.elibracha.brewrey.web.controllers;

import com.github.elibracha.brewrey.services.BeerService;
import com.github.elibracha.brewrey.web.dtos.BeerDto;
import com.github.elibracha.brewrey.web.dtos.BeerPagedList;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/beer")
@AllArgsConstructor
public class BeerController {

    private final BeerService beerService;

    @GetMapping
    public ResponseEntity<BeerPagedList> getBeer(@RequestParam(required = false) Optional<Integer> page,
                                                 @RequestParam(required = false) Optional<Integer> size) {
        val beers = beerService.getBeers(page.orElse(0), size.orElse(10));
        val beerPagedList = new BeerPagedList(beers);
        return new ResponseEntity<>(beerPagedList, HttpStatus.OK);
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId) {
        return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> creatBeer(@Valid @RequestBody BeerDto beer) {
        val id = beerService.createBeer(beer);
        val headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + id);
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<Void> updateBeer(@PathVariable UUID beerId, @Valid @RequestBody BeerDto beer) {
        val id = beerService.updateBeer(beerId, beer);
        val headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + id);
        return new ResponseEntity<>(headers, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    public ResponseEntity<Void> deleteBeer(@PathVariable UUID beerId) {
        beerService.deleteBeer(beerId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
