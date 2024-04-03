package application;

import application.album.AlbumManager;
import javafx.controller.ApplicationController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import launcher.Launcher;
import launcher.Session;

public final class Application
{
    private static Application instance;

    private AlbumManager albumManager;
    private Session session;

    public Application(Session session)
    {
        instance = this;
        this.session = session;

        this.albumManager = new AlbumManager();
        this.albumManager.cacheAllAlbums();
    }

    /**
     * Opens the main application GUI
     */
    public void openGUI(Stage mainStage)
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Launcher.class.getResource("../javafx/view/Application.fxml"));

        try
        {
            BorderPane root = loader.load();
            Scene mainScene = new Scene(root);
            mainStage.setScene(mainScene);
            mainStage.setTitle("Photos");
            mainStage.setX(300.0);
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }

        ApplicationController.getInstance().updateAlbumList(this.albumManager.getLoadedAlbums());
    }

    /**
     * @return The current application instance
     */
    public static Application getInstance()
    {
        if (instance == null)
        {
            throw new IllegalStateException("No active application");
        }

        return instance;
    }

    /**
     * @return The current user session
     */
    public Session getSession()
    {
        if (this.session == null)
        {
            throw new IllegalStateException("No active session");
        }

        return this.session;
    }

    public AlbumManager getAlbumManager()
    {
        if (this.albumManager == null)
        {
            throw new IllegalStateException("No active album manager");
        }

        return this.albumManager;
    }

    /**
     * Closes the application, logs the user out, and displays the login screen
     */
    public void logout()
    {
        instance = null;
        session = null;

        Launcher.openLogin();
    }
}
