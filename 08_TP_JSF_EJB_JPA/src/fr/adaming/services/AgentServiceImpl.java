package fr.adaming.services;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.dao.IAgentDao;
import fr.adaming.model.Agent;

@Stateful
public class AgentServiceImpl implements IAgentService {

	@EJB // cette annotation sert à injecter l'EJB DAO
	private IAgentDao agentDao;

	public IAgentDao getAgentDao() {
		return agentDao;
	}

	public void setAgentDao(IAgentDao agentDao) {
		this.agentDao = agentDao;
	}

	@Override
	public Agent isExist(Agent a) throws Exception {
		return agentDao.isExist(a);
	}

}
