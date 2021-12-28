package com.company;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

/**
 * Classe Controller
 */
public class Controller {

    private LinkedList<EntityA> a = new LinkedList<EntityA>();
    private LinkedList<EntityB> b = new LinkedList<EntityB>();
    private LinkedList<EntityC> c = new LinkedList<EntityC>();

    EntityA entA;
    EntityB entB;
    EntityC entC;
    private Jeu jeu;
    public ArrayList<Explosion> listeExplosion= new ArrayList<>();
    Random r = new Random();

    /**
     * Constructeur de Controller
     * @param jeu L'objet jeu
     * @throws SlickException Exception slick2D
     */
    public Controller(Jeu jeu) throws SlickException {
        this.jeu=jeu;


    }

    /**
     * Méthode qui rajoute un astéroide
     * @param enemyCount Nombres d'astéroides
     * @throws SlickException Exception slick2D
     */
    public void addEnemy(int enemyCount) throws SlickException {
    for (int i = 0; i < enemyCount ; i++) {
        addEntity(new Ennemi(r.nextInt(Main.largeur-150), -10,this,jeu));
    }
}

    /**
     * Méthode qui prend tous les ticks des Entités A, B et C
     * @throws SlickException Exception slick2D
     */
    public void tick() throws SlickException {
        //Pour ENTITY A
        for (int i = 0; i < a.size(); i++) {
            entA=a.get(i);
            entA.tick();
        }
        //POUR ENTITY B
        for (int i = 0; i < b.size(); i++) {
            entB=b.get(i);
            entB.tick();
        }
        //Pour ENTITY C
        for (int i = 0; i < c.size(); i++) {
            entC=c.get(i);
            entC.tick();
        }
        for (int i = 0; i < listeExplosion.size(); i++) {
            listeExplosion.get(i).tick();
        }
    }

    /**
     * Méthode qui permet d'afficher les images pour Entité A,B et C
     * @param g Objet Graphics
     */
    public void render(Graphics g){
        //Pour A
        for (int i = 0; i < a.size(); i++) {
            entA=a.get(i);
            entA.render(g);
        }
        //POUR B
        for (int i = 0; i < b.size(); i++) {
            entB=b.get(i);
            entB.render(g);
        }
        //Pour C
        for (int i = 0; i < c.size(); i++) {
            entC=c.get(i);
            entC.render(g);
        }

            for (int i = 0; i < listeExplosion.size(); i++) {
                listeExplosion.get(i).render(g);


        }


    }
  public void addEntity(EntityA block){
        a.add(block);
    }
    public void removeEntity(EntityA block){
        a.remove(block);
    }
    public void addEntity(EntityB block){
        b.add(block);
    }
    public void removeEntity(EntityB block){
        b.remove(block);
    }
    public void addEntity(EntityC block){
        c.add(block);
    }
    public void removeEntity(EntityC block){
        c.remove(block);
    }

    public LinkedList<EntityA> getEntityA(){
        return a;
    }
    public LinkedList<EntityB> getEntityB(){
        return b;
    }
    public LinkedList<EntityC> getEntityC(){
        return c;
    }
}
