package com.example.ejb.bussines;

import java.util.ArrayList;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import com.example.ejb.domain.Card;
import com.example.ejb.domain.Game;





/**
 * Session Bean implementation class CounterClick
 */
@Stateful
@LocalBean
public class GameService {
	
	Game game;
	
	public GameService(){		
		game= new Game(); 
	}
	

	
	
	public ArrayList<ArrayList<Card>> startGame(){
	 return game.startGame(); 		
	}
	
	
	public ArrayList<ArrayList<Card>> nextBaraja(int column){		
	 return game.nextBaraja(column); 
	}
	
	
	public int getStatus(){
		return game.getStatus(); 
	}
	
	public Card cardFinal(int column){
		return game.cardFinal(column);		
	}

	
	
}
