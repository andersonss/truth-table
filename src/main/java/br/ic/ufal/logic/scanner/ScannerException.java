package br.ic.ufal.logic.scanner;

import br.ic.ufal.logic.TruthTableException;

public class ScannerException extends TruthTableException {
	public static int UNKNOWN_ERROR = 0, ILLEGAL_SYMBOL = 1,
			ILLEGAL_SYMBOLS = 2;
	public static String[] messageTable = {
			"An unknown error occurred while scanning the statement.",
			"Illegal symbol at position @X.",
			"Illegal symbol from positions @X to @Y." };
	private static final long serialVersionUID = 1L;

	private int xValue = -1, yValue = -1;

	public ScannerException() {
		messageType = UNKNOWN_ERROR;
		super.message = messageTable[0];
	}

	public ScannerException(final int messageType, final int xValue) {
		super.message = messageTable[messageType];
		this.messageType = messageType;
		super.message = super.message.replace("@X", String.valueOf(xValue));
		this.xValue = xValue;
		this.yValue = xValue;
	}

	public ScannerException(final int messageType, final int xValue,
			final int yValue) {
		this(messageType, xValue);
		super.message = super.message.replace("@Y", String.valueOf(yValue));
		this.yValue = yValue;
	}

	public int getXValue() {
		return xValue;
	}

	public int getYValue() {
		return yValue;
	}
}
