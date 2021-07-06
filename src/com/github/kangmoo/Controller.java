package com.github.kangmoo;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.web.WebView;
import com.github.kangmoo.connection.Connection;
import net.sourceforge.plantuml.FileFormat;
import net.sourceforge.plantuml.FileFormatOption;
import net.sourceforge.plantuml.SourceStringReader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Controller {
    ExecutorService executor = Executors.newCachedThreadPool();
    @FXML
    private TextField host;
    @FXML
    private TextField userName;
    @FXML
    private TextField password;
    @FXML
    private TextArea command;
    @FXML
    private Button buttonRun;
    @FXML
    private Button buttonClearLog;
    @FXML
    private TextArea umlText;
    @FXML
    private TextArea consoleText;
    @FXML
    private TabPane rootPane;
    @FXML
    private WebView umlView;
    @FXML
    private Button buttonLoadUmlView;
    @FXML
    private Button buttonClearUmlText;

    private PrintStream ps = new PrintStream(new Console(consoleText));
    private Connection connection = Connection.getInstance();
    private ByteArrayOutputStream os;

    public void initialize() {
        rootPane.setMinSize(800, 600);
        ps = new PrintStream(new Console(consoleText));
        System.setOut(ps);
        System.setErr(ps);
    }

    public void onCommandRun(ActionEvent actionEvent) {
        executor.submit(()->{
            if (buttonRun.getText().equals("Run")) {
                connection.run(host.getText(), userName.getText(), password.getText(), command.getText());
                buttonRun.setText("Stop");
            } else {
                connection.stop();
            }
        });
    }

    public void updateUmlText(String text) {
        umlText.setText(text);
    }

    public void updateUmlView(String svg) {
        umlView.getEngine().loadContent(svg);
    }

    public String getUmlText() {
        return umlText.getText();
    }

    public void onClearUmlText(ActionEvent actionEvent) {
        umlText.clear();
    }

    public void onClearLog(ActionEvent actionEvent) {
        consoleText.clear();
    }

    public void onLoadUmlView(ActionEvent actionEvent) {
        executor.submit(()->{
            try {
                Platform.runLater(() -> this.updateUmlView("Loading ..."));
                String uml = "@startuml\n!theme bluegray\n" +
                        this.getUmlText() +
                        "\n@enduml";
                SourceStringReader reader = new SourceStringReader(uml);
                os = new ByteArrayOutputStream();
                reader.outputImage(os, new FileFormatOption(FileFormat.SVG));
                String svg = os.toString(String.valueOf(StandardCharsets.UTF_8));
                Platform.runLater(() -> this.updateUmlView(svg));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public class Console extends OutputStream {
        private TextArea console;

        public Console(TextArea console) {
            this.console = console;
        }

        public void appendText(String valueOf) {
            Platform.runLater(() -> console.appendText(valueOf));
        }

        public void write(int b) throws IOException {
            appendText(String.valueOf((char) b));
        }
    }

    public static Controller getController() {
        return Log2UmlMain.loader.getController();
    }

    public void enableRunButton() {
        buttonRun.setText("Run");
    }
}
