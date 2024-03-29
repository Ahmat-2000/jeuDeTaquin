package model.observerPattern;

/**
 * Interface définissant le contrat pour les modèles écoutables dans le cadre du patron de conception Observateur.
 * Les classes implémentant cette interface permettent à des écouteurs (listeners) externes de s'abonner
 * ou de se désabonner pour recevoir des notifications lorsque l'état du modèle change.
 */
public interface ListenableModel {
    /**
     * Ajoute un écouteur au modèle. L'écouteur ajouté sera notifié des changements dans le modèle.
     *
     * @param l L'écouteur à ajouter. Doit implémenter l'interface ModelListener.
     */
    void addModelListener(ModelListener l);

    /**
     * Retire un écouteur précédemment ajouté du modèle. Après cette opération, l'écouteur ne recevra plus
     * de notifications des changements du modèle.
     *
     * @param l L'écouteur à retirer. Doit avoir été précédemment ajouté avec addModelListener.
     */
    void removeModelListener(ModelListener l);
}
