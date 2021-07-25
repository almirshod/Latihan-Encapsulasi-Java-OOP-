package com.tutorial;

class Player{
    private String name;
    private int baseAttack;
    private int baseHealth;
    private int level;
    private int incrementHealth;
    private int incrementAttack;
    private int totalDemage;
    private boolean isAlive;
    
    // ini adlah object memmber
    private Armor armor;
    private Weapon weapon;

    public Player(String name){
        this.name = name;
        this.baseHealth = 100;
        this.baseAttack = 10;
        this.level= 1;
        this.incrementAttack = 20;
        this.incrementHealth = 20;
        this.isAlive = true;
    }
    
    public void display(){
        System.out.println("Nama\t\t: " + this.name);
        System.out.println("Level\t\t: " + this.level);
        System.out.println("Health\t\t: " + this.getHealth() + " / " + this.maxHealth());
        System.out.println("Attack\t\t: " + this.getAttackPower());
        System.out.println("Status\t\t: " + this.isAlive + "\n");
    }
    public String getName(){
        return this.name; 
    }

    public int getHealth(){
        return this.maxHealth() - this.totalDemage;
    }

    public void attack(Player opponent){
        // hitung damage
        int damage = this.getAttackPower();
        // print event
        System.out.println("Player: " + this.name + " Is Attacking: " + opponent.getName() + " | Demage: " + damage);
        // atttack opponent
        opponent.deffence(damage);

        this.levelUp();
    }

    public void deffence(int damage){
        // recieve damage
        int deffencePower = this.armor.getDeffencePower();
        int deltaDemage;
        System.out.println(this.name + " Deffence Power: " + deffencePower);
        if (damage > deffencePower){
            deltaDemage = damage - deffencePower;
        } else {
            deltaDemage = 0; 
        }
        System.out.println("Damage Earned = " + deltaDemage + ("\n"));
        // adding total damage
        this.totalDemage += deltaDemage;
        // check is alive
        if (this.getHealth() <= 0){
            this.isAlive = false;
            this.totalDemage = this.maxHealth();
        }

        this.display(); 

    }
    
    public int getAttackPower(){
        return this.baseHealth + this.level*this.incrementAttack + this.weapon.getAttack();
    }

    private void levelUp(){
        this.level++;
    }

    public void setArmor(Armor armor){
        this.armor = armor;
    }

    public void setWeapon(Weapon weapon){
        this.weapon = weapon;
    }

    public int maxHealth(){
        return this.baseHealth + this.level*this.incrementHealth + this.armor.getAddHealth();
    }
}

class Weapon{
    private String name;
    private int attack;

    public Weapon (String name, int attack){
        this.name = name;
        this.attack = attack;
    }

    public int getAttack(){
        return this.attack;
    }
}

class Armor{
    private String name;
    private int strength;
    private int health;

    public Armor(String name, int strength, int health){
        this.name = name;
        this.strength = strength;
        this.health = health;
    }

    public int getAddHealth(){
        return this.strength * 10 + this.health;
    }

    public int getDeffencePower(){
        return this.strength * 2;
    }
}

public class Main {
    public static void main(String[] args) {
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");
        
        Armor armor1 = new Armor("Rompi 1", 10, 100);
        Armor armor2 = new Armor("Rompi 2", 20, 100);
        
        Weapon weapon1 = new Weapon("Sniper", 80);
        Weapon weapon2 = new Weapon("AKM", 75);

        player1.setArmor(armor1);
        player2.setArmor(armor2);
        player1.setWeapon(weapon1);
        player2.setWeapon(weapon2);

        player1.display();
        player2.display();

        player1.attack(player2);
        player2.attack(player1);
        player1.attack(player2);
        player1.attack(player2); 
    }
}












