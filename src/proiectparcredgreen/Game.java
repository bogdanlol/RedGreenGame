package proiectparcredgreen;

import proiectparcredgreen.Player;

class Game {

    private static Player[] tabela = {
        null, null, null,
        null, null, null,
        null, null, null,
        null, null, null,
        null, null , null,
        null, null , null,
        null, null , null
        
    };
    
    static Player currentPlayer;

   
    public static boolean castigator() {
        return
            (tabela[0] != null && tabela[0] == tabela[1] && tabela[0] == tabela[2])
          ||(tabela[0] != null && tabela[0] == tabela[5] && tabela[0] == tabela[10])
          ||(tabela[0] != null && tabela[0] == tabela[4] && tabela[0] == tabela[8])
          ||(tabela[1] != null && tabela[1] == tabela[2] && tabela[1] == tabela[3])
          ||(tabela[1] != null && tabela[1] == tabela[5] && tabela[1] == tabela[9])
          ||(tabela[1] != null && tabela[1] == tabela[6] && tabela[1] == tabela[11])
          ||(tabela[2] != null && tabela[2] == tabela[6] && tabela[2] == tabela[10])
          ||(tabela[2] != null && tabela[2] == tabela[5] && tabela[2] == tabela[8])
        	||(tabela[3] != null && tabela[3] == tabela[6] && tabela[3] == tabela[9])
        	||(tabela[3] != null && tabela[3] == tabela[7] && tabela[3] == tabela[11])
        	||(tabela[4] != null && tabela[4] == tabela[5] && tabela[4] == tabela[6])
        	||(tabela[4] != null && tabela[4] == tabela[9] && tabela[4] == tabela[14])
        	||(tabela[4] != null && tabela[4] == tabela[8] && tabela[4] == tabela[12])
        	||(tabela[5] != null && tabela[5] == tabela[6] && tabela[5] == tabela[7])
        	||(tabela[5] != null && tabela[5] == tabela[9] && tabela[5] == tabela[13])
        	||(tabela[5] != null && tabela[5] == tabela[10] && tabela[5] == tabela[15])
        	||(tabela[6] != null && tabela[6] == tabela[9] && tabela[6] == tabela[12])
        	||(tabela[6] != null && tabela[6] == tabela[10] && tabela[6] == tabela[14])
        	||(tabela[6] != null && tabela[6] == tabela[9] && tabela[6] == tabela[12])
        	||(tabela[7] != null && tabela[7] == tabela[11] && tabela[7] == tabela[15])
        	||(tabela[7] != null && tabela[7] == tabela[10] && tabela[7] == tabela[13])
        	||(tabela[8] != null && tabela[8] == tabela[12] && tabela[8] == tabela[16])
        	||(tabela[8] != null && tabela[8] == tabela[9] && tabela[8] == tabela[10])
        	||(tabela[8] != null && tabela[8] == tabela[13] && tabela[8] == tabela[18])
        	||(tabela[9] != null && tabela[9] == tabela[10] && tabela[9] == tabela[11])
        	||(tabela[9] != null && tabela[9] == tabela[13] && tabela[9] == tabela[17])
        	||(tabela[9] != null && tabela[9] == tabela[14] && tabela[9] == tabela[19])
        	||(tabela[10] != null && tabela[10] == tabela[14] && tabela[10] == tabela[18])
        	||(tabela[10] != null && tabela[10] == tabela[13] && tabela[10] == tabela[16])
        	||(tabela[11] != null && tabela[11] == tabela[14] && tabela[11] == tabela[17])
        	||(tabela[11] != null && tabela[11] == tabela[15] && tabela[11] == tabela[19])
        	||(tabela[12] != null && tabela[12] == tabela[13] && tabela[12] == tabela[14])
        	||(tabela[13] != null && tabela[13] == tabela[14] && tabela[13] == tabela[15])
        	||(tabela[16] != null && tabela[16] == tabela[17] && tabela[16] == tabela[18])
        	||(tabela[17] != null && tabela[17] == tabela[18] && tabela[17] == tabela[19]);
      	 }

    
    public static boolean tabelaPlina() {
        for (int i = 0; i < tabela.length; i++) {
            if (tabela[i] == null) {
                return false;
            }
        }
        return true;
    }


    public static void schimbaJucator(int location){
    	
    	currentPlayer = currentPlayer.opponent;
		currentPlayer.otherPlayerMoved(location);
    }
    
    public synchronized static boolean legalMove(int loc,Player player) {
        
    	if(player == currentPlayer && tabela[loc] == null ) {
        		
        			tabela[loc] = currentPlayer;
        			
        		    	schimbaJucator(loc);
        				
        		    	return true; 
        					
    }		
        return false;
    }
 }