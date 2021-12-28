package com.company;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;


import java.util.Random;

/**
 * Classe Ennemi
 */
public class Ennemi extends GameObject implements EntityB {

    private Jeu jeu;
    private Controller c;
    private Image asteroide;
    Random r = new Random();
    private SpriteSheet sprite;
private int largeur=100, hauteur=100;
public static int hit=0;



    private int speed = r.nextInt(3)+1;

    /**
     * Constructeur pour l'ennemi/astéroide
     * @param x Variable position en x
     * @param y Variable position en y
     * @param c Objet controller du jeu
     * @param jeu Objet jeu
     * @throws SlickException Exception de slick2D
     */
    public Ennemi(double x, double y, Controller c, Jeu jeu) throws SlickException {
        super(x,y);
        this.c=c;
        this.jeu=jeu;
        sprite= new SpriteSheet("res/spriteAsteroid.png",279,277);
        asteroide=sprite.getSprite(r.nextInt(4),0);

    }

    /**
     * Méthode qui vérifie les collisions et qui initialise la vitesse et position de l'ennemi
     * @throws SlickException Exception slick2D
     */
    public void tick() throws SlickException {

    y+=speed ; //speed of enemy
        if(y>Main.hauteur){

            speed=r.nextInt(3)+1;
           y=-10;
            x=r.nextInt(Main.largeur-150);

        }
        for (int i = 0; i < jeu.a.size() ; i++) {
            EntityA tempA = jeu.a.get(i);
            if(hauteur==100) {
                if (Collision.collision(this, tempA)) {
                    this.setX(x);
                    this.setY(y);
                    c.addEntity(new Ennemi(r.nextInt(600-150),-10,c,jeu));

                    c.listeExplosion.add(new Explosion(this.x,this.y,c,jeu));

                    hauteur = 100 / 2;
                    largeur = 100 / 2;
                    Ennemi division = new Ennemi(this.x + 50, this.y, c, jeu);
                    division.setHauteur(50);
                    division.setLargeur(50);
                    c.removeEntity(tempA);
                    c.addEntity(division);
                    jeu.setEnemyKilled(jeu.getEnemyKilled() + 1);

                }

                else{
                    hit=0;
                }
            }
            if(hauteur==50){
                if(Collision.collision(this,tempA)) {
                    this.setX(x);
                    this.setY(y);
                    c.removeEntity(tempA);
                    c.removeEntity(this);
                    c.addEntity(new Debris(this.x,this.y,c,jeu));
                    c.listeExplosion.add(new Explosion(this.x,this.y,c,jeu));
                }
            }
        }

    }

    /**
     * Méthode qui affiche l'image
     * @param g Objet Graphics
     */
    public void render (Graphics g){

       asteroide.draw((int)x,(int)y,largeur,hauteur);
    }

    /**
     * Méthode qui prend les valeurs avec un rectangle pour les collisions
     * @return Bounds du rectangle
     */
    public Rectangle getBounds(){
        return new Rectangle((int)x,(int)y,hauteur-15, largeur-15);
    }

    public  double getX() {
        return x;
    }

    public  double getY(){
        return y;
    }
    public void setY(double y){
        this.y= y;
    }
    public void setX(double x){
        this.x=x;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }
}
