package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Agent;
import fr.adaming.model.Client;

//@Local 
//Remplacé dans la classe
public interface IClientDao {

	public List<Client> getAllClientsByAgent(Agent a);
	public Client addClient(Client cl);
	public int updateClient(Client cl);
	public int deleteClient(Client cl);
	public Client getClientById(Client cl);
	
}
