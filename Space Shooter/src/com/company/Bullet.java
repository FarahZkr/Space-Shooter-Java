package com.company;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * Classe Bullet
 */
public class Bullet extends GameObject implements EntityA {
    private Controller c;
    private Jeu jeu;
    public Image image;

    /**
     * @param x  Position en x
     * @param y  Position en y
     * @param jeu L'objet jeu
     * @throws SlickException Exception slick2D
     */
    public Bullet(double x, double y, Jeu jeu) throws SlickException {
        super(x, y);
        this.jeu = jeu;
        image = new Image("res/bullet.png");
    }


    /**
     * Méthode qui initialise la vitesse du bullet
     */
    public void tick() {
        y -= 10;
    }


    /**
     * Méthode qui affiche l'image
     * @param g Objet Graphics
     */
    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, null);
    }

    /**
     * Méthode qui prend les valeurs du bullet avec un rectangle pour les collisions
     * @return Bounds du rectangle
     */
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 10, 40);
    }

    public double getX() {
        return x;
    }


    public double getY() {
        return y;
    }

}
