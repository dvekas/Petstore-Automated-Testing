package org.dvekas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;

public class CustomObjectMapper {

    /**
     * Creates an Object, from the body of the given Response object.
     *
     * @param response Response object, to create an Object from.
     * @return The created object.
     */
    public  <T> T mapObjectFromResponse(Response response, Class<T> typeParameterClass) {
        ObjectMapper mapper = new ObjectMapper();
        T targetObject;

        try {
            targetObject = mapper.readValue(response.getBody().asPrettyString(), typeParameterClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return targetObject;
    }

    /**
     * Creates an Object, from the given YAML file.
     *
     * @param yamlFile YAML file object, to create an Object from.
     * @return The created object.
     */
    public  <T> T mapObjectFromYaml(File yamlFile, Class<T> typeParameterClass) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        mapper.findAndRegisterModules();
        T targetObject;

        try {
            targetObject = mapper.readValue(yamlFile, typeParameterClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return targetObject;
    }

}
