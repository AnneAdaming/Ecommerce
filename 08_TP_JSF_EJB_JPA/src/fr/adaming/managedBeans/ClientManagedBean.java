package fr.adaming.managedBeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.Agent;
import fr.adaming.model.Client;
import fr.adaming.services.IClientService;

@ManagedBean(name = "clMB")
@RequestScoped
public class ClientManagedBean {

	@EJB
	private IClientService clService;

	private Agent agent;
	private Client client;
	private boolean cache;

	private HttpSession session;

	public ClientManagedBean() {
		this.client = new Client();
		cache = false;
	}

	// On met un getter et setter juste pour le client car c'est juste ce que
	// l'on appelle depuis la page html
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	// En fait pour cache aussi
	public boolean isCache() {
		return cache;
	}

	public void setCache(boolean cache) {
		this.cache = cache;
	}

	// Cette annotation sert � ex�cuter la m�thode juste apr�s l'instantiation
	// du Managed Bean
	@PostConstruct
	public void init() {
		// R�cup�ration de la session
		FacesContext contexte = FacesContext.getCurrentInstance();
		this.session = (HttpSession) contexte.getExternalContext().getSession(false);
		// R�cup�ration de l'agent � partir de la session
		this.agent = (Agent) session.getAttribute("agentSession");

	}

	public String ajouterClient() {
		// Appel de la m�thode service
		Client clientOut = clService.addClient(this.client, this.agent);

		if (clientOut.getId() != 0) {
			// R�cup�ration de la nouvelle liste � partir de la DB
			List<Client> listeClients = clService.getAllClientsByAgent(this.agent);
			// Actualiser la lsite des clients dans la session
			session.setAttribute("clientListe", listeClients);

			return "accueil";
		} else {
			// Afficher un message d'erreurs
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("L'ajout a �chou�"));
			return "ajout";
		}
	}

	public String modifierClient() {
		// Appel de la m�thode service
		int verif = clService.updateClient(this.client, this.agent);

		if (verif != 0) {
			// R�cup�ration de la nouvelle liste � partir de la DB
			List<Client> listeClients = clService.getAllClientsByAgent(this.agent);
			// Actualiser la lsite des clients dans la session
			session.setAttribute("clientListe", listeClients);

			return "accueil";
		} else {
			// Afficher un message d'erreurs
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La modification a �chou�"));
			return "update";
		}
	}

	public String supprClient() {
		// Appel de la m�thode service
		int verif = clService.deleteClient(this.client, this.agent);

		if (verif != 0) {
			// R�cup�ration de la nouvelle liste � partir de la DB
			List<Client> listeClients = clService.getAllClientsByAgent(this.agent);
			// Actualiser la lsite des clients dans la session
			session.setAttribute("clientListe", listeClients);

			return "accueil";
		} else {
			// Afficher un message d'erreurs
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La modification a �chou�"));
			return "delete";
		}
	}

	public String cherchClient() {
		client = clService.getClientById(this.client, this.agent);
		if (client != null) {
			cache = true;
			session.setAttribute("clientCherche", client);
			return "cherch";
		} else {
			cache = false;
			// Afficher un message d'erreurs
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La recherche a �chou�"));
			return "cherch";
		}
	}

}
