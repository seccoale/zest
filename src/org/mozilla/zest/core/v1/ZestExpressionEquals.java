/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.zest.core.v1;

import java.util.regex.Pattern;

import org.mozilla.zest.impl.ZestUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class ZestExpressionRegex.
 */
public class ZestExpressionEquals extends ZestExpression {
	private static final Pattern pattern = Pattern
			.compile("(NOT\\s)?(CASE\\sEXACT\\s)?Value:\\s"
					+ ZestUtils.START_VARIABLE_REGEX + "(\\S*\\s*)*"
					+ ZestUtils.END_VARIABLE_REGEX + "\\sin\\s"
					+ ZestUtils.START_VARIABLE_REGEX + "\\S+"
					+ ZestUtils.END_VARIABLE_REGEX);// TODO make it better
	// Value: Sample string in location

	/** The value to compare with. */
	private String value;

	private String variableName;

	// /** The variableName which will be assigned to. */
	// private String variableName;

	/** The case exact. */
	private boolean caseExact = false;

	/**
	 * Instantiates a new zest expression regex.
	 */
	public ZestExpressionEquals() {
		this("", null, false, false);
	}

	/**
	 * Instantiates a new zest expression regex.
	 * 
	 * @param variableName
	 *            the variableName
	 * @param regex
	 *            the regex
	 */
	public ZestExpressionEquals(String variableName, String regex) {
		this(variableName, regex, false, false);
	}

	/**
	 * Instantiates a new zest expression regex.
	 * 
	 * @param variableName
	 *            the variableName
	 * @param value
	 *            the value
	 * @param caseExact
	 *            the case exact
	 * @param inverse
	 *            the inverse
	 */
	public ZestExpressionEquals(String variableName, String value,
			boolean caseExact, boolean inverse) {
		super();
		this.setVariableName(variableName);
		// this.variableName = variableName;
		this.value = value;
		this.caseExact = caseExact;
		this.setInverse(inverse);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.mozilla.zest.core.v1.ZestExpressionElement#isTrue(org.mozilla.zest
	 * .core.v1.ZestResponse)
	 */
	public boolean isTrue (ZestRuntime runtime) {
		String str = runtime.getVariable(variableName);		
		if (str == null) {
			return false;
		}

		if (this.caseExact) {
			return str.equals(value);
		} else {
			return str.equalsIgnoreCase(value);
		}
	}

	// /**
	// * Gets the variable name.
	// *
	// * @return the variable name
	// */
	// public String getVariableName() {
	// return variableName;
	// }

	// /**
	// * Sets the variable name.
	// *
	// * @param variableName the new variable name
	// */
	// public void setVariableName(String variableName) {
	// this.variableName = variableName;
	// }

	/**
	 * Gets the value.
	 * 
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 * 
	 * @param value
	 *            the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Checks if is case exact.
	 * 
	 * @return true, if is case exact
	 */
	public boolean isCaseExact() {
		return caseExact;
	}

	/**
	 * Sets the case exact.
	 * 
	 * @param caseExact
	 *            the new case exact
	 */
	public void setCaseExact(boolean caseExact) {
		this.caseExact = caseExact;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mozilla.zest.core.v1.ZestExpression#isLeaf()
	 */
	@Override
	public boolean isLeaf() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.mozilla.zest.core.v1.ZestExpression#deepCopy()
	 */
	@Override
	public ZestExpressionEquals deepCopy() {
		return new ZestExpressionEquals(this.getVariableName(),
				this.getValue(), this.isCaseExact(), this.isInverse());
	}

	@Override
	public String toString() {
		String expression = (isInverse() ? "NOT " : "")
				+ (isCaseExact() ? "CASE EXACT " : "") + "Value: "
				+ ZestUtils.START_VARIABLE + value + ZestUtils.END_VARIABLE
				+ " in " + ZestUtils.START_VARIABLE + variableName
				+ ZestUtils.END_VARIABLE;
		return expression;
	}

	public static boolean isLiteralInstance(String string) {
		if (string == null || string.isEmpty()) {
			return false;
		}
		return pattern.matcher(string).matches();
	}

	public static Pattern getPattern() {
		return pattern;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public String getVariableName() {
		return this.variableName;
	}

}
