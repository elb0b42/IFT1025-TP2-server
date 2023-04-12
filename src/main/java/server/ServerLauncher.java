package server;

/**
 * Class qui sert a lancer le serveur
 */
public class ServerLauncher {
    /**
     * Port qui est utilisé pour la connection avec le client.
     */
    public final static int PORT = 1337;

    /**
     * Fonction qui démare le serveur et qui va exécuté les commade donné par le client.
     * @param args Ceci est un argument qui n'est pas utilisé dans notre code.
     */
    public static void main(String[] args) {
        Server server;
        try {
            server = new Server(PORT);
            System.out.println("Server is running...");
            server.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}