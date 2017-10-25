package fr.adaming.dao;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Agent;
import fr.adaming.model.Client;

@Stateless
@Local(fr.adaming.dao.IClientDao.class)
public class ClientDaoImpl implements IClientDao {
	
	@PersistenceContext(unitName="PU_TP")
	EntityManager em;

	@Override
	public List<Client> getAllClientsByAgent(Agent a) {
		// Requête JPQL
		String req="SELECT cl FROM Client cl WHERE cl.agent.id=:pId";
		
		Query query = em.createQuery(req);
		query.setParameter("pId", a.getId());
		
		List<Client>liste=query.getResultList();
		
		return liste;
	}

	@Override
	public Client addClient(Client cl) {
		System.out.println("id du client avant persist : "+cl.getId());
		em.persist(cl);		
		System.out.println("id du client après persist : "+cl.getId());
		return cl;
	}

	@Override
	public int updateClient(Client cl) {
		String req="UPDATE Client cl SET nom=:pNom, prenom=:pPrenom, dateNaissance=:pDate WHERE id=:pId AND agent_id=:pAgent";
		Query query = em.createQuery(req);
		query.setParameter("pNom", cl.getNom());
		query.setParameter("pPrenom", cl.getPrenom());
		query.setParameter("pDate", cl.getDateNaissance());
		query.setParameter("pId", cl.getId());
		query.setParameter("pAgent", cl.getAgent().getId());
		
		int verif=query.executeUpdate();
		
		return verif;
	}

	@Override
	public Client getClientById(Client cl) {
		String req="SELECT cl FROM Client cl WHERE id=:pId";
		Query query=em.createQuery(req);
		query.setParameter("pId", cl.getId());
		Client client = (Client) query.getSingleResult();
		return client;
	}

	@Override
	public int deleteClient(Client cl) {
		String req="DELETE FROM Client cl WHERE cl.id=:pId AND cl.agent.id=:pAgent";
		Query query=em.createQuery(req);
		query.setParameter("pId", cl.getId());
		query.setParameter("pAgent", cl.getAgent().getId());
		return query.executeUpdate();
	}

}
