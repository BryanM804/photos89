package launcher;

import java.io.File;

public final class Session
{
    private final String username;
    private final File userFile;

    public Session(String username)
    {
        this.username = username;

        File workingDir = new File("").getAbsoluteFile();
        File userFile = new File(workingDir, "data/" + username);

        if (!userFile.exists())
        {
            if (!userFile.mkdir())
            {
                throw new IllegalStateException();
            }
        }

        this.userFile = userFile;
    }

    public String getUsername()
    {
        return this.username;
    }

    public File getUserFile()
    {
        return this.userFile;
    }
}
