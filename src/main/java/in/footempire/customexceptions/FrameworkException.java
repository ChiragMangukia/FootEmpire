package in.footempire.customexceptions;

public class FrameworkException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FrameworkException(String msg) {
		super(msg);
		printStackTrace();
	}
}