package com.example.demo.exception;

import com.example.demo.config.PropertiesConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Optional;

@Component
public class DemoException {

    private static PropertiesConfig propertiesConfig;

    @Autowired
    public DemoException(PropertiesConfig propertiesConfig) {
        DemoException.propertiesConfig = propertiesConfig;
    }

    public static RuntimeException throwException(String messageTemplate, String... args) {
        return new RuntimeException(format(messageTemplate, args));
    }

    public static RuntimeException throwException(EntityType entityType, ExceptionType exceptionType, String... args) {
        String messageTemplate = getMessageTemplate(entityType, exceptionType);
        return throwException(exceptionType, messageTemplate, args);
    }

    public static RuntimeException throwExceptionWithId(EntityType entityType, ExceptionType exceptionType, Integer id, String... args) {
        String messageTemplate = getMessageTemplate(entityType, exceptionType).concat(".").concat(id.toString());
        return throwException(exceptionType, messageTemplate, args);
    }

    private static String getMessageTemplate(EntityType entityType, ExceptionType exceptionType) {
        return entityType.name().concat(".").concat(exceptionType.getValue()).toLowerCase();
    }

    private static String format(String template, String... args) {
        Optional<String> templateContent = Optional.ofNullable(propertiesConfig.getConfigValue(template));
        return templateContent.map(s -> MessageFormat.format(s, (Object[]) args)).orElseGet(() -> String.format(template, (Object[]) args));
    }

    private static RuntimeException throwException(ExceptionType exceptionType, String messageTemplate, String... args) {
        if (ExceptionType.ENTITY_NOT_FOUND.equals(exceptionType)) {
            return new EntityNotFoundException(format(messageTemplate, args));
        } else if (ExceptionType.DUPLICATE_ENTITY.equals(exceptionType)) {
            return new DuplicateEntityException(format(messageTemplate, args));
        }
        return new RuntimeException(format(messageTemplate, args));
    }


    public static class EntityNotFoundException extends RuntimeException {
        public EntityNotFoundException(String message) {
            super(message);
        }
    }

    public static class DuplicateEntityException extends RuntimeException {
        public DuplicateEntityException(String message) {
            super(message);
        }
    }

}
