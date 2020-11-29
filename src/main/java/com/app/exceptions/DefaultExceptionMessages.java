package com.app.exceptions;

public final class DefaultExceptionMessages {
    public static String entityNotFound(String identifier) {
        return "The entity identified by " + '\'' + identifier + '\'' + " was not found";
    }
}
