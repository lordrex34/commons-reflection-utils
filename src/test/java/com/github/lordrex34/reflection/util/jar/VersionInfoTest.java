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
package com.github.lordrex34.reflection.util.jar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import com.github.lordrex34.reflection.util.jar.VersionInfo.VersionInfoManifest;
import com.google.common.collect.Multimap;

/**
 * @author lord_rex
 */
class VersionInfoTest
{
	@Test
	void testGuavaLib()
	{
		final VersionInfo versionInfo = new VersionInfo(Multimap.class);
		assertNotNull(versionInfo.getManifest(VersionInfoManifest.BUILT_BY));
		assertNotNull(versionInfo.getManifest(VersionInfoManifest.CREATED_BY));
		assertEquals("123", versionInfo.getManifest("nonsense", "123"));
		assertTrue(versionInfo.hasManifest(VersionInfoManifest.BUILT_BY));
		assertFalse(versionInfo.hasManifest("another_nonsense"));
	}
}
