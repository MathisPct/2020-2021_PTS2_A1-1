package Application.Database;

/**
 * Exception levée en cas de mauvais mot de passe
 */
public class BadPasswordError extends DaoError {
    public BadPasswordError(){
        super("Mauvais mot de passe");
    }
    
    /**
     * Crée une nouvelle instance de BadPasswordError
     * @param message Le message détaillant exception
     */
    public BadPasswordError(String message){
        super(message);
    }

    /**
     * Crée une nouvelle instance de BadPasswordError
     * @param cause L'exception à l'origine de cette exception
     */
    public BadPasswordError(Throwable cause) {
        super(cause);
    }
    /**
     * Crée une nouvelle instance de BadPasswordError
     * @param message Le message détaillant exception
     * @param cause L'exception à l'origine de cette exception
     */
    public BadPasswordError(String message, Throwable cause) {
        super(message, cause);
    }
}