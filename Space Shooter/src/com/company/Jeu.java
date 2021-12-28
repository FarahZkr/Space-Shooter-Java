package com.company;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.awt.Font;
import java.util.LinkedList;
import java.util.Random;

/**
 * Classe Jeu principal
 */
public class Jeu extends BasicGame {

    public Image background;
    public float x = 240.0f;
    public float y = 300.0f;
    public float speed = 0.2f;
    private Controller controller;
    private int enemyCount = 4;
    private int enemyKilled = 0;
    public LinkedList<EntityA> a;
    public LinkedList<EntityB> b;
    public LinkedList<EntityC> c;
    private Joueur joueur;
    private Ennemi enemy;
    private GameContainer game;
    private Image playButton;
    private Image quitButton;
    private Image titre;
    public static int vie = 3;
    private Image troisVies;
    private Image deuxVies;
    private Image uneVie;
    private SpriteSheet bg;
    private Animation bgAnimation;
    private Image gameOver;
    private Image replay;
    Random r = new Random();
    private int x2 = r.nextInt(Main.largeur - 150);
    private float y3 = -10;
    public static int collecter = 0;
    public static int score = 0;
    public Image affichage;
    TrueTypeFont fnt1;
    java.awt.Font fnt;

    private enum STATE {
        MENU,
        GAME,
        END
    }
    private STATE state = STATE.MENU;

    /**
     * Constructeur avec le titre du jeu
     */
    public Jeu() {
        super("Space Shooter");


    }

    /**
     * Méthode qui initialise les objets et variables
     * @param gameContainer L'objet du Controller du jeu
     * @throws SlickException Exception slick2D
     */
    @Override
    public void init(GameContainer gameContainer) throws SlickException {
        this.game = gameContainer;

        bg = new SpriteSheet("res/backgroundstars.png", 500, 471);
        bgAnimation = new Animation(bg, 45);
        bgAnimation.setLooping(true);

        this.background = new Image("res/background.png");
        this.playButton = new Image("res/playButton.png");
        this.quitButton = new Image("res/quitButton.png");
        this.troisVies = new Image("res/3hearts.png");
        this.deuxVies = new Image("res/2hearts.png");
        this.uneVie = new Image("res/1heart.png");
        this.titre = new Image("res/titre.png");
        this.gameOver = new Image("res/gameover.png");
        this.replay = new Image("res/replay.png");
        this.affichage = new Image("res/score.png");

        fnt = new java.awt.Font("Monospaced", Font.BOLD, 35);
        fnt1 = new TrueTypeFont(fnt, true);



        controller = new Controller(this);
        controller.addEnemy(enemyCount);
        enemy = new Ennemi(x2, y3, controller, this);
        joueur = new Joueur(x, y, this, controller);
        a = controller.getEntityA();
        b = controller.getEntityB();
        c = controller.getEntityC();
    }

    /**
     * Méthode qui update les ticks des Entités et le jeu
     * @param gameContainer  L'objet du Controller du jeu
     * @param i Variable qui aide avec la vitesse et input des keys
     * @throws SlickException Exception slick2D
     */
    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        if (state == STATE.GAME) {
            joueur.tick();
            controller.tick();

            if (x <= 0)
                x = 0;
            if (x >= Main.largeur - 100)
                x = Main.largeur - 100;
            if (y < 0)
                y = 0;
            if (y >= Main.hauteur - 78)
                y = Main.hauteur - 78;
        } else if (state == STATE.MENU) {
            int posX = Mouse.getX();
            int posY = Mouse.getY();

            //PLAY BUTTON
            if ((posX > 105 && posX < 275) && (posY > 225 && posY < 290)) {

                if (Mouse.isButtonDown(0)) {
                    state = STATE.GAME;

                }
            }
            //QUIT BUTTON
            if ((posX > 320 && posX < 488) && (posY > 225 && posY < 290)) {

                if (Mouse.isButtonDown(0)) {
                    System.exit(0);
                }
            }

        }

        joueur.setX(x);
        joueur.setY(y);

        if (state == STATE.GAME) {
            Input input = gameContainer.getInput();
            if (input.isKeyDown(Input.KEY_RIGHT) || input.isKeyDown(Input.KEY_D)) {
                x += speed * i;
            }
            if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_A)) {
                x -= speed * i;
            }
            if (input.isKeyDown(Input.KEY_DOWN) || input.isKeyDown(Input.KEY_S)) {
                y += speed * i;
            }
            if (input.isKeyDown(Input.KEY_UP) || input.isKeyDown(Input.KEY_W)) {
                y -= speed * i;
            }
            if (input.isKeyPressed(Input.KEY_SPACE)) {
                controller.addEntity(new Bullet(x + 40, y - 65, this));
            }


        } else if (state == STATE.END) {


            b.clear();
            a.clear();
            c.clear();
            int posX = Mouse.getX();
            int posY = Mouse.getY();

            //PLAY BUTTON
            if ((posX > 105 && posX < 275) && (posY > 225 && posY < 290)) {

                if (Mouse.isButtonDown(0)) {
                    state = STATE.GAME;
                    vie = 3;

                    enemyCount = 4;
                    enemyKilled = 0;
                    score = 0;
                    collecter = 0;
                    x = 240;
                    y = 300;


                    for (int j = 0; j < enemyCount; j++) {

                        controller.addEntity(new Ennemi(r.nextInt(Main.largeur - 150), -10, controller, this));
                    }

                }
            }
            //QUIT BUTTON
            if ((posX > 320 && posX < 488) && (posY > 225 && posY < 290)) {

                if (Mouse.isButtonDown(0)) {
                    System.exit(0);
                }
            }
        }


    }

    /**
     * Méthode qui permet d'afficher toutes les images du jeu
     * @param gameContainer L'objet du Controller du jeu
     * @param graphics Object Graphics
     * @throws SlickException Exception slick2D
     */
    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {


        if (state == STATE.GAME) {
            bgAnimation.draw(0, 0, 600, 600);

            joueur.render(graphics);

            controller.render(graphics);

            fnt1.drawString(540, -3, String.valueOf(score), Color.cyan);


            if (collecter == 10) {

                graphics.drawString("Cliquez sur E pour lancer la cargaison à Mars!", 100, 75);
            }
            graphics.drawImage(this.affichage.getScaledCopy(70, 30), 450, 5);

            if (vie == 3) {
                graphics.drawImage(this.troisVies.getScaledCopy(119, 70), 5, 0);
            } else if (vie == 2) {
                graphics.drawImage(this.deuxVies.getScaledCopy(109, 65), 5, 0);
            } else if (vie == 1) {
                graphics.drawImage(this.uneVie.getScaledCopy(109, 65), 5, 0);
            }


        } else if (state == STATE.MENU) {
            //MENU
            background.draw(0, 0);

            graphics.drawImage(this.titre.getScaledCopy(450, 288), 80, 0);

            graphics.drawImage(this.playButton.getScaledCopy(190, 150), 95, 275);

            graphics.drawImage(this.quitButton.getScaledCopy(190, 150), 305, 275);


        }
        if (vie == 0) {
            state = STATE.END;
            background.draw(0, 0);
            graphics.drawImage(this.gameOver, 125, 100);
            graphics.drawImage(this.replay.getScaledCopy(190, 200), 100, 257);

            graphics.drawImage(this.quitButton.getScaledCopy(190, 150), 300, 275);
        }
    }


    /**
     * Méthode qui get le input de certain keys
     * @param key Variable key
     * @param c Variable char
     */
    @Override
    public void keyReleased(int key, char c) {
        if (Input.KEY_ESCAPE == key) {
            this.game.exit();
        }
        if (Input.KEY_E == key) {
            collecter = 0;
        }
    }



    public int getEnemyKilled() {
        return enemyKilled;
    }

    public void setEnemyKilled(int enemyKilled) {
        this.enemyKilled = enemyKilled;
    }

}
