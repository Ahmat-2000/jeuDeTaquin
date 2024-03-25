package model.observerPattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite représentant un modèle écoutable dans le cadre du patron de conception Observateur.
 * Elle définit la base pour la gestion des écouteurs (listeners) qui sont intéressés par les changements
 * d'état de ce modèle.
 */
public abstract class AbstractListenableModel implements ListenableModel {
    // Liste des écouteurs qui sont abonnés pour recevoir des notifications lorsque le modèle change.
    private List<ModelListener> listeners;

    /**
     * Constructeur de AbstractListenableModel. Initialise la liste des écouteurs.
     */
    public AbstractListenableModel(){
        listeners = new ArrayList<>();
    }

    /**
     * Ajoute un écouteur au modèle. L'écouteur sera notifié des changements du modèle.
     *
     * @param l L'écouteur (listener) à ajouter.
     */
    @Override
    public void addModelListener(ModelListener l){
        listeners.add(l);
    }

    /**
     * Retire un écouteur du modèle. L'écouteur ne recevra plus de notifications des changements du modèle.
     *
     * @param l L'écouteur (listener) à retirer.
     */
    @Override
    public void removeModelListener(ModelListener l){
        listeners.remove(l);
    }

    /**
     * Notifie tous les écouteurs enregistrés qu'un changement a eu lieu dans le modèle.
     * Cette méthode est protégée et destinée à être appelée par les classes dérivées
     * lorsque leur état interne change.
     */
    protected void fireChange(){
        for(ModelListener l : listeners){
            // Appelle la méthode de rappel de chaque écouteur pour les informer du changement.
            l.somethingHasChanged(this);
        }
    }
}
