package com.expression.parser;

/**
 * 
 * 
 * @author Sergio Besada
 * 
 */
public class ParserManager {

    private boolean deegre = false;

    // ..... Other configuration values //

    private static ParserManager instance = null;

    protected ParserManager() {

    }

    /**
     * 
     * getInstance
     * 
     * @return
     */
    public static ParserManager getInstance() {
        if (instance == null) {
            instance = new ParserManager();
        }
        return instance;
    }

    /**
     * 
     * isDeegre
     * 
     * @return
     */
    public boolean isDeegre() {
        return this.deegre;
    }

    /**
     * 
     * setDeegre
     * 
     * @param deegre
     */
    public void setDeegre(final boolean deegre) {
        this.deegre = deegre;
    }

}
