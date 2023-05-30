package business;

import javax.management.InvalidAttributeValueException;

public class AvaliacaoInvalidaException extends InvalidAttributeValueException{

    public AvaliacaoInvalidaException(String message) {
        super(message);
    }

}
