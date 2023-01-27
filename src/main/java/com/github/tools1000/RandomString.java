/*******************************************************************************
 * Copyright (c) 2017 kerner1000. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.github.tools1000;

import java.util.Random;

public class RandomString {

    public static final char[] DEFAULT_CHARS = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public static final int DEFAULT_LENGTH = 6;

    private char[] chars;

    private int length;

    public RandomString() {
	this.chars = DEFAULT_CHARS;
	this.length = DEFAULT_LENGTH;
    }

    char[] getChars() {
	return chars;
    }

    int getLength() {
	return length;
    }

    public RandomString ofLength(final int lenth) {
	return setLength(lenth);
    }

    public RandomString withChars(final char[] chars) {
	return setChars(chars);
    }

    RandomString setChars(final char[] chars) {
	this.chars = chars;
	return this;
    }

    RandomString setLength(final int length) {
	this.length = length;
	return this;
    }

    @Override
    public String toString() {
	final StringBuilder sb = new StringBuilder();
	final Random random = new Random();
	for (int i = 0; i < getLength(); i++) {
	    final char c = getChars()[random.nextInt(getChars().length)];
	    sb.append(c);
	}
	return sb.toString();
    }

    public RandomString withCharacters(final char[] chars) {
	return setChars(chars);
    }
}
