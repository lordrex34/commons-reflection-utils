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

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * @author UnAfraid
 * @author NB4L1
 */
class ClassPathTest
{
	@Test
	void testDiscovery() throws IOException
	{
		final List<Class<?>> classes1 = ClassPathUtil.getAllClasses("com").toList();
		final List<Class<?>> classes2 = ClassPathUtil.getAllClasses("com.github.lordrex34.reflection.util").toList();
		final List<Class<?>> classes3 = ClassPathUtil.getAllClasses("com.github.lordrex34.reflection.util.jar").toList();
		
		assertTrue(classes1.size() > classes2.size());
		assertTrue(classes2.size() > classes3.size());
		assertTrue(classes3.size() > 0);
	}
}
