package org.generation.blogPessoal.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import java.time.format.DateTimeFormatter;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UsuarioTest {

    public Usuario usuario;
    public Usuario usuarioNulo = new Usuario();
    
    @Autowired
    private ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    
    Validator validator = factory.getValidator();
    
    @BeforeEach
    public void start() {
        LocalDate data = LocalDate.parse("2000-07-22", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        usuario = new Usuario(0L, "João da Silva", "joao@email.com.br", "13465278", data);
    }
    
    @Test
    void testValidaAtributos() {
        Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuario);
        System.out.println(violacao.toString());
        
        assertTrue(violacao.isEmpty());
    }
    
    @Test
    void testNaoValidaAtributos() {
        Set<ConstraintViolation<Usuario>> violacao = validator.validate(usuarioNulo);
        System.out.println(violacao.toString());
        
        assertFalse(violacao.isEmpty());
    }
    
}