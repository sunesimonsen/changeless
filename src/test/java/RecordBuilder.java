import java.lang.reflect.Proxy;


public class RecordBuilder {
	@SuppressWarnings("unchecked")
	public <T extends Record> T create(Class<T> clazz) throws InstantiationException, IllegalAccessException {
		T instance = (T) Proxy.newProxyInstance(clazz.getClassLoader(),
                new Class[] { clazz },
                new RecordInceptor<T>(clazz));
		return instance;
	}
}
