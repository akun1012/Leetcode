package chessOOP;

public class Game {

	Player whitePlayer;
	Player blackPlayer;
	Block[][] board;
	
	public Game(){
		board = new Block[8][8];
		whitePlayer = new Player();
		blackPlayer = new Player();
	}
	
	public void play(){
		
	}
	
	public boolean validateMove(Piece p, Move m){
		return true;
	}
	
	public void updateBoard(Piece p, Move m){
		
	}
	
	public void checkKills(Piece p, Move m){
		
	}
	
	public boolean isGameOver(Piece p, Move m){
		return true;
	}
	

}
