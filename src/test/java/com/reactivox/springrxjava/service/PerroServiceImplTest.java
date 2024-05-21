package com.reactivox.springrxjava.service;

import com.reactivox.springrxjava.model.Perro;
import com.reactivox.springrxjava.model.dto.PerroDto;
import com.reactivox.springrxjava.repository.PerroRepository;
import com.reactivox.springrxjava.utils.MapperClass;
import com.reactivox.springrxjava.utils.exceptions.ErrorHandlerException;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.observers.TestObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.TestScheduler;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)

public class PerroServiceImplTest {
    @Mock
    private PerroRepository repository;

    @Mock
    private MapperClass<Perro, PerroDto> mapper;

    @InjectMocks
    private PerroServiceImpl service;

    @BeforeEach
    void setUp() {
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
    }

    @Test
    void findByIdTest(){
        Integer id = 1;
        Perro perro = new Perro();
        perro.setId(id);
        PerroDto perroDto = new PerroDto();

        when(repository.findById(id)).thenReturn(Optional.of(perro));
        when(mapper.entityToDto(any(Perro.class), eq(PerroDto.class))).thenReturn(perroDto);

        Maybe<PerroDto> result = service.findByIdService(id);

        result.test()
                .assertResult(perroDto)
                .assertNoErrors()
                .assertComplete();

        verify(repository, times(1)).findById(id);
    }
    @Test
    void findByIdTest_error(){
        Integer id = 1;
        when(repository.findById(id)).thenReturn(Optional.empty());
        Maybe<PerroDto> result = service.findByIdService(id);
        result.test()
                .assertError(ErrorHandlerException.class);
        verify(repository, times(1)).findById(id);
    }
    @Test
    void findAllTest(){
        Perro perro1 = new Perro();
        perro1.setId(1);
        Perro perro2 = new Perro();
        perro2.setId(1);
        List<Perro>listPerros = Arrays.asList(perro1, perro2);

        when(repository.findAll()).thenReturn(listPerros);
        when(mapper.entityToDto(any(), eq(PerroDto.class)))
                .thenAnswer(invocation -> {
                    Perro perro = invocation.getArgument(0);
                    PerroDto p1 = new PerroDto();
                    p1.setId(perro.getId());
                    return p1;
                });

        Flowable<PerroDto> perrosFlow = service.findAllService();
        perrosFlow.test()
                .assertValueCount(2);
    }
    @Test
    void saveTest(){
        //Arrange
        PerroDto perroDto = new PerroDto();
        perroDto.setNombrePerro("tyson");
        Perro perroEntity = new Perro();
        perroEntity.setNombrePerro("tyson");

        when(mapper.dtoToEntity(perroDto, Perro.class)).thenReturn(perroEntity);
        when(repository.save(perroEntity)).thenReturn(perroEntity);

        //Act
        Completable result = service.saveService(perroDto);

        //Assert
        result.test()
                .assertNoErrors()
                .assertComplete();
        verify(repository,times(1)).save(perroEntity);
        verify(mapper,times(1)).dtoToEntity(perroDto, Perro.class);


    }
    @Test
    void deleteServiceTest() {
        // Arrange
        Integer id = 1;
        Perro perro = new Perro();
        perro.setId(id);
        PerroDto perroDto = new PerroDto();
        perroDto.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(perro));
        when(mapper.entityToDto(perro, PerroDto.class)).thenReturn(perroDto);
        when(mapper.dtoToEntity(perroDto, Perro.class)).thenReturn(perro);
        doNothing().when(repository).delete(perro);

        // Act
        service.deleteService(id).test()
                // Assert
                .assertComplete()
                .assertNoErrors();

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).delete(perro);
    }
    @Test
    void updateService(){
        // Arrange
        Integer id = 1;
        PerroDto perroDto = new PerroDto();
        perroDto.setId(id);

        Perro perroFound = new Perro();
        perroFound.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(perroFound));
        when(mapper.entityToDto(perroFound, PerroDto.class)).thenReturn(perroDto);
        when(mapper.dtoToEntity(perroDto, Perro.class)).thenReturn(perroFound);
        when(repository.save(perroFound)).thenReturn(perroFound);

        // Act
        service.updateService(perroDto, id).test()
                // Assert
                .assertComplete()
                .assertNoErrors();

        verify(repository, times(1)).findById(id);
        verify(repository, times(1)).save(perroFound);

    }
}
