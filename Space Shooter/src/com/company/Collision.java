package com.company;

/**
 * Classe Collision
 */
public class Collision {


    /**
     * Méthode qui vérifie les collisions d'Entité A avec Entité B
     * @param entA Entité A
     * @param entB Entité B
     * @return boolean
     */
    public static boolean collision(EntityA entA,EntityB entB){
        if(entA.getBounds()==null){
            return false;
        }


            if(entA.getBounds().intersects(entB.getBounds())){
                return true;


        }


        return false;
    }

    /**
     * Méthode qui vérifie les collisions d'Entité B avec Entité A
     * @param entB Entité B
     * @param entA Entité A
     * @return boolean
     */
    public static boolean collision(EntityB entB, EntityA entA){


        if(entB.getBounds()==null){
            return false;
        }

            if(entB.getBounds().intersects(entA.getBounds())){
                return true;
            }


        return false;
    }

    /**
     * Méthode qui vérifie les collisions d'Entité A avec Entité C
     * @param entA Entité A
     * @param entC Entité C
     * @return boolean
     */
    public static boolean collision(EntityA entA, EntityC entC){


        if(entA.getBounds()==null){
            return false;
        }

        if(entA.getBounds().intersects(entC.getBounds())){
            return true;
        }

        return false;
    }

}
