package com.app.exceptions;

public final class DefaultExceptionMessages {
    public static final String entityNotFound(String identifier) {
        return "The entity identified by " + '\'' + identifier + '\'' + " was not found";
    }
}
