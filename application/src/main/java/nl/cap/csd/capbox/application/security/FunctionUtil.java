package authenticationprovider;


public class FunctionUtil {

	@FunctionalInterface
	interface ExceptionThrower<T>{
		T apply() throws Exception;
	}

	public static <T> T runtimeException(ExceptionThrower<T> exceptionThrower){
		try {
			return exceptionThrower.apply();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
