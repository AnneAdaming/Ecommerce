package fr.adaming.services;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Agent;
import fr.adaming.model.Client;

@Local
public interface IClientService {
	public List<Client> getAllClientsByAgent(Agent a);
	public Client addClient(Client cl, Agent a);
	public int updateClient(Client cl, Agent a);
	public int deleteClient(Client cl, Agent a);
	public Client getClientById(Client cl, Agent a);
}
