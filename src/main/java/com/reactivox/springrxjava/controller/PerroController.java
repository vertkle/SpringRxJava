package com.reactivox.springrxjava.controller;

import com.reactivox.springrxjava.model.dto.PerroDto;
import com.reactivox.springrxjava.service.PerroServiceImpl;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.schedulers.Schedulers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/perro")
@RequiredArgsConstructor
public class PerroController {
    private final PerroServiceImpl service;

    @GetMapping
    public ResponseEntity<Flowable<PerroDto>>findAll(){
        return new ResponseEntity<>(service.findAllService(), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Completable>create(@Valid @RequestBody PerroDto perroDto){
        return new ResponseEntity<>(service.saveService(perroDto)
                , HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Maybe<PerroDto>>findById(@PathVariable Integer id){
        return new ResponseEntity<>(service.findByIdService(id), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Completable> delete(@PathVariable Integer id){
        return new ResponseEntity<>(service.deleteService(id), HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Completable> update(@Valid @RequestBody PerroDto perroDto, @PathVariable Integer id){
        return new ResponseEntity<>(service.updateService(perroDto, id), HttpStatus.NO_CONTENT);
    }
}
