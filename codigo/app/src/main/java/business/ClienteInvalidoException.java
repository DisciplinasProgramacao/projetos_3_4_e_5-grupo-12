package business;

import javax.management.InvalidAttributeValueException;

public class ClienteInvalidoException extends InvalidAttributeValueException {

    public ClienteInvalidoException(String message) {
        super(message);
    }
}
