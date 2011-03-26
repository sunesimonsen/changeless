package com.jayway.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;


/**
 * See http://martinaharris.com/2009/10/testing-java-equals-and-hashcode-methods-essential/
 */
public abstract class EqualsAndHashcodeTestSupport {

	private Object x;
	private Object y;
	private Object z;
	
	private Object notx;
	
	@Before
	public void baseSetup() {
		x = createFirstInstance();
		y = createFirstInstance();
		z = createFirstInstance();
		notx = createSecondInstance(); 
	}
	
	protected abstract Object createFirstInstance();
	protected abstract Object createSecondInstance();
	
	@Test
    /**
     * A class is equal to itself.
     */
    public void equalToSelf() {
        assertTrue("Class equal to itself.", x.equals(x));
    }
 
    /**
     * x.equals(WrongType) must return false;
     *
     */
    @Test
    public void passIncompatibleTypeIsFalse() {
        assertFalse("Passing incompatible object to equals should return false", x.equals("string"));
    }
 
    /**
     *
     * x.equals(null) must return false;
     */
    @Test
    public void nullReferenceIsFalse() {
        assertFalse("Passing null to equals should return false", x.equals(null));
    }
 
    /**
     * 1. x, x.equals(x) must return true.
     * 2. x and y, x.equals(y) must return true if and only if y.equals(x) returns true.
     */
    @Test
    public void equalsIsReflexiveIsSymmetric() {
 
        assertTrue("Reflexive test fail x,y", x.equals(y));
        assertTrue("Symmetric test fail y", y.equals(x));
 
    }
 
    /**
     * 1. x.equals(y) returns true
     * 2. y.equals(z) returns true
     * 3. x.equals(z) must return true
     */
    @Test
    public void equalsIsTransitive() {
 
        assertTrue("Transitive test fails x,y", x.equals(y));
        assertTrue("Transitive test fails y,z", y.equals(z));
        assertTrue("Transitive test fails x,z", x.equals(z));
    }
 
    /**
     * Repeated calls to equals consistently return true or false.
     */
    @Test
    public void equalsIsConsistent() {
 
        assertTrue("Consistent test fail x,y", x.equals(y));
        assertTrue("Consistent test fail x,y", x.equals(y));
        assertTrue("Consistent test fail x,y", x.equals(y));
        assertFalse(notx.equals(x));
        assertFalse(notx.equals(x));
        assertFalse(notx.equals(x));
 
    }
 
    /**
     * Repeated calls to hashcode should consistently return the same integer.
     */
    @Test
    public void hashcodeIsConsistent() {
 
        int initial_hashcode = x.hashCode();
 
        assertEquals("Consistent hashcode test fails", initial_hashcode, x.hashCode());
        assertEquals("Consistent hashcode test fails", initial_hashcode, x.hashCode());
    }
 
    /**
     * Objects that are equal using the equals method should return the same integer.
     */
    @Test
    public void hashcodeTwoEqualsObjects_produceSameNumber() {
 
        int xhashcode = x.hashCode();
        int yhashcode = y.hashCode();
 
        assertEquals("Equal object, return equal hashcode test fails", xhashcode, yhashcode);
    }
 
    /**
     * A more optimal implementation of hashcode ensures
     * that if the objects are unequal different integers are produced.
     *
     */
    @Test
    public void hashcodeTwoUnEqualObjects_produceDifferentNumber() {
 
        int xhashcode = x.hashCode();
        int yhashcode = notx.hashCode();
 
        assertTrue("Equal object, return unequal hashcode test fails", !(xhashcode == yhashcode));
    }
}
