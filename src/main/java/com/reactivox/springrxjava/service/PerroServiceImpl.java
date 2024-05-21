package com.reactivox.springrxjava.service;

import com.reactivox.springrxjava.model.Perro;
import com.reactivox.springrxjava.model.dto.PerroDto;
import com.reactivox.springrxjava.repository.PerroRepository;
import com.reactivox.springrxjava.utils.MapperClass;
import com.reactivox.springrxjava.utils.exceptions.ErrorHandlerException;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.schedulers.Schedulers;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class PerroServiceImpl implements PerroService{

    private final PerroRepository repository;
    private final MapperClass<Perro,PerroDto> mapper;
    @Override
    public Completable saveService(PerroDto perroDto) {
        return Completable.fromCallable(() ->
                repository.save(mapper.dtoToEntity(perroDto, Perro.class))
        ).subscribeOn(Schedulers.io());
    }

    @Override
    public Completable deleteService(Integer id) {
        return findByIdService(id)
                .flatMapCompletable(perroDto ->
                        Completable.fromAction(() ->
                            repository.delete(mapper.dtoToEntity(perroDto, Perro.class))))
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Completable updateService(PerroDto perroDto, Integer id) {
        return findByIdService(id)
                .flatMapCompletable(perroDtoFound ->
                        Completable.fromAction(() -> {
                            perroDto.setId(id);
                            repository.save(mapper.dtoToEntity(perroDto, Perro.class));
                        }
                )).subscribeOn(Schedulers.io());
    }
    @Override
    public Flowable<PerroDto> findAllService() {
        return Flowable.fromIterable(() -> repository.findAll()
                .stream()
                .map(perro -> mapper.entityToDto(perro, PerroDto.class))
                .iterator())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Maybe<PerroDto> findByIdService(Integer id) {
        return Maybe.fromCallable(() -> repository.findById(id)
                .orElseThrow(() -> new ErrorHandlerException("Recurso no existe", HttpStatus.NOT_FOUND)))
                .flatMap(perro -> Maybe.just(mapper.entityToDto(perro, PerroDto.class)))
                .subscribeOn(Schedulers.io());
    }
}
