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
     *
     * @return retourne le nom du cours
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return "Course{" +
                "name=" + name +
                ", code=" + code +
                ", session=" + session +
                '}';
    }
}
