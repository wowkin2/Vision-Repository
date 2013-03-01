package com.checkers;

import java.util.LinkedList;
import java.util.Random;
import com.models.Move;

public class Logic {
	private static Random rand;
	Logic(){
		rand = new Random();
	}
	public int getBestMove(LinkedList<Move> ListMoves){

			return rand.nextInt(ListMoves.size());
		
	}
}
