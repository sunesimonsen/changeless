package com.jayway.changeless.records;

import org.junit.Test;

import com.jayway.changeless.maps.Map;
import com.jayway.changeless.maps.Maps;

public class RecordValidationTests {

	private class ClassRecord implements Record<ClassRecord> {
		@Override
		public Map<String, Object> getData() {
			return null;
		}

		@Override
		public ClassRecord merge(Map<String, ?> of) {
			return null;
		}
	}
	
	@Test(expected=RecordValidationException.class)
	public void throwOnConcreteClass() {
		Records.of(ClassRecord.class);
	}
	
	private interface MissingMutatorRecord extends Record<MissingMutatorRecord> {
		String foo();
	}
	
	@Test(expected=RecordValidationException.class)
	public void throwOnMissingMutator() {
		Records.of(MissingMutatorRecord.class);
	}
	
	private interface MissingExtractorRecord extends Record<MissingExtractorRecord> {
		MissingExtractorRecord foo(String bar);
	}
	
	@Test(expected=RecordValidationException.class)
	public void throwOnMissingExtractor() {
		Records.of(MissingExtractorRecord.class);
	}
	
	private interface DuplicateMutatorRecord extends Record<DuplicateMutatorRecord> {
		String foo();
		DuplicateMutatorRecord foo(String bar);
		DuplicateMutatorRecord foo(String bar, String baz);
	}
	
	@Test(expected=RecordValidationException.class)
	public void throwOnDuplicateMutator() {
		Records.of(DuplicateMutatorRecord.class);
	}
	
	private interface InvalidExtratorRecord extends Record<InvalidExtratorRecord> {
		void foo();
		InvalidExtratorRecord foo(String bar);
	}
	
	@Test(expected=RecordValidationException.class)
	public void throwOnInvalidExtrator() {
		Records.of(InvalidExtratorRecord.class);
	}
	
	private interface InvalidMutatorReturnRecord extends Record<InvalidMutatorReturnRecord> {
		String foo();
		int foo(String bar);
	}
	
	@Test(expected=RecordValidationException.class)
	public void throwOnInvalidMutatorReturnType() {
		Records.of(InvalidMutatorReturnRecord.class);
	}
	
	private interface InvalidMutatorParameterTypeRecord extends Record<InvalidMutatorParameterTypeRecord> {
		String foo();
		InvalidMutatorParameterTypeRecord foo(int bar);
	}
	
	@Test(expected=RecordValidationException.class)
	public void throwOnInvalidMutatorParameterType() {
		Records.of(InvalidMutatorParameterTypeRecord.class);
	}
	
	private interface InvalidMutatorParameterCountRecord extends Record<InvalidMutatorParameterCountRecord> {
		String foo();
		InvalidMutatorParameterCountRecord foo(String foo, int bar);
	}
	
	@Test(expected=RecordValidationException.class)
	public void throwOnInvalidMutatorParameterCount() {
		Records.of(InvalidMutatorParameterCountRecord.class);
	}
	
	private interface InheritErrorRecord extends InvalidMutatorParameterCountRecord {
		
	}
	
	@Test(expected=RecordValidationException.class)
	public void throwOnInheritedErrors() {
		Class<InheritErrorRecord> clazz = InheritErrorRecord.class;
		Records.of(clazz);
	}
	
	private interface Valid extends Record<Valid> {
		String foo();
		Valid foo(String str);
	}
	
	@Test(expected=RecordValidationException.class)
	public void throwOnMergeWithWronglyTypedValues() {
		Valid valid = Records.of(Valid.class);
		valid.merge(Maps.of("foo", 1));
	}
}
