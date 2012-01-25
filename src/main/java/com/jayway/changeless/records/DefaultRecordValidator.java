package com.jayway.changeless.records;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

class DefaultRecordValidator implements RecordValidator {
	private static final IdentityHashMap<Class<?>, Class<?>> boxTypes =
			new IdentityHashMap<Class<?>, Class<?>>();
	static {
		boxTypes.put(Byte.TYPE, Byte.class);
		boxTypes.put(Character.TYPE, Character.class);
		boxTypes.put(Double.TYPE, Double.class);
		boxTypes.put(Float.TYPE, Float.class);
		boxTypes.put(Integer.TYPE, Integer.class);
		boxTypes.put(Long.TYPE, Long.class);
		boxTypes.put(Short.TYPE, Short.class);
		boxTypes.put(Boolean.TYPE, Boolean.class);
	}

	@Override
	public HashMap<String, Class<?>> validateRecord(Class<?> clazz) {
		ensureInterface(clazz);
		
		Method[] methods = clazz.getMethods();
				
		Map<String,RecordField> recordFields = new HashMap<String,RecordField>();
		for (Method method : methods) {
			if (method.getDeclaringClass().equals(Record.class)) {
				continue;
			}
			
			String methodName = method.getName();
			RecordField field = recordFields.get(methodName);
			if (field == null) {
				field = new RecordField();
				recordFields.put(methodName, field);
			}
			
			if (isExtrator(method)){
				field.setExtractor(method);
			} else {
				field.setMutator(method);
			}
		}
		
		HashMap<String, Class<?>> types = new HashMap<String, Class<?>>();
		for (Map.Entry<String, RecordField> entry : recordFields.entrySet())	{
			RecordField field = entry.getValue();
			field.validate(clazz);
			types.put(entry.getKey(), field.getType());
		}
		return types;
	}

	private void ensureInterface(Class<?> clazz) {
		if (!clazz.isInterface()) {
			throw new RecordValidationException("Records can only be created from an interface.");
		}
	}

	private boolean isExtrator(Method method) {
		return method.getParameterTypes().length == 0;
	}
	
	private class RecordField {
		private final String helpMessage = 
                  "A record is an immutable collection of unordered labeled fields\n"
				+ "defined by a record interface. Each field has an extractor method and\n"
				+ "a mutator method. The extractor method can be used to extract the\n"
				+ "value of the field in the record and the mutator is used to create a\n"
				+ "copy of the record where the field is change to a new value. For a\n"
				+ "Person record with a name field of type String the extractor and\n"
				+ "mutator must have the following signature:\n"
				+ "\n"
				+ "interface Person {\n"
				+ "  String name();            // Extractor for the name field\n"
				+ "  Person name(String name); // Mutator for the name field\n"
				+ "}";
		
		private Method extractor;
		private Method mutator;
		
		public void setExtractor(Method extractor) {
			this.extractor = extractor;
		}

		public Class<?> getType() {
			Class<?> type = extractor.getReturnType();
			if (type.isPrimitive()) {
				// the data map will never contain primitives, so we need to
				// convert this into the boxed variant, or merge()s will fail.
				type = boxTypes.get(type);
			}
			return type;
		}

		public void setMutator(Method mutator) {
			if (this.mutator != null) {
				String message = String.format("Duplicate mutator '%s'", mutator);
				throwValidationException(message);
			}
			this.mutator = mutator;
		}
		
		public void validate(Class<?> clazz) {
			ensureMutatorIsDefined();
			ensureExtractorIsDefined();
			ensureValidExtrator();
			ensureValidMutator(clazz);
		}

		private void ensureValidMutator(Class<?> clazz) {
			ensureMutatorReturnTypeIsRecordType(clazz);
			ensureValidMutatorParameters();
		}

		private void ensureValidMutatorParameters() {
			if (mutator.getParameterTypes().length != 1) {
				throwInvalidMutatorParameterException();
			}
			if (!mutator.getParameterTypes()[0].equals(extractor.getReturnType())) {
				throwInvalidMutatorParameterException();
			}
		}

		private void throwInvalidMutatorParameterException() {
			String message = String.format("Mutator '%s' must have one parameter of type '%s'", 
					mutator, extractor.getReturnType());
			throwValidationException(message);
		}

		private void ensureMutatorReturnTypeIsRecordType(Class<?> clazz) {
			if (!mutator.getReturnType().isAssignableFrom(clazz)) {
				String message = String.format("Mutator '%s' must have return type that is assignable from '%s'", mutator, clazz);
				throwValidationException(message);
			}
		}

		private void ensureValidExtrator() {
			if (extractor.getReturnType().equals(void.class)) {
				String message = String.format("Extractor must have a return type '%s'", extractor);
				throwValidationException(message);
			}
		}

		private void ensureExtractorIsDefined() {
			if (this.extractor == null) {
				String message = String.format("Missing extractor for mutator '%s'", mutator);
				throwValidationException(message);
			}
		}

		private void ensureMutatorIsDefined() {
			if (this.mutator == null) {
				String message = String.format("Missing mutator for extractor '%s'", extractor);
				throwValidationException(message);
			}
		}
		
		private void throwValidationException(String errorMessage) {
			String message = String.format("Record validation error: %s\n\n%s", 
					errorMessage, helpMessage);
			throw new RecordValidationException(message);
		}
	}
}
