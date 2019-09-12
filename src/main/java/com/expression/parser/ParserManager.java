package com.expression.parser;

/**
 * The Class ParserManager.
 */
public class ParserManager {

	/** The deegre. */
	private boolean deegre = false;

	// ..... Other configuration values //

	/** The instance. */
	private static ParserManager instance = null;

	/**
	 * Instantiates a new parser manager.
	 */
	protected ParserManager() {

	}

	/**
	 * getInstance.
	 *
	 * @return single instance of ParserManager
	 */
	public static ParserManager getInstance() {
		if (instance == null) {
			instance = new ParserManager();
		}
		return instance;
	}

	/**
	 * isDeegre.
	 *
	 * @return true, if is deegre
	 */
	public boolean isDeegre() {
		return deegre;
	}

	/**
	 * setDeegre.
	 *
	 * @param deegre the new deegre
	 */
	public void setDeegre(final boolean deegre) {
		this.deegre = deegre;
	}

}
