package server.models;

import java.io.Serializable;


public class RegistrationForm implements Serializable {
    private String prenom;
    private String nom;
    private String email;
    private String matricule;
    private Course course;
    /**
     * Ce constructeur créer une nouvelle instance de la classe et
     * initialise ses attributs avec les valeurs fournies en paramètre.
     */
    public RegistrationForm(String prenom, String nom, String email, String matricule, Course course) {
        this.prenom = prenom;
        this.nom = nom;
        this.email = email;
        this.matricule = matricule;
        this.course = course;
    }

    /**
     * getters qui permettent d'accéder au prenom.
     * @return retourne le prenom.
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * setters qui permettent de modifier le prenom.
     * @param prenom Prénom entrée par le client.
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * getters qui permettent d'accéder au nom
     * @return retourne le nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * setters qui permettent de modifier le nom.
     * @param nom nom entrée par le client.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * getters qui permettent d'accéder au Email.
     * @return retourne le Email
     */
    public String getEmail() {
        return email;
    }

    /**
     * setters qui permettent de modifier le Email
     * @param email Email entrée par le client
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getters qui permettent d'accéder au matricule.
     * @return retourne le matricule.
     */
    public String getMatricule() {
        return matricule;
    }

    /**
     * setters qui permettent de modifier le matricule.
     * @param matricule Matricule entré par le client.
     */
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    /**
     * getters qui permettent d'accéder au cours.
     * @return retourne le cours.
     */
    public Course getCourse() {
        return course;
    }

    /**
     * setters qui permettent de modifier le cours
     * @param course retourne le cours
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Fonction qui concatene tout les information écrit par le client.
     * @return retourne les information concaténé
     */
    @Override
    public String toString() {
        return "InscriptionForm{" + "prenom='" + prenom + '\'' + ", nom='" + nom + '\'' + ", email='" + email + '\'' + ", matricule='" + matricule + '\'' + ", course='" + course + '\'' + '}';
    }
}
