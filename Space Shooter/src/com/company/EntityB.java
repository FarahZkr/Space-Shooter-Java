package com.company;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * Interface de tous les Entités B
 */
public interface EntityB {

    /**
     * Méthode pour vitesse et collisions
     * @throws SlickException Exception slick2D
     */
    public void tick() throws SlickException;

    /**
     * Méthode qui affiche l'image
     * @param g Objet Graphics
     */
    public void render(Graphics g);

    /**
     * Méthode pour les bounds des Entités avec un Rectangle
     * @return Bounds du rectangle
     */
    public Rectangle getBounds();
    public double getX();
    public double getY();

}
