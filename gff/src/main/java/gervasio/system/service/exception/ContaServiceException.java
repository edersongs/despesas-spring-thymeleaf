/**
 * 
 */
package gervasio.system.service.exception;

/**
 * @author Éderson Gervásio
 *
 */
public class ContaServiceException extends Exception {

	private static final long serialVersionUID = 7509486453972170653L;

	 public ContaServiceException() {
	        super();
	    }

	    public ContaServiceException(String message) {
	        super(message);
	    }

	    public ContaServiceException(String message, Throwable cause) {
	        super(message, cause);
	    }
}
