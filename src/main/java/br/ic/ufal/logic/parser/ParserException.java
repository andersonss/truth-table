package br.ic.ufal.logic.parser;

import br.ic.ufal.logic.TruthTableException;

/**
 * 
 * @author anderson
 * 
 */
public class ParserException extends TruthTableException {
	public static int UNKNOWN_ERROR = 0, MISSING_CONNECTIVE = 1,
			MISSING_STATEMENT = 2, MISSING_STATEMENT_IN_PARENTHESES = 3,
			ILLEGAL_USE_OF_PARENTHESES = 4, MISSING_OPEN_PARENTHESIS = 5,
			MISSING_CLOSE_PARENTHESIS = 6;
	public static String[] messageTable = {
			"An unknown error occurred while parsing the expression.",
			"Missing connective at position @X.",
			"Missing statement at position @X.",
			"Missing statement inside parentheses at position @X.",
			"Illegal use of parentheses at position @X.",
			"Missing opening parenthesis.",
			"Missing closing parenthesis." };
	private static final long serialVersionUID = 1L;

	private int xValue = -1;
	private boolean selectAll = false;

	public ParserException() {
		messageType = UNKNOWN_ERROR;
		super.message = messageTable[0];
	}

	public ParserException(final int messageType) {
		super.message = messageTable[messageType];
		this.messageType = messageType;
	}

	public ParserException(final int messageType, final int xValue) {
		this(messageType);
		super.message = super.message.replace("@X", String.valueOf(xValue));
		this.xValue = xValue;
	}

	public ParserException(final int messageType, final boolean selectAll) {
		super.message = messageTable[messageType];
		this.messageType = messageType;
		this.selectAll = selectAll;
	}

	public int getXValue() {
		return xValue;
	}

	public boolean selectAll() {
		return selectAll;
	}
}
