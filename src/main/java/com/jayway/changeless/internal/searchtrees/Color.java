package com.jayway.changeless.internal.searchtrees;

public enum Color {
	RED, BLACK, NEGATIVE_BLACK, DOUBLE_BLACK;

	public Color darken() {
		switch (this) {
		case BLACK:
			return DOUBLE_BLACK;
		case RED:
			return BLACK;
		case NEGATIVE_BLACK:
			return RED;
		default:
			throw new IllegalStateException("Can't darken: " + this);
		}
	}

	public Color lighten() {
		switch (this) {
		case DOUBLE_BLACK:
			return BLACK;
		case BLACK:
			return RED;
		case RED:
			return NEGATIVE_BLACK;
		default:
			throw new IllegalStateException("Can't lighten: " + this);
		}
	}
}
