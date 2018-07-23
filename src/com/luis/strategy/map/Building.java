package com.luis.strategy.map;

import com.luis.strategy.constants.GameParams;

public class Building {
	
	int type;
	int state;
	int level;// 0, 1, 2 //Ejemplo: torre -> muralla -> fortaleza

	public Building(int type, int state, int level) {
		super();
		this.type = type;
		this.state = state;
		this.level = level;
	}

	public boolean isBuilding() {
		if (level < 0)
			return false;
		else
			return state < GameParams.BUILDING_STATE[type][level];
	}
	
	/**
	 * Devuelve el nivel de efectividad el cual aplica cuando se lega al estado del mismo
	 * @return
	 */
	public int getActiveLevel(){
		if(level < 0){
			return -1;
		}else{
			//Si se ha alcanzado el estado
			if(GameParams.BUILDING_STATE[type][level] == state){
				return level;
			}
			else{
				return level-1;
			}
		}
	}

	public int getType() {
		return type;
	}

	public int getState() {
		return state;
	}

	public int getLevel() {
		return level;
	}
}
