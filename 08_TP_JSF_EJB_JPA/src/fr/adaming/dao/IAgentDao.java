package fr.adaming.dao;

import javax.ejb.Local;

import fr.adaming.model.Agent;

@Local
public interface IAgentDao {

	public Agent isExist(Agent a) throws Exception;
	
}
