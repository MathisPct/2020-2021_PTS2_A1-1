package Application.Database;

/**
 * Exception générale de la couche DAO
 */
public class DaoError extends Exception{
    /**
     *Crée une nouvelle instance de DaoError
     */
    public DaoError(){
        super();
    }

    /**
     * Crée une nouvelle instance de DaoError
     * @param message Le message détaillant exception
     */
    public DaoError(String message){
        super(message);
    }

    /**
     * Crée une nouvelle instance de DaoError
     * @param cause L'exception à l'origine de cette exception
     */
    public DaoError(Throwable cause) {
        super(cause);
    }
    /**
     * Crée une nouvelle instance de DaoError
     * @param message Le message détaillant exception
     * @param cause L'exception à l'origine de cette exception
     */
    public DaoError(String message, Throwable cause) {
        super(message, cause);
    }
}
