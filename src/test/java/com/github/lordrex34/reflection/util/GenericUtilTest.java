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

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

/**
 * @author lord_rex
 */
public final class GenericUtilTest
{
	@Test
	public void testParent()
	{
		assertEquals(GenericUtil.getGenericParameter(ParentTestSubject.class, 0), Integer.class);
		assertEquals(GenericUtil.getGenericParameter(ParentTestSubject.class, 1), String.class);
	}
	
	@Test
	public void testInherited()
	{
		assertEquals(GenericUtil.getGenericParameter(InheritedTestSubject.class, 0), Integer.class);
		assertEquals(GenericUtil.getGenericParameter(InheritedTestSubject.class, 1), String.class);
	}
	
	abstract class ParentTestSubject extends HashMap<Integer, String>
	{
		private static final long serialVersionUID = 1L;
	}
	
	abstract class InheritedTestSubject extends ParentTestSubject
	{
		private static final long serialVersionUID = 1L;
	}
}
