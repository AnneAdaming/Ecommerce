package fr.adaming.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fr.adaming.model.Agent;

@Stateless
public class AgentDaoImpl implements IAgentDao {

	@PersistenceContext(unitName = "PU_TP")
	EntityManager em;// cette annotation sert à injecter
	// un entityManager: le conteneur
	// EJB va l'instancier et me le
	// donne qnd j'en aurai besoin

	@Override
	public Agent isExist(Agent a) throws Exception {
		// la requete jpql
		String req="SELECT a FROM Agent a WHERE a.mail=:pMail AND a.mdp=:pMdp";
		
		// creation de query
		Query query=em.createQuery(req);
		
		// passage des params
		query.setParameter("pMail", a.getMail());
		query.setParameter("pMdp", a.getMdp());
		
		// envoyer la requete et recuperer le resultat
		Agent a_rec=(Agent) query.getSingleResult();
		
		return a_rec;
	}

}
