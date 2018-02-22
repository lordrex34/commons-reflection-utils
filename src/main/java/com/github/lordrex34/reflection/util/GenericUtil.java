/*
 * Copyright (c) 2017 Reginald Ravenhorst <lordrex34@gmail.com>
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

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author lord_rex
 */
public final class GenericUtil
{
	private GenericUtil()
	{
		// utility class
	}
	
	/**
	 * Retrieves the generic types of a specified field into an array of {@link Type}s.
	 * @param field the field used to get the generic types
	 * @return array of types
	 */
	public static Type[] typesOf(Field field)
	{
		final Type genericType = field.getGenericType();
		if (!ParameterizedType.class.isInstance(genericType))
		{
			return null;
		}
		
		final ParameterizedType parameterizedType = (ParameterizedType) genericType;
		return parameterizedType.getActualTypeArguments();
	}
	
	/**
	 * Retrieves a specific element of the generic type array, see {@link #typesOf(Field)}.
	 * @param field the field used to get the generic types
	 * @param index the index of the desired generic type
	 * @return generic type
	 */
	public static Class<?> typeOf(Field field, int index)
	{
		final Type[] allGenericTypes = typesOf(field);
		if (allGenericTypes == null)
		{
			return Object.class; // missing wildcard declaration
		}
		
		return (Class<?>) allGenericTypes[index];
	}
	
	/**
	 * Retrieves the generic parameter of a specified class.<br>
	 * In case it cannot be found, the parent is being checked as well.
	 * @param clazz the class whose generic is being gathered
	 * @param index the index of the desired generic parameter
	 * @return generic parameter
	 */
	public static Class<?> parameterOf(Class<?> clazz, int index)
	{
		Type genericSuperClass = clazz.getGenericSuperclass();
		
		ParameterizedType parametrizedType = null;
		while (parametrizedType == null)
		{
			if ((genericSuperClass instanceof ParameterizedType))
			{
				parametrizedType = (ParameterizedType) genericSuperClass;
			}
			else
			{
				genericSuperClass = ((Class<?>) genericSuperClass).getGenericSuperclass();
			}
		}
		
		return (Class<?>) parametrizedType.getActualTypeArguments()[index];
	}
}
