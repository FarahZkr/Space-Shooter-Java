package com.company;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;

import java.util.Random;

public class Debris extends GameObject implements EntityC {

    private Image briser;

    private Jeu jeu;
    private Controller c;
    Random r = new Random();
    private int speed = r.nextInt(2)+1;
    private SpriteSheet debrisSprite;

    /**
     *
     * @param x Position x
     * @param y Position y
     * @param c Objet controller du jeu
     * @param jeu Objet jeu
     * @throws SlickException  Exception de slick2D
     */
    public Debris (double x, double y, Controller c, Jeu jeu) throws SlickException {
        super(x,y);
        this.c=c;
        this.jeu=jeu;
        debrisSprite= new SpriteSheet("res/debrisSprite.png",900,1017);
        briser=debrisSprite.getSprite(r.nextInt(4),0);
    }

    /**
     * Méthode pour faire descendre les débris/minérais
     */
    public void tick() {
        //speed of enemy
        y+=speed;


    }

    /**
     * Méthode qui affiche l'image
     * @param g Objet Graphics
     */
    public void render(Graphics g) {
    briser.getScaledCopy(60,60).draw((float)x,(float)y);
    }

    /**
     * Méthode qui prend les valeurs avec un rectangle pour les collisions
     * @return Bounds du rectangle
     */
    public Rectangle getBounds() {
            return new Rectangle((int)x,(int)y,50, 50);
    }



}
