package com.user.service.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Utilisateur introuvable avec l’ID : " + id);
    }
}
