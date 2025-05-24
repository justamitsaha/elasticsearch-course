package com.vinsguru.playground;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ResourceLoader;

import java.util.function.Consumer;

@SpringBootTest
@Import(TestcontainersConfiguration.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AbstractTest {

    private static final Logger log = LoggerFactory.getLogger(AbstractTest.class);

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private ResourceLoader resourceLoader;

    protected <T> T readResource(String path, TypeReference<T> typeReference){
        try{
            var classpath = "classpath:" + path;
            var file = this.resourceLoader.getResource(classpath).getFile();
            return this.mapper.readValue(file, typeReference);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    protected <T> Consumer<T> print(){
        return t -> log.info("{}", t);
    }


}
