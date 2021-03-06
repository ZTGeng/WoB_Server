/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lobby;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.Log;

/**
 *
 * @author yanxingwang
 */
public class MiniGameServers {
    
    private static MiniGameServers sServers;
    private final Map<String, MiniGame> miniGames = new HashMap<String, MiniGame>();
    
    public static MiniGameServers getInstance() {
        if (sServers == null) {
            sServers = new MiniGameServers();
        }
        return sServers;
    }
    
    public MiniGameServers() {
        initMiniGames();
        
        int totalAvailables = 0;
        for (MiniGame mg : miniGames.values()) {
            if ( mg.isAvailable() ) {
                ++totalAvailables;
            }
        }
        Log.printf("All mini games initialized: %d/%d avaiable", totalAvailables, miniGames.size());
    }
    
    public void runServers() {
        try {
            for (MiniGame g : this.miniGames.values()) {
                g.run();
            }
        } catch (IOException ex) {
            Logger.getLogger(MiniGameServers.class.getName()).log(Level.SEVERE, null, ex);
        }            
    }
    
    private void initMiniGames() {
        MiniGame game;

        game = new MiniGame("Running Rhino");
        game.setAsMultiPlayerGame("../Speed_Server/dist/Speed_Server.jar", 20039);
        miniGames.put(game.getName(), game);
        
        game = new MiniGame("Cards of the Wild");
        game.setAsMultiPlayerGame("../WoC_Server/dist/Cards_Server.jar", 20038);
        miniGames.put(game.getName(), game);
    }
}
