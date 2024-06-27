package Model.Exceptions;

@SuppressWarnings("serial")
public abstract class GameActionException extends Exception {

    public GameActionException() {
        super();
    }

    public GameActionException(String message) {
        super(message);
    }

}
