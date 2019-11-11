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
package com.github.lordrex34.reflection.util.jar;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;

import com.google.common.base.Strings;

/**
 * A simple class to gather the manifest version information of JAR files.
 * @author lord_rex
 */
public class VersionInfo
{
	/** A default string for the cases when version info cannot be retrieved. (IDE Mode) */
	public static final String IDE_MODE = "Version Info - IDE Mode.";
	
	private String filename = null;
	private final Map<String, String> manifestAttributes;
	
	/**
	 * Gather version information from the class.
	 * @param clazz the class that is used for version extraction
	 */
	public VersionInfo(Class<?> clazz)
	{
		this(Locator.getClassSource(clazz));
	}
	
	/**
	 * Gather version information from the class.
	 * @param file the JAR file that is used for version extraction
	 */
	public VersionInfo(File file)
	{
		this.manifestAttributes = new HashMap<>();

		if (!file.isFile())
		{
			return;
		}
		
		final String filename = file.getName();
		this.filename = filename.substring(0, filename.lastIndexOf("."));
		
		try (JarFile jarFile = new JarFile(file))
		{
			final Attributes attributes = jarFile.getManifest().getMainAttributes();
			attributes.forEach((key, value) -> manifestAttributes.put(String.valueOf(key), String.valueOf(value)));
		}
		catch (IOException e)
		{
			// ignore, IDE mode, etc...
		}
	}
	
	/**
	 * Gets an unmodifiable view of the manifest attributes of the given JAR.
	 * @return all manifest attributes
	 */
	public Map<String, String> getManifestAttributes()
	{
		return Collections.unmodifiableMap(manifestAttributes);
	}
	
	/**
	 * Checks whether the map contains the specified manifest or not.
	 * @param name see {@link VersionInfoManifest}
	 * @return @{code true} if the map contains the specified manifest
	 */
	public boolean hasManifest(String name)
	{
		return manifestAttributes.containsKey(name);
	}
	
	/**
	 * Gets a manifest from the manifest attribute map, shows {@link #IDE_MODE} if null.
	 * @param name see {@link VersionInfoManifest}
	 * @param defaultValue a fallback value
	 * @return manifest info
	 */
	public String getManifest(String name, String defaultValue)
	{
		return manifestAttributes.getOrDefault(name, defaultValue);
	}
	
	/**
	 * Compatibility method. See {@link #getManifest(String, String)}
	 * @param name see {@link VersionInfoManifest}
	 * @return manifest info
	 */
	public String getManifest(String name)
	{
		return getManifest(name, IDE_MODE);
	}
	
	/**
	 * Verifies if current class is running from IDE.
	 * @return {@code true} if current environment is IDE, {@code false} otherwise
	 */
	public boolean isIDE()
	{
		return Strings.isNullOrEmpty(filename);
	}
	
	/**
	 * Gets a pretty formatted class info.
	 * @return formatted class info
	 */
	public String getFormattedClassInfo()
	{
		if (isIDE())
		{
			return IDE_MODE;
		}
		
		final StringBuilder sb = new StringBuilder();
		
		sb.append(filename).append(": ");
		sb.append(getManifest(VersionInfoManifest.GIT_HASH_SHORT)).append(", ");
		sb.append(getManifest(VersionInfoManifest.GIT_COMMIT_COUNT)).append(", ");
		sb.append(getManifest(VersionInfoManifest.IMPLEMENTATION_TIME));
		sb.append(" [ ").append(getManifest(VersionInfoManifest.GIT_BRANCH)).append(" ]");
		
		return sb.toString();
	}
	
	/**
	 * Standard manifest attribute constants.
	 * @author lord_rex
	 */
	public interface VersionInfoManifest
	{
		String CREATED_BY = "Created-By";
		String BUILT_BY = "Built-By";
		String IMPLEMENTATION_URL = "Implementation-URL";
		String IMPLEMENTATION_TIME = "Implementation-Time";
		String GIT_BRANCH = "Git-Branch";
		String GIT_HASH_FULL = "Git-Hash-Full";
		String GIT_HASH_SHORT = "Git-Hash-Short";
		String GIT_COMMIT_COUNT = "Git-Commit-Count";
	}
	
	/**
	 * Gather version info of multiply classes. You can use it to gather information of any 3rd party application JAR.
	 * @param classes the classes to be checked
	 * @return string array of version info
	 */
	public static List<String> of(Class<?>... classes)
	{
		final List<String> versions = new ArrayList<>();
		for (Class<?> clazz : classes)
		{
			final VersionInfo versionInfo = new VersionInfo(clazz);
			versions.add(versionInfo.getFormattedClassInfo());
		}
		
		return versions;
	}
}
