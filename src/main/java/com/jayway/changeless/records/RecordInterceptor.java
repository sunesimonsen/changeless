package com.jayway.changeless.records;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;

import com.jayway.changeless.maps.Map;
import com.jayway.changeless.maps.Maps;
import com.jayway.changeless.optionals.Optional;
import com.jayway.changeless.tuples.Tuple;
import com.jayway.changeless.utilities.Guard;


class RecordInceptor implements InvocationHandler {
	private final Map<String, Object> data;
	private final HashMap<String, Class<?>> types;
	private final Class<?> clazz;
	
	public RecordInceptor(Class<?> clazz, HashMap<String, Class<?>> types) {
		this(clazz, Maps.<String,Object>empty(), types);
	}
	
	private RecordInceptor(
			Class<?> clazz,
			Map<String, Object> data,
			HashMap<String, Class<?>> types) {
		Guard.notNull(clazz, "clazz");
		this.clazz = clazz;
		this.data = data;
		this.types = types;
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		if (method.getDeclaringClass() == Object.class) {
			if (isToString(method)) {
				return toString();
			}
			
			if (isHashCodeMethod(method)) {
				return hashCode();
			}
			
			if (isEqualsMethod(method)) {
				return equals(args[0]);
			}
		}
		
		if (method.getDeclaringClass() == Record.class) {
			if (isGetDataMethod(method)) {
				return getData();
			}
			
			if (isMergeMethod(method)) {
				return merge(args[0]);
			}
		}
		
		Class<?> returnType = method.getReturnType();
		String methodName = method.getName();
		
		if (returnType.equals(clazz)) {
			Object value = args[0];
			if (value == null) {
				String message = String.format("Field '%s' can't be set to null", methodName);
				throw new IllegalArgumentException(message);
			}
			
			Map<String, Object> newData = data.put(methodName, value);
			return Proxy.newProxyInstance(clazz.getClassLoader(),
					new Class[] { clazz }, 
					new RecordInceptor(clazz, newData, types));
		} 
		
		Optional<Object> result = data.get(methodName);
		if (result.hasNoValue()) {
			String message = String.format("Field '%s' has not been set to a value", methodName);
			throw new IllegalStateException(message);
		}
		return result.getValue();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Record))
			return false;
		Record<?> other = (Record<?>) obj;
		return data.equals(other.getData());
	}
	
	public Map<String, Object> getData() {
		return data;
	}
	
	public Object merge(Object arg) {
		@SuppressWarnings("unchecked")
		Map<String, Object> merging = (Map<String, Object>) arg;
		Map<String, Object> newData = data;
		for (Tuple<String, Object> entry : merging) {
			String field = entry.getFirst();
			Object value = entry.getSecond();
			Class<?> type = types.get(field);
			if (type != null && !type.isInstance(value)) {
				String msg = "Cannot merge value '%s' of type %s " +
						"into field '%s' of type %s";
				throw new RecordValidationException(
						String.format(msg, value, value.getClass(), field, type));
			}
			newData = newData.put(field, value);
		}
		
		return Proxy.newProxyInstance(clazz.getClassLoader(),
				new Class[] { clazz }, 
				new RecordInceptor(clazz, newData, types));
	}

	@Override
	public String toString() {
		return data.toString();
	}
	
	private boolean isGetDataMethod(Method method) {
		return "getData".equals(method.getName());
	}
	
	private boolean isMergeMethod(Method method) {
		return "merge".equals(method.getName());
	}
	
	private boolean isEqualsMethod(Method method) {
		return "equals".equals(method.getName());
	}

	private boolean isHashCodeMethod(Method method) {
		return "hashCode".equals(method.getName());
	}
	
	private boolean isToString(Method method) {
		return "toString".equals(method.getName());
	}	
}
