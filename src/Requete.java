import java.sql.*;
import java.util.ArrayList;

public class Requete {
    //Attribut paramètre BDD
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cdaLibrary";
    private static final String USERNAME = "root"; // Change if necessary
    private static final String PASSWORD = ""; // Change if necessary
    //Connexion à la BDD
    private static final Connection connexion;
    static {
        try {
            if(Env.DB_URL.isEmpty()){
                connexion = DriverManager.getConnection(Env.DB_URL, Env.USERNAME, Env.PASSWORD);
            } else{
                connexion = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addLivreDB(Livre book, ArrayList<Livre> library) {
        try{
            //Connection à la BDD...
            Statement stmt = connexion.createStatement();
            //requête SQL
            String sql = "INSERT INTO Livre (nom_livre, description_livre, date_publication_livre, genre_livre)" + "VALUES (?, ?, ?, ?)";
            //Préparation de la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            //Bind des paramètres
            preparedStatement.setString(1, book.getTitre());
            preparedStatement.setString(2, book.getDescription());
            preparedStatement.setString(3, book.getDatePublication());
            preparedStatement.setString(4, book.getGenreAsString());
            //Exécution de la requête
            int addedRows = preparedStatement.executeUpdate();
            //test si l'enregistrement est ok, sinon on retire le livre de la library locale
            if (addedRows <= 0) {
                library.remove(book);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void deleteLivreDBbyTitle(Livre book, ArrayList<Livre> library) {
        try {
            // Connection à la BDD...
            Statement stmt = connexion.createStatement();
            // Requête SQL
            String sql = "DELETE FROM Livre WHERE nom_livre = ?";
            // Préparation de la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            // Bind des paramètres
            preparedStatement.setString(1, book.getTitre());
            // Exécution de la requête
            int deletedRows = preparedStatement.executeUpdate();
            // Test si la suppression est réussie, sinon on garde le livre dans la bibliothèque locale
            if (deletedRows > 0) {
                library.remove(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void loadDB(ArrayList<Livre> library) {
        Livre newBook;
        try {
            // Connection à la BDD ...
            Statement stmt = connexion.createStatement();
            //requête SQL
            String sql = "SELECT nom_livre, description_livre, date_publication_livre, genre_livre FROM Livre";
            //Préparation de la requête
            PreparedStatement preparedStatement = connexion.prepareStatement(sql);
            //Bind des paramètres
            ResultSet rs = preparedStatement.executeQuery();
            //Parcours du résultat
            while (rs.next()) {
                newBook = new Livre();
                newBook.setTitre(rs.getString("nom_livre"));
                newBook.setDescription(rs.getString("description_livre"));
                newBook.setDatePublication(rs.getString("date_publication_livre"));
                newBook.setGenreFromString(rs.getString("genre_livre"));
                library.add(newBook);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
