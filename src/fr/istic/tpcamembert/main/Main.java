package fr.istic.tpcamembert.main;

import java.util.Observer;

import fr.istic.tpcamembert.adapter.Adapter;
import fr.istic.tpcamembert.controller.Controller;
import fr.istic.tpcamembert.generic.IRelay;
import fr.istic.tpcamembert.model.IModel;
import fr.istic.tpcamembert.model.Model;
import fr.istic.tpcamembert.view.CamembertView;
import fr.istic.tpcamembert.view.MainFrame;
import fr.istic.tpcamembert.view.TableView;

public class Main {

	public static void main(String[] args) {
		
		// création du modèle de données
		Model model = new Model("Dépenses");
		
		// valeurs tests
		model.addItem("Loyer", "le loyer par mois", 395);
		model.addItem("Eau", "l'eau par mois", 15);
		model.addItem("EDF", "l'électricité par mois", 45);
		model.addItem("Nourriture", "nom nom", 150);
		
		// création de l'adapteur et du controller
		// chacun possède une interface du suivant
		// (controller => adapter => model)
		Adapter adapter = new Adapter((IRelay)model);
		Controller controller = new Controller((IRelay)adapter);
		
		// création de la vue camembert et ajout à l'adapter en tant qu'observer
		CamembertView camembert = new CamembertView((IModel)model, (IRelay)controller);
		adapter.addObserver((Observer)camembert);

		// création de la vue jtable et ajout à l'adapter en tant qu'observer
		TableView table = new TableView((IModel)model, (IRelay)controller);
		adapter.addObserver((Observer)table);
		
		// création de la frame principal contenant
		// nos deux vues
		MainFrame mainFrame = new MainFrame(camembert, table);
	}

}
