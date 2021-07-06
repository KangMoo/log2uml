package com.github.kangmoo.connection;

import javafx.application.Platform;
import com.github.kangmoo.Controller;

/**
 *
 * @author kangmoo Heo
 */
public class Connection {
    private static final Connection INSTANCE = new Connection();
    private Thread thd;

    private Connection() {
    }

    public static Connection getInstance() {
        return INSTANCE;
    }

    public void connection(String host, String userName, String pwd) {

    }

    public void run(String host, String userName, String pwd, String command) {
        try {
            BashServiceImpl bashService = new BashServiceImpl();
            bashService.initSSHPassword(host, userName, pwd, 22);
            bashService.setOnMessage((msg) -> Platform.runLater(() -> Controller.getController().updateUmlText(msg)));
            bashService.exec(command);
            bashService.disconnectionSSH();
        } finally {
            Platform.runLater(() -> Controller.getController().enableRunButton());
        }
    }

    public void stop() {
        if (!thd.isInterrupted())
            thd.interrupt();
    }
}
