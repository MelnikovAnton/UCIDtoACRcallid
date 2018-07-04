package ru.melnikov.converter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.DataFormat;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Map;


public class Controller {

    @FXML private TextField ucidText;
    @FXML private TextField callidText;
    @FXML private Label errorLable;

    private final Converter converter;

    public Controller() {
        this.converter = new Converter();
    }

    public void convertToUCID() {
        String sCallId = callidText.getText();
        errorLable.setText("");
        try {
            ucidText.setText(converter.parseACR(sCallId));
            writeTextToClipboard(ucidText.getText());
            errorLable.setTextFill(Color.GREEN);
            errorLable.setText("UCID скопирован " + ucidText.getText() );
        } catch (UcidFormatException e) {
            errorLable.setTextFill(Color.RED);
            errorLable.setText(e.getMessage());
        }
    }

    public void convertToAcr() {
        String sUcid = ucidText.getText();
        errorLable.setText("");
        try {
            callidText.setText(converter.parse(sUcid));
            writeTextToClipboard(callidText.getText());
            errorLable.setTextFill(Color.GREEN);
            errorLable.setText("ACR Call Id скопирован " + callidText.getText() );
        } catch (UcidFormatException e) {
            errorLable.setTextFill(Color.RED);
            errorLable.setText(e.getMessage());
        }
    }

    private void writeTextToClipboard(String s) {
        Clipboard clipboard = Clipboard.getSystemClipboard();
        Map <DataFormat, Object> content = new HashMap <> ();
        content.put(DataFormat.PLAIN_TEXT, s);
        clipboard.setContent(content);
    }

}