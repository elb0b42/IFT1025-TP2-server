package server.models;

import java.io.Serializable;

/**
 * Créer les cours et les serializes
 */
public class Course implements Serializable {

    private String name;
    private String code;
    private String session;

    /**
     *initialize les information du cours
     * @param name nom du cours
     * @param code code du cours
     * @param session session dans le quel le cours se trouve
     */
    public Course(String name, String code, String session) {
        this.name = name;
        this.code = code;
        this.session = session;
    }

    /**
     * Fonction qui va chercher le nom
     * @return retourne le nom du cours
     */
    public String getName() {
        return name;
    }

    /**
     *Fonction qui set le nom.
     * @param name nom du cours
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * fonction qui va chercher le code du cours.
     * @return retourne le code du cours
     */
    public String getCode() {
        return code;
    }

    /**
     * Fonction qui set le code du cours.
     * @param code  code du cours
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Fonction qui la chercher la session approprié.
     * @return retourne la session.
     */
    public String getSession() {
        return session;
    }

    /**
     * Fonction qui set la bonne session.
     * @param session session dans lequelle le cours se donne
     */
    public void setSession(String session) {
        this.session = session;
    }

    /**
     * créer les information du cours.
     * @return retourne les information du cours.
     */
    @Override
    public String toString() {
        return "Course{" +
                "name=" + name +
                ", code=" + code +
                ", session=" + session +
                '}';
    }
}
