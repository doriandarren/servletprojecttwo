package servletprojecttwo;

import java.util.ArrayList;

import org.junit.Test;

import com.example.ejb.domain.Card;
import com.example.ejb.domain.Game;

import junit.framework.Assert;

public class TestGameService {

	@Test
	public void testStartGame(){
		
		Game mazo = new Game();
		
		ArrayList<ArrayList<Card>> arrayMazo = mazo.startGame();
		
		Assert.assertNotNull(arrayMazo);		
		Assert.assertEquals(3, arrayMazo.size());
		Assert.assertEquals(7, arrayMazo.get(0).size());
		Assert.assertEquals(7, arrayMazo.get(1).size());
		Assert.assertEquals(7, arrayMazo.get(2).size());
		
		
		for(int i=0; i<3;i++) {
			
			for(int j=0;j<7;j++) {
				System.out.print(arrayMazo.get(i).get(j).getCode());
				System.out.println(arrayMazo.get(i).get(j).getName());
			}
			
		}
		
	}
	
	
	
	
	
}
