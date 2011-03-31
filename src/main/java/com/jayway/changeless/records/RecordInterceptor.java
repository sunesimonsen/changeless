package com.jayway.changeless.records;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.jayway.changeless.Optional;
import com.jayway.changeless.maps.Map;
import com.jayway.changeless.maps.Maps;
import com.jayway.changeless.utilities.Guard;


class RecordInceptor<T extends Record> implements InvocationHandler {
	private Map<String, Object> data;
	private final Class<T> clazz;
	
	public RecordInceptor(Class<T> clazz) {
		this(clazz, Maps.<String,Object>empty());
	}
	
	private RecordInceptor(Class<T> clazz, Map<String, Object> data) {
		Guard.notNull(clazz, "clazz");
		this.clazz = clazz;
		this.data = data;
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
		}
		
		Class<?> returnType = method.getReturnType();
		String methodName = method.getName();
		
		if (returnType.equals(clazz)) {
			Map<String, Object> newData = data.put(methodName, args[0]);
			return Proxy.newProxyInstance(returnType.getClassLoader(),
					new Class[] { clazz }, 
					new RecordInceptor<T>(clazz, newData));
		} 
		
		Optional<Object> result = data.get(methodName);
		if (result.hasNoValue()) {
			throw new IllegalStateException(String.format("%s is not defined", methodName));
		}
		return result.getValue();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((clazz == null) ? 0 : clazz.hashCode());
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
		Record other = (Record) obj;
		return data.equals(other.getData());
	}
	
	public Map<String, Object> getData() {
		return data;
	}

	@Override
	public String toString() {
		return data.toString();
	}
	
	private boolean isGetDataMethod(Method method) {
		return "getData".equals(method.getName());
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
