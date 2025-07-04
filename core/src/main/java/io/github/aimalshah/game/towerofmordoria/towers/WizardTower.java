package io.github.aimalshah.game.towerofmordoria.towers;

public class WizardTower extends Tower {
    public  WizardTower(float x,float y, int damage){
        super("placement_wizard_tower.png" , x , y ,damage);
        this.range = 7f;
        this.fireCoolDown = 0.5f;
        this.reward = 20;
    }
}
