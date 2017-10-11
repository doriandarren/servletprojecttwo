package com.example.ejb.domain;

import java.util.ArrayList;


/**
 * Session Bean implementation class CounterClick
 */

public class Game {
	
	

	private int status; 
	
	private ArrayList<ArrayList<Card>> mesa3x7; 
	
	/*private static final String MAZO[] =
		  { "corazones 1", "corazones 2", "corazones 3", "corazones 4", "corazones 5", "corazones 6",
			"corazones 7", "corazones 8", "corazones 9", "corazones 10", "corazones J", "corazones Q", "corazones K",
			"trebol 1", "trebol 2", "trebol 3", "trebol 4", "trebol 5", "trebol 6", "trebol 7", "trebol 8", "trebol 9",
			"trebol 10", "trebol J", "trebol Q", "trebol K", "picas 1", "picas 2", "picas 3", "picas 4", "picas 5",
			"picas 6", "picas 7", "picas 8", "picas 9", "picas 10", "picas J", "picas Q", "picas K", "diamantes 1",
			"diamantes 2", "diamantes 3", "diamantes 4", "diamantes 5", "diamantes 6", "diamantes 7", "diamantes 8",
			"diamantes 9", "diamantes 10", "diamantes J", "diamantes Q", "diamantes K"
			};*/
	
	
	private static final String MAZO[] =
		  { "uno_corazones", "dos_corazones", "tres_corazones", "cuatro_corazones", "cinco_corazones", "seis_corazones",
			"siete_corazones", "ocho_corazones", "nueve_corazones", "diez_corazones", "j_corazones", "q_corazones", "k_corazones",			
			"uno_trebol", "dos_trebol", "tres_trebol", "cuatro_trebol", "cinco_trebol", "seis_trebol", "siete_trebol", "ocho_trebol", "nueve_trebol",
			"diez_trebol", "j_trebol", "q_trebol", "k_trebol", "uno_picas", "dos_picas", "tres_picas", "cuatro_picas", "cinco_picas",			
			"seis_picas", "siete_picas", "ocho_picas", "nueve_picas", "diez_picas", "j_picas", "q_picas", "k_picas", "uno_diamantes",
			"dos_diamantes", "tres_diamantes", "cuatro_diamantes", "cinco_diamantes", "seis_diamantes", "siete_diamantes", "ocho_diamantes",
			"nueve_diamantes", "diez_diamantes", "j_diamantes", "q_diamantes", "k_diamantes"
			};
	
	
	
	
	
	public Game() {
		super();
		this.status=0;
	}
	 
	 
	public ArrayList<ArrayList<Card>> startGame(){
		 this.status=1; 
		  ArrayList<Card> mesa21 = getAleatorio(); 
		  mesa3x7 = repartirCartas(mesa21); 		
		return mesa3x7; 
		
	}
	
	
	public ArrayList<ArrayList<Card>> nextBaraja(int column){		
		this.status++; 
		ArrayList<Card> mesa21 = reordenarCartas(mesa3x7, column);
		mesa3x7 = repartirCartas(mesa21); 
		return mesa3x7;
	}
	
	
	
	/**
	 * Encuentra un elemento dentro del array y devuelve "true" si encuentra
	 * @param rdm
	 * @param array
	 * @return
	 */
	private static boolean hasElement(int rdm, int array[]){
		boolean retorno=false;		
		for(int i=0; i<array.length; i++){
			if(array[i]==rdm){
				retorno = true;
			}
		}		
		return retorno;
	}
			
	/**
	 * Crea un arreglo y retornar 21 numeros aleatorios 
	 * @return
	 */
	private static ArrayList<Card> getAleatorio(){	
		int array[] = new int[21];			
		int contador=0;		
		while(contador<array.length){
			int  rdm  = (int) (Math.random()* MAZO.length);	
			if(!hasElement(rdm,array)){
				array[contador] = rdm; 
				contador++;
			}		
		}
		
		ArrayList<Card> mazo21 = new ArrayList<Card>(); 
		
		for (int i=0;i<21; i++) {
			mazo21.add(new Card(MAZO[array[i]],array[i],MAZO[array[i]])); 
			
		}
			
		return mazo21;		
	}
	
	
	
	/**
	 * Recibe un arreglo de 21
	 * Devuelve un arreglo multidimencional 3x7
	 * @param baraja21
	 * @return
	 */
	private static ArrayList<ArrayList<Card>> repartirCartas(ArrayList<Card> mazo21){
		
		ArrayList<ArrayList<Card>> mesa = new ArrayList<ArrayList<Card>>(); 		
		mesa.add(new ArrayList<Card>()); 
		mesa.add(new ArrayList<Card>()); 
		mesa.add(new ArrayList<Card>()); 
		int contador=0;	
		for(int i=0; i<7; i++){		
				 mesa.get(0).add(mazo21.get(contador++)); 
				 mesa.get(1).add(mazo21.get(contador++)); 
				 mesa.get(2).add( mazo21.get(contador++)); 
		}		
		
		return mesa;
	}
	
	
	private static ArrayList<Card> reordenarCartas( ArrayList<ArrayList<Card>> mesa, int elegida){
		
		
		ArrayList<ArrayList<Card>> newMesa = new ArrayList<ArrayList<Card>>(); 
		
		
		if (elegida == 1) {			
			newMesa.add(mesa.get(1)); 
			newMesa.add(mesa.get(0));
			newMesa.add(mesa.get(2)); 	
		}else if(elegida == 2) {
			newMesa.add(mesa.get(0)); 
			newMesa.add(mesa.get(1));
			newMesa.add(mesa.get(2)); 
		}else if(elegida == 3) {
			newMesa.add(mesa.get(0)); 
			newMesa.add(mesa.get(2));
			newMesa.add(mesa.get(1)); 
		}
				
		ArrayList<Card> baraja21=new ArrayList<Card>(); 
		baraja21.addAll(newMesa.get(0)); 
		baraja21.addAll(newMesa.get(1)); 
		baraja21.addAll(newMesa.get(2)); 

		return baraja21;
	}
		
	
	public Card cardFinal(int column){	
		status=0;
		return mesa3x7.get(column-1).get(3);	
	}
	
	public int getStatus(){
		return status;		
	}
	
	
}
