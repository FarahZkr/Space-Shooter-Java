package com.company;

import org.newdawn.slick.*;


import java.util.Random;

/**
 * Classe Explosion
 */
public class Explosion {


    private Jeu jeu;
    private Controller c;
    public Image asteroide;
    Random r = new Random();
    public Animation explosionAnimation;
    public SpriteSheet explosion;
    public boolean detruit;
    private double x, y;

    /**
     * Constructeur d'Explosion
     * @param x Variable position en x
     * @param y Variable position en y
     * @param c Objet controller du jeu
     * @param jeu Objet jeu
     * @throws SlickException  Exception de slick2D
     */
    public Explosion(double x, double y, Controller c, Jeu jeu) throws SlickException {
        this.x = x;
        this.y = y;
        this.c = c;
        this.jeu = jeu;

        explosion = new SpriteSheet("res/Explosion.png", 96, 96);
        explosionAnimation = new Animation(explosion, 76);
        explosionAnimation.setLooping(false);
    }

    /**
     * Méthode qui vérifie quand le loop de l'animation s'arrête
     */
    public void tick() {
        if (explosionAnimation.isStopped()) {
            detruit = true;
        }

    }

    /**
     * Méthode qui affiche l'animation de l'explosion
     * @param g Objet Graphics
     */
    public void render(Graphics g) {
        if (detruit == false) {
            explosionAnimation.draw((float) x - 7, (float) y - 3, 96, 96);
        }
    }


}


