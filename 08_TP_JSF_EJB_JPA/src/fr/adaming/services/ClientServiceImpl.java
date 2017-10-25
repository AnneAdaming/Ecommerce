package fr.adaming.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IClientDao;
import fr.adaming.model.Agent;
import fr.adaming.model.Client;

@Stateful
public class ClientServiceImpl implements IClientService {
	@EJB
	IClientDao clientDao;

	@Override
	public List<Client> getAllClientsByAgent(Agent a) {
		return clientDao.getAllClientsByAgent(a);
	}

	@Override
	public Client addClient(Client cl, Agent a) {
		cl.setAgent(a);
		return clientDao.addClient(cl);
	}

	@Override
	public int updateClient(Client cl, Agent a) {
		cl.setAgent(a);
		return clientDao.updateClient(cl);
	}

	@Override
	public int deleteClient(Client cl, Agent a) {
		cl.setAgent(a);
		return clientDao.deleteClient(cl);
	}

	@Override
	public Client getClientById(Client cl, Agent a) {
		return clientDao.getClientById(cl);
	}

}
