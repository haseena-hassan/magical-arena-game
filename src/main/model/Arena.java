package src.main.model;

import src.main.service.Dice;

// TODO: Refactor Arena to hold only dice attribute and handle simple fight logic between any attacker and defender
public class Arena {
    private final Player player1;
    private final Player player2;
    private final Dice dice;

    public Arena(Player player1, Player player2, Dice dice) {
        this.player1 = player1;
        this.player2 = player2;
        this.dice = dice;
    }

    // TODO: Refactor startGame() method to fight function between any 2 players, which is attacker and defender
    public void startGame() {
        Player attacker = (player1.getHealth() <= player2.getHealth()) ? player1 : player2;
        Player defender = (attacker == player1) ? player2 : player1;

        while(player1.isAlive() && player2.isAlive()) {
            takeTurns(attacker, defender);

            // Swap roles for next round
            Player temp = attacker;
            attacker = defender;
            defender = temp;
        }
    }

    private void takeTurns(Player attacker, Player defender) {
        int attackRoll = dice.roll();
        int defenseRoll = dice.roll();

        int attackDamage = attacker.getAttack() * attackRoll;
        int defenseStrength = defender.getStrength() * defenseRoll;

        int damage = Math.max(0, attackDamage - defenseStrength);
        defender.setHealth(defender.getHealth() - damage);

        System.out.println(attacker.getName() + " attacks with roll " + attackRoll + ", causing a damage of " + attackDamage + ".");
        System.out.println(defender.getName() + " defends with roll " + defenseRoll + ", reducing damage by " + defenseStrength + ".");
        System.out.println(defender.getName() + "'s health: " + defender.getHealth());
        System.out.println();
    }

    // TODO: Incorporate gameOver() and determineWinner() logic into fight's logic making it simpler
    public boolean gameOver() {
        return !player1.isAlive() || !player2.isAlive();
    }

    public void determineWinner() {
        if(gameOver()) {
            String winner = player1.isAlive() ? player1.getName() : player2.getName();
            System.out.println("Game Over! " + winner + " wins!");
        }
        else {
            throw new IllegalStateException("The game is not over yet. Cannot determine the winner.");
        }
    }

    @Override
    public String toString() {
        return "In this Arena : " + player1.getName() +  " is playing against " + player2.getName() + ".";
    }
}














