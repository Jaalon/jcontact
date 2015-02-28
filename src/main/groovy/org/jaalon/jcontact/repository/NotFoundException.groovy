package org.jaalon.jcontact.repository

class NotFoundException extends RuntimeException {
    NotFoundException(String message) {
        super(message)
    }
}
