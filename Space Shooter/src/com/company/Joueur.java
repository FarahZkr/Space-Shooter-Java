package com.company;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

import java.util.Random;

/**
 * Classe Joueur
 */
public class Joueur extends GameObject implements EntityA {

    private Jeu jeu;
    public Image image;
    private Controller c;
    Random r = new Random();


    /**
     * Constructeur de Joueur
     * @param x Position x
     * @param y Position y
     * @param jeu Objet jeu
     * @param c Object controller du jeu
     * @throws SlickException Exception slick2D
     */
    public Joueur(double x, double y, Jeu jeu, Controller c) throws SlickException {
        super(x, y);
        this.jeu = jeu;
        this.c = c;
        image = new Image("res/spaceshipImage.png");
    }

    /**
     * Méthode qui met les bounds du joueur et qui vérifie les collisions
     * @throws SlickException Exception slick2D
     */
    public void tick() throws SlickException {
        if (x <= 0)
            x = 0;
        if (x >= Main.largeur - 100)
            x = Main.largeur - 100;
        if (y < 0)
            y = 0;
        if (y >= Main.hauteur - 78)
            y = Main.hauteur - 78;
        for (int i = 0; i < jeu.b.size(); i++) {
            EntityB tempB = jeu.b.get(i);
            if (Collision.collision(this, tempB)) {

                c.removeEntity(tempB);
                c.addEntity(new Ennemi(r.nextInt(Main.largeur), -10, c, jeu));
                jeu.vie -= 1;
            }

        }
        if (jeu.collecter != 10) {

            for (int i = 0; i < jeu.c.size(); i++) {
                EntityC tempC = jeu.c.get(i);
                if (Collision.collision(this, tempC)) {

                    c.removeEntity(tempC);

                    jeu.collecter += 1;
                    jeu.score += 1;
                }
            }
        }
    }

    /**
     * Méthode qui affiche l'image
     * @param g Objet Graphics
     */
    public void render(Graphics g) {
        g.drawImage(image.getScaledCopy(110, 100), (int) x, (int) y, null);
    }


    /**
     * Méthode qui prend les valeurs du joueur avec un rectangle pour les collisions
     * @return Bounds du rectangle
     */
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 100, 90);
    }


    public double getX() {
        return x;
    }


    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setX(double x) {
        this.x = x;
    }
}
