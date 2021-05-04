package Application.Database;

/**
 * Exception levée en cas de mauvais mot de passe
 */
public class BadPasswordError extends DaoError {
    public BadPasswordError(){
        super("Mauvais mot de passe");
    }
}