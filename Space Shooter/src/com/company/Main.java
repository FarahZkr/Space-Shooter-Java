package com.company;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/**
 * Classe Main
 */
public class Main {

public static int largeur= 600, hauteur= 600;

    /**
     * Méthode qui permet de commencer le jeu
     * @param args Non utilisé
     */
    public static void main(String[] args) {

        try{
            AppGameContainer app = new AppGameContainer(new Jeu());

            app.setDisplayMode(largeur, hauteur,false);
            app.setShowFPS(false);
            app.setTargetFrameRate(30);
            app.setVSync(true);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }


    }
}
