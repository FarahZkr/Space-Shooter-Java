package com.company;




/**
 * Classe Parent GameObject
 */
public class GameObject {
    public double x,y;

    /**
     * Constructeur pour les sous-classes
     * @param x Position en x
     * @param y Position en y
     */
    public GameObject (double x, double y){
        this.x=x;
        this.y=y;
    }

}
