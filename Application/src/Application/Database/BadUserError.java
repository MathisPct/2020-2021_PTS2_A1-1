package Application.Database;

/**
 * Exception levée en cas de mauvais utilisateur
 */
public class BadUserError extends DaoError {
    /**
     *Crée une nouvelle instance de BadUserError
     */
    public BadUserError() {
        super("L'utilisateur ne correspond pas à celui dans la base de donnée");
    }

    /**
     * Crée une nouvelle instance de BadUserError
     *
     * @param message Le message détaillant exception
     */
    public BadUserError(String message) {
        super(message);
    }

    /**
     * Crée une nouvelle instance de BadUserError
     *
     * @param cause L'exception à l'origine de cette exception
     */
    public BadUserError(Throwable cause) {
        super(cause);
    }

    /**
     * Crée une nouvelle instance de BadUserError
     *
     * @param message Le message détaillant exception
     * @param cause   L'exception à l'origine de cette exception
     */
    public BadUserError(String message, Throwable cause) {
        super(message, cause);
    }
}