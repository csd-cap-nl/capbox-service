package nl.cap.csd.capbox.commons.services.authentication;


public class FunctionUtil {

	@FunctionalInterface
	public interface ExceptionThrower<T>{
		T apply() throws Exception;
	}

	public static <T> T runtimeException(ExceptionThrower<T> exceptionThrower){
		System.out.println("b00n");
		try {
			return exceptionThrower.apply();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
