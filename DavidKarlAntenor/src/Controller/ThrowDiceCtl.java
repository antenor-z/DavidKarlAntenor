package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.GameException;
import Model.GameState;
import Model.Event.ChangeViewEvent;
import Model.Event.ViewType;
import View.Gameboard.GameControlPanel;

public class ThrowDiceCtl implements ActionListener {

	ActionListener _controller;
    public ThrowDiceCtl(GameControlPanel gamePanel, ActionListener controller) {
    	_controller = controller;
    }

    public void actionPerformed(ActionEvent e) {
        GameState gameState = GameState.getInstance();
        gameState.nextPlayer();
        int[] dices = gameState.throwDice();

        try {
            gameState.goFoward(dices[0], dices[1]);

            String currentTile = gameState.getTileType();

            if (currentTile != null) {
                switch (gameState.getTileType()) {
                    case "Land" -> {
                        if (!gameState.canBuyLand() &&
                                !gameState.canBuildHouse() &&
                                !gameState.canBuildHotel()) {
                            try {
                                gameState.landPayRent();
                            } catch (GameException e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                    case "Money" -> {
                        gameState.moneyExecute();
                    }
                    case "LuckSetback" -> {
                        gameState.luckSetbackPickCard();
                    }
                    case "GoToPrision" -> {
                        gameState.gotoPrision();
                    }
                    case "Company" -> {
                        if (gameState.companyGetOwner() != null && gameState.companyGetOwner() != gameState.turn) {
                            gameState.companyPayRent(gameState.dices[0], gameState.dices[1]);
                        }
                    }
                    case "Prision" -> {
                    	gameState.processCard();
                    }
                    default -> {
                    }
                }
            }
        } catch (Exception e2) {
            System.out.println(e2.getMessage());
        }
        if(gameState.numberOfNotBankrupt() < 2)
        {
        	_controller.actionPerformed(new ChangeViewEvent(this, 200, "", ViewType.END));
        }
    }
}
