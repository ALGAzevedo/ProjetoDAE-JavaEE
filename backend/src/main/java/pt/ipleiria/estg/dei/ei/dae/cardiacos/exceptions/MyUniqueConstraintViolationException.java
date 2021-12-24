package pt.ipleiria.estg.dei.ei.dae.cardiacos.exceptions;

public class MyUniqueConstraintViolationException extends Exception{
    public MyUniqueConstraintViolationException(String message) {
        super(message);
    }
}
