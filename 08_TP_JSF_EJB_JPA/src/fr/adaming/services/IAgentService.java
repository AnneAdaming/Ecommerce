package fr.adaming.services;

import javax.ejb.Local;

import fr.adaming.model.Agent;


@Local
public interface IAgentService {

	public Agent isExist(Agent a) throws Exception;
}
