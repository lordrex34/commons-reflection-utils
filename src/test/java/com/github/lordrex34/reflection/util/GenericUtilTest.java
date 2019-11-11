/*
 * Copyright (c) 2018 Reginald Ravenhorst <lordrex34@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.github.lordrex34.reflection.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * @author lord_rex
 */
class GenericUtilTest
{
	@Test
	void testClassParent()
	{
		assertNotEquals(Object.class, GenericUtil.parameterOf(ParentTestSubject.class, 0));
		assertNotEquals(Object.class,GenericUtil.parameterOf(ParentTestSubject.class, 1));
		assertEquals( Integer.class,GenericUtil.parameterOf(ParentTestSubject.class, 0));
		assertEquals(String.class, GenericUtil.parameterOf(ParentTestSubject.class, 1));
	}
	
	@Test
	void testClassInherited()
	{
		assertNotEquals(Object.class, GenericUtil.parameterOf(InheritedTestSubject.class, 0));
		assertNotEquals(Object.class, GenericUtil.parameterOf(InheritedTestSubject.class, 1));
		assertEquals(Integer.class, GenericUtil.parameterOf(InheritedTestSubject.class, 0));
		assertEquals(String.class, GenericUtil.parameterOf(InheritedTestSubject.class, 1));
	}
	
	@Test
	void testField()
	{
		final Field field = FieldTestSubject.class.getFields()[0];
		
		assertNotEquals(Object.class, GenericUtil.typeOf(field, 0));
		assertNotEquals(Object.class, GenericUtil.typeOf(field, 1));
		assertEquals(String.class, GenericUtil.typeOf(field, 0));
		assertEquals(Byte.class, GenericUtil.typeOf(field, 1));
	}
	
	abstract class ParentTestSubject extends HashMap<Integer, String>
	{
		private static final long serialVersionUID = 1L;
	}
	
	abstract class InheritedTestSubject extends ParentTestSubject
	{
		private static final long serialVersionUID = 1L;
	}
	
	class FieldTestSubject
	{
		public Map<String, Byte> testField;
	}
}
