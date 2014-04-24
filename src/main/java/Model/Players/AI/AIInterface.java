/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model.Players.AI;

import Model.Players.AI.Events.EndOfOperationsEvent;

/**
 *
 * @author wasson
 */
public interface AIInterface {
     public void startOperations();
     public void endOfOperations(EndOfOperationsEvent e);
}
