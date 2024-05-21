package com.reactivox.springrxjava.repository;

import com.reactivox.springrxjava.model.Perro;
import com.reactivox.springrxjava.model.Raza;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PerroRepositoryTest {
    @Autowired
    private PerroRepository perroRepository;
    private Perro perro;

    @BeforeEach
    void setUp(){
        perro = new Perro();
        perro.setNombrePerro("Fido");
        perro.setEdad(22);
        Raza raza = new Raza();
        raza.setId(1);
        perro.setRaza(raza);
    }
    @Test
    public void testGuardarPerro() {
        Perro perroGuardado = perroRepository.save(perro);
        assertThat(perroGuardado).isNotNull();
        assertThat(perroGuardado.getId()).isGreaterThan(0);
    }
    @Test
    public void findById() {
        final Integer id = 5;
        Optional<Perro> perroGuardado = perroRepository.findById(id);
        assertThat(perroGuardado).isPresent();
        assertThat(perroGuardado.get()).isNotNull();
    }
}
