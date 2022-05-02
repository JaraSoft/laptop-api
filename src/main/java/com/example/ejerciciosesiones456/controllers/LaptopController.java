package com.example.ejerciciosesiones456.controllers;

import com.example.ejerciciosesiones456.entities.Laptop;
import com.example.ejerciciosesiones456.repositories.LaptopRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/laptops")
public class LaptopController {
    LaptopRepository laptopRepository;
    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @GetMapping
    @ApiOperation(value = "Mostrar todas las laptops en la Db", response = Laptop.class, responseContainer = "List")
    public ResponseEntity<List<Laptop>> getAllLaptops() {
        return ResponseEntity.ok(laptopRepository.findAll());
    }

    @PostMapping
    @ApiOperation(value = "Guardar nueva laptop")
    @ApiResponses(value = {@ApiResponse(code = 201, message = "Laptop guardada"),
            @ApiResponse(code = 500, message = "Error guardando laptop")})
    public ResponseEntity<Laptop> saveLaptop(@RequestBody Laptop laptop) {
        return new ResponseEntity<>(laptopRepository.save(laptop), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Encontrar laptop por id", response = Laptop.class)
    @GetMapping("/{id}")
    public ResponseEntity<Laptop> findOneById(@ApiParam("id de laptop tipo long") @PathVariable Long id) {
        Optional<Laptop> laptop = laptopRepository.findById(id);
        return laptop.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @ApiOperation(value = "Actualizar laptop", response = Laptop.class)
    @PutMapping("/{id}")
    public ResponseEntity<Laptop> updateLaptop(@ApiParam("Información para actualizar laptop")@RequestBody Laptop laptop, @ApiParam("Id de laptop a actualizar")@PathVariable Long id) {
        Optional<Laptop> laptopFind = laptopRepository.findById(id);
        if (laptopFind.isPresent()) {
            laptopRepository.save(laptop);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @ApiIgnore
    @DeleteMapping("/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id) {
        Optional<Laptop> laptop = laptopRepository.findById(id);
        if (laptop.isPresent()) {
            laptopRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }
    @ApiIgnore
    @DeleteMapping
    public ResponseEntity<Void> deleteAll(){
    laptopRepository.deleteAll();
    return ResponseEntity.ok().build();
    }
}
