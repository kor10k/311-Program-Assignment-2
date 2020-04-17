package dilemma.homework;

import java.util.Arrays;
import java.util.Collections;


public class Prisoner{
	
	// a prisoner has type, currentPayoff, ID, played
	
	private int type;
	private int currentPayoff;
	private int ID;
	private boolean played;
	
	public Prisoner() {
		this.type = 0;
		this.currentPayoff = 0;
		this.ID = 0;
		this.played = false;
	}
	
	public Prisoner(int xType, int xPayoff, int xID, boolean xPlay) {
		this.type = xType;
		this.currentPayoff = xPayoff;
		this.ID = xID;
		this.played = xPlay;
	}
	
	// add to current payoff
	public int addPayoff(int howMuch) {
		this.currentPayoff += howMuch;
		return this.currentPayoff;
	}
	
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int n = 100; // number of players
		int m = 5; // number of games played
		int p = 5; // percent of population
		int k = 20; // generation
		
		Prisoner[] players = new Prisoner[n];
		
		// change distribution of population here
		
		
		for (int i = 0; i < n; i++) {
			players[i] = new Prisoner();
			players[i].type = i % 4;
			players[i].ID = i;
		}
		
		// for question 2
		/*
		for (int i = 0; i < 30; i++) {
			players[i] = new Prisoner();
			//players[i].type = i % 4;
			players[i].ID = i;
			players[i].type = 0;
		}
		for (int i = 30; i < 60; i++) {
			players[i] = new Prisoner();
			//players[i].type = i % 4;
			players[i].ID = i;
			players[i].type = 1;
		}
		for (int i = 60; i < 100; i++) {
			players[i] = new Prisoner();
			//players[i].type = i % 4;
			players[i].ID = i;
			players[i].type = 3;
		}
		*/
		
		
		for (int i = 0; i < k; i++) { // for k generations
			for (int a = 0; a < n; a++) { // player 1
				for (int b = a+1; b < n; b++) { // player 2
					boolean aDefect = false;
					boolean bDefect = false;
					
					for (int j = 0; j < m; j++) { // play m games						
						if(players[a].type == 0) { // when player 1 is T4T
							
							aDefect = bDefect;
							
							if(players[b].type == 0) { // when player 2 is T4T
								bDefect = aDefect;
							}
							else if(players[b].type == 1) { // when player 2 is Grudger
								if (aDefect == true) {
									bDefect = true;
								}
							}
							else if(players[b].type == 2) { // when player 2 is Always Cooperate
								bDefect = false;
							}
							else { // when player 2 is Always Defect 
								bDefect = true;
							}
						}
						else if(players[a].type == 1) { // when a is Grudger
							
							if(bDefect == true) {
								aDefect = true;
							}
							
							if(players[b].type == 0) { // when player 2 is T4T
								bDefect = aDefect;
							}
							else if(players[b].type == 1) { // when player 2 is Grudger
								if (aDefect == true) {
									bDefect = true;
								}
							}
							else if(players[b].type == 2) { // when player 2 is Always Cooperate
								bDefect = false;
							}
							else { // when player 2 is Always Defect 
								bDefect = true;
							}
						}
						else if(players[a].type == 2) { // when a is Always Cooperate
							
							aDefect = false;
							
							if(players[b].type == 0) { // when player 2 is T4T
								bDefect = aDefect;
							}
							else if(players[b].type == 1) { // when player 2 is Grudger
								if (aDefect == true) {
									bDefect = true;
								}
							}
							else if(players[b].type == 2) { // when player 2 is Always Cooperate
								bDefect = false;
							}
							else { // when player 2 is Always Defect 
								bDefect = true;
							}
						}
						else { // when a is Always Defect
							
							aDefect = true;
							
							if(players[b].type == 0) { // when player 2 is T4T
								bDefect = aDefect;
							}
							else if(players[b].type == 1) { // when player 2 is Grudger
								if (aDefect == true) {
									bDefect = true;
								}
							}
							else if(players[b].type == 2) { // when player 2 is Always Cooperate
								bDefect = false;
							}
							else { // when player 2 is Always Defect 
								bDefect = true;
							}
						}
						
						// for Extra Credit
						/*
						if (j % 3 == 0) {
							bDefect = !bDefect;
						}
						*/
						//
						
						if (aDefect == false && bDefect == false) { // when both cooperate
							
							players[a].addPayoff(3);
							players[b].addPayoff(3);
						}
						else if (aDefect == false && bDefect == true) {
							
							players[a].addPayoff(0);
							players[b].addPayoff(5);
						}
						else if (aDefect == true && bDefect == false) {
							
							players[a].addPayoff(5);
							players[b].addPayoff(0);
						}
						else {
							
							players[a].addPayoff(1);
							players[b].addPayoff(1);
						}
												
					}
				}
			}
			
			// sort array in ascending order after playing m games
			Arrays.sort(players, (a,b) -> Integer.compare(a.currentPayoff, b.currentPayoff)); 
						
			int percent = (int) ((float)n / 100 * (float)p);
			float type0p = 0;
			float type1p = 0;
			float type2p = 0;
			float type3p = 0;
			int type0pay = 0;
			int type1pay = 0;
			int type2pay = 0;
			int type3pay = 0;
			
			// keep track of the number of each type in the population
			// keep track of the total payoff of each type
			for (int z = 0; z < n; z++) {
				if (players[z].type == 0) {
					type0p ++;
					type0pay += players[z].currentPayoff;
				}
				else if (players[z].type == 1) {
					type1p++;
					type1pay += players[z].currentPayoff;
				}
				else if (players[z].type == 2) {
					type2p++;
					type2pay += players[z].currentPayoff;
				}
				else {
					type3p++;
					type3pay += players[z].currentPayoff;
				}
				
				
			}
			
			
			// Print result per generation		
			
		
			System.out.println("1. Population Pecentage:");
			System.out.print("Gen " + (i+1) + ":  " + "T4T: " + (int)((type0p/n)*100) + "%  ");
			System.out.print("G: " + (int)((type1p/n)*100) + "%  ");
			System.out.print("AC: " + (int)((type2p/n)*100) + "%  ");
			System.out.println("AD: " + (int)((type3p/n)*100) + "%  ");
			
			
			System.out.println("2. Payoff for Each Type:");
			System.out.print("Gen " + (i+1) + ":  " + "T4T: " + type0pay + "  ");
			System.out.print("G: " + type1pay + "  ");
			System.out.print("AC: " + type2pay + "  ");
			System.out.print("AD: " + type3pay + "  ");
			System.out.println("Total: " + (type0pay + type1pay + type2pay + type3pay));
			
			System.out.println("3. Average Payoff for Each Type:");
			System.out.print("Gen " + (i+1) + ":  " + "T4T: " + type0pay/type0p + "  ");
			System.out.print("G: " + type1pay/type1p + "  ");
			System.out.print("AC: " + type2pay/type2p + "  ");
			System.out.println("AD: " + type3pay/type3p );
			System.out.println();
			
			
			
			// change the population by replacing the bottom p% with the top p%
			for (int b = 0; b < percent; b++) {
				players[b].type = players[players.length - 1 - b].type;
			}
			
			// reset each player's payoff
			for (int b = 0; b < players.length; b++) {
				players[b].currentPayoff = 0;
			}
			
		}
		
	}

	
}
