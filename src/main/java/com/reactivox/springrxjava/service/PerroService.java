package com.reactivox.springrxjava.service;

import com.reactivox.springrxjava.model.dto.PerroDto;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.Single;

public interface PerroService {
    Completable saveService(PerroDto perroDto);
    Completable deleteService(Integer id);
    Completable updateService(PerroDto perroDto, Integer id);
    Flowable<PerroDto>findAllService();
    Maybe<PerroDto> findByIdService(Integer id);

}
