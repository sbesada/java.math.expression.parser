package com.expression.parser.function;

public class Test {
	public static void main(final String[] args) {

		final long time1 = System.currentTimeMillis();
		for (int i = 0; i < 10000000; i++) {

			final char character = 'k';

			isValidNumericAndCharacter3(character);
		}

		final long time2 = System.currentTimeMillis();
		System.out.println("time:" + (time2 - time1));
	}

	private static boolean isValidNumericAndCharacter(final char character) {
		boolean result = false;
		switch (character) {
		case 'a':
			result = true;
			break;
		case 'b':
			result = true;
			break;
		case 'c':
			result = true;
			break;
		case 'd':
			result = true;
			break;
		case 'e':
			result = true;
			break;
		case 'f':
			result = true;
			break;
		case 'g':
			result = true;
			break;
		case 'h':
			result = true;
			break;
		case 'i':
			result = true;
			break;
		case 'j':
			result = true;
			break;
		case 'k':
			result = true;
			break;

		case 'l':
			result = true;
			break;
		case 'm':
			result = true;
			break;
		case 'n':
			result = true;
			break;
		case 'o':
			result = true;
			break;
		case 'p':
			result = true;
			break;
		case 'q':
			result = true;
			break;
		case 'r':
			result = true;
			break;
		case 's':
			result = true;
			break;
		case 't':
			result = true;
			break;
		case 'u':
			result = true;
			break;
		case 'v':
			result = true;
			break;
		case 'w':
			result = true;
			break;
		case 'x':
			result = true;
			break;
		case 'y':
			result = true;
			break;
		case 'z':
			result = true;
			break;
		case '0':
			result = true;
			break;
		case '1':
			result = true;
			break;
		case '2':
			result = true;
			break;
		case '3':
			result = true;
			break;
		case '4':
			result = true;
			break;
		case '5':
			result = true;
			break;
		case '6':
			result = true;
			break;
		case '7':
			result = true;
			break;
		case '8':
			result = true;
			break;
		case '9':
			result = true;
			break;
		default:
			result = false;
			break;
		}

		return result;
	}

	private static boolean isValidNumericAndCharacter2(final char character) {
		boolean result = false;
		if ((character >= 'a' && character <= 'z') || (character >= '1' && character <= '9')) {
			result = true;
		}

		return result;

	}

	private static boolean isValidNumericAndCharacter3(final char character) {
		boolean result = false;

		if (character == 'a') {
			result = true;
		} else if (character == 'b') {
			result = true;
		} else if (character == 'c') {
			result = true;
		} else if (character == 'd') {
			result = true;
		} else if (character == 'e') {
			result = true;
		} else if (character == 'f') {
			result = true;
		} else if (character == 'g') {
			result = true;
		} else if (character == 'h') {
			result = true;
		} else if (character == 'i') {
			result = true;
		} else if (character == 'j') {
			result = true;
		} else if (character == 'k') {
			result = true;
		}

		return result;

	}
}
